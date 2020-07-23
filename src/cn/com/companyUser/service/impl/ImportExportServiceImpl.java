package cn.com.companyUser.service.impl;

import cn.com.common.CommonProperty;
import cn.com.common.agent.BaseDomain;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.*;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.export.ExpressExcel;
import cn.com.common.model.export.ExpressInvoiceExcel;
import cn.com.common.model.export.InvoiceExcel;
import cn.com.common.model.imports.ConstantMsg;
import cn.com.common.model.imports.ImportPaidExcel;
import cn.com.common.utils.DateUtils;
import cn.com.common.utils.ExcelUtil;
import cn.com.common.utils.IntegerUtil;
import cn.com.common.utils.ResultMessage;
import cn.com.commonUser.service.OrderUserService;
import cn.com.companyUser.dao.ImportExportMapper;
import cn.com.companyUser.service.ExcelTemplateService;
import cn.com.companyUser.service.ImportExportService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kohsuke.rngom.parse.host.Base;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Horace.zhang on 2019/7/23.
 */
@Service
public  class ImportExportServiceImpl implements ImportExportService {

    @Value("${httpfilerootOrderTemp}")
    private String httpfilerootOrderTemp;
    @Autowired
    ImportExportMapper importExportMapper;
    @Autowired
    ExcelTemplateService excelTemplateService;
    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private OrderQueryCompanyService companyService;

    @Override
    public List<TemPlateExcelVo> exportExcel(String templateExcelId,String year) throws Exception {
        List<TemPlateExcelVo> temPlateExcelVos = null;
        if(Objects.equals(BaseDomain.PRODUCTIONEXCEL, templateExcelId)){  //生产任务单
            temPlateExcelVos = importExportMapper.exportProductionExcel();

        }else if(Objects.equals(BaseDomain.INVOICEEXCEL, templateExcelId)){  //财务开票表
            temPlateExcelVos = importExportMapper.exportInvoiceExcel();

        }else if(Objects.equals(BaseDomain.EXPRESSEXCEL, templateExcelId)){   //快递表
            temPlateExcelVos = importExportMapper.exportExpressExcel();

        }else if(Objects.equals(BaseDomain.ORDERMANAGEEXCEL, templateExcelId)){   //订购单管理表
            temPlateExcelVos = importExportMapper.exportOrderManageExcel(year);
        }
        //组装剩下字段
        excelTemplateService.paramTemPlateExcel(temPlateExcelVos,templateExcelId);
        return temPlateExcelVos;
    }

    @Override
    public ResultMessage convertPaidExcelDetailData(String userId,ResultMessage message, MultipartFile file,HttpServletRequest request) throws Exception {
        String path = httpfilerootOrderTemp;
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path,fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<ImportPaidExcel> paidExcelsList = new ArrayList<>();
        ExcelUtil<ImportPaidExcel> excelUtil = new ExcelUtil<ImportPaidExcel>(ImportPaidExcel.class);
        paidExcelsList = excelUtil.importExcel("",new FileInputStream(targetFile));
        targetFile.delete();
        if(CollectionUtils.isEmpty(paidExcelsList)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("上传文件内容不能为空");
            return message;
        }
        List<ImportPaidExcel> errorList = new ArrayList<>();
        List<OrderPaid> paids = new ArrayList<>();
        for(ImportPaidExcel paidExcel : paidExcelsList){
            ImportPaidExcel errorVo = new ImportPaidExcel();
            BeanUtilsEx.copyProperties(paidExcel,errorVo);
            if(StringUtils.isBlank(paidExcel.getFlowId())){
                errorVo.setError(ConstantMsg.FLOWIDISNULL);
                errorList.add(errorVo);
                continue;
            }

            Order order = orderUserService.getOrderByFlowId(paidExcel.getFlowId());
            if(order==null){
                errorVo.setError(ConstantMsg.FLOWIDBYORDERISNULL);
                errorList.add(errorVo);
                continue;
            }else if(Objects.equals(BaseDomain.ORDER_STATUS_FIVE,order.getOrderStatus()) || Objects.equals(BaseDomain.ORDER_STATUS_SIX,order.getOrderStatus())){
                errorVo.setError(ConstantMsg.ORDERERROR);
                errorList.add(errorVo);
                continue;
            }
            //可保存登记数据
            OrderPaid orderPaid = new OrderPaid();
            BeanUtilsEx.copyProperties(paidExcel,orderPaid);
            orderPaid.setOrderId(order.getId());
            //是否全部到款
            if(StringUtils.isNotBlank(paidExcel.getIsTotalArrival())){
                if("是".equals(paidExcel.getIsTotalArrival())){
                    orderPaid.setIsTotalArrival(1);
                }else if("否".equals(paidExcel.getIsTotalArrival())){
                    orderPaid.setIsTotalArrival(0);
                }else{
                    errorVo.setError(ConstantMsg.ISTOTALARRIVALERRO);
                    errorList.add(errorVo);
                    continue;
                }
            }
            //到款账户
            if(StringUtils.isNotBlank(paidExcel.getArrivalAccount())) {
                if ("辰锐".equals(paidExcel.getArrivalAccount())) {
                    orderPaid.setArrivalAccount(1);
                } else if ("三所".equals(paidExcel.getArrivalAccount())) {
                    orderPaid.setIsTotalArrival(0);
                } else {
                    errorVo.setError(ConstantMsg.ARRIVALACCOUNTERRO);
                    errorList.add(errorVo);
                    continue;
                }
            }
            //到款金额
            if(StringUtils.isNotBlank(paidExcel.getArrivalAmount())) {
                try {
                    orderPaid.setArrivalAmount(Double.parseDouble(paidExcel.getArrivalAmount()));
                }catch (Exception e){
                    errorVo.setError(ConstantMsg.ARRIVALAMOUNTERROR);
                    errorList.add(errorVo);
                    continue;
                }
            }
            //到款日期
            try{
                orderPaid.setArrivalDate(DateUtils.parseStringByPatternYYYYMMDD(paidExcel.getArrivalDate()));
            }catch (Exception e){
                errorVo.setError(ConstantMsg.ARRIVALDATEERROR);
                errorList.add(errorVo);
                continue;
            }
            paids.add(orderPaid);
        }
        //保存
        if(!CollectionUtils.isEmpty(paids)){
            companyService.postOrderPaid(paids,userId);
        }
        if(CollectionUtils.isEmpty(errorList)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("导入成功");
            return message;
        }
        ExcelUtil<ImportPaidExcel> util = new ExcelUtil<ImportPaidExcel>(ImportPaidExcel.class);
        String sheetName = "导入到款信息错误反馈";
        return util.exportUserProgress(errorList,sheetName,httpfilerootOrderTemp,null,request);
    }

    @Override
    public ResultMessage convertInvoiceExcelDetailData(String userId, ResultMessage message, MultipartFile file, HttpServletRequest request) throws Exception {
        String path = httpfilerootOrderTemp;
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path,fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<InvoiceExcel> paidExcelsList = new ArrayList<>();
        ExcelUtil<InvoiceExcel> excelUtil = new ExcelUtil<InvoiceExcel>(InvoiceExcel.class);
        paidExcelsList = excelUtil.importExcel("",new FileInputStream(targetFile));
        targetFile.delete();
        if(CollectionUtils.isEmpty(paidExcelsList)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("上传文件内容不能为空");
            return message;
        }
        List<InvoiceExcel> errorList = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        for(InvoiceExcel invoiceExcel : paidExcelsList){
            InvoiceExcel errorVo = new InvoiceExcel();
            BeanUtilsEx.copyProperties(invoiceExcel,errorVo);
            if(StringUtils.isBlank(invoiceExcel.getFlowId())){
                errorVo.setError(ConstantMsg.FLOWIDISNULL);
                errorList.add(errorVo);
                continue;
            }

            Order order = orderUserService.getOrderByFlowId(invoiceExcel.getFlowId());
            if(order==null){
                errorVo.setError(ConstantMsg.FLOWIDBYORDERISNULL);
                errorList.add(errorVo);
                continue;
            }else if(Objects.equals(BaseDomain.ORDER_STATUS_FIVE,order.getOrderStatus()) || Objects.equals(BaseDomain.ORDER_STATUS_SIX,order.getOrderStatus())){
                errorVo.setError(ConstantMsg.ORDERERROR);
                errorList.add(errorVo);
                continue;
            }
            //可保存的开票日期
            try{
                order.setBillingDate(DateUtils.parseStringByPatternYYYYMMDD(invoiceExcel.getBillingDate()));
                order.setOrderInvoiceStatus(1);
            }catch (Exception e){
                errorVo.setError(ConstantMsg.BILLINGDATEERROR);
                errorList.add(errorVo);
                continue;
            }
            orders.add(order);
        }
        //更改开票日期
        if(!CollectionUtils.isEmpty(orders)){
            orderUserService.putOrder(orders);
        }
        if(CollectionUtils.isEmpty(errorList)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("导入成功");
            return message;
        }
        ExcelUtil<InvoiceExcel> util = new ExcelUtil<InvoiceExcel>(InvoiceExcel.class);
        String sheetName = "导入开票日期错误反馈";
        return util.exportUserProgress(errorList,sheetName,httpfilerootOrderTemp,null,request);
    }

    @Override
    public ResultMessage convertExpressExcelDetailData(String userId, ResultMessage message, MultipartFile file, HttpServletRequest request) throws Exception {
        String path = httpfilerootOrderTemp;
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path,fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<ExpressExcel> paidExcelsList = new ArrayList<>();
        ExcelUtil<ExpressExcel> excelUtil = new ExcelUtil<ExpressExcel>(ExpressExcel.class);
        paidExcelsList = excelUtil.importExcel("",new FileInputStream(targetFile));
        targetFile.delete();
        if(CollectionUtils.isEmpty(paidExcelsList)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("上传文件内容不能为空");
            return message;
        }
        List<ExpressExcel> errorList = new ArrayList<>();
        //待新增发运数据
        List<OrderExpress> expressesPost = new ArrayList<>(); 
        //待更新发运数据
        List<OrderExpress> expressesPut = new ArrayList<>(); 
        //待订单状态流转为发运中数据
        List<Order> orderStatusList = new ArrayList<>();  
        for(ExpressExcel expressExcel : paidExcelsList){
            ExpressExcel errorVo = new ExpressExcel();
            BeanUtilsEx.copyProperties(expressExcel,errorVo);
            if(StringUtils.isBlank(expressExcel.getProductionFlowId())){
                errorVo.setError(ConstantMsg.PRODUCTIONFLOWIDISNULL);
                errorList.add(errorVo);
                continue;
            }else if(StringUtils.isBlank(expressExcel.getExpressNo())){
                errorVo.setError(ConstantMsg.EXPRESSNOISNULL);
                errorList.add(errorVo);
                continue;
            }
            OrderProduct product = orderUserService.getOrderProductByproductionFlowId(expressExcel.getProductionFlowId());
            if(product==null){
                errorVo.setError(ConstantMsg.PRODUCTIONFLOWIDBYORDERISNULL);
                errorList.add(errorVo);
                continue;
            }

            Order order = orderUserService.getOrderByFlowId(product.getFlowId());
            if(order==null){
                errorVo.setError(ConstantMsg.FLOWIDBYORDERISNULL);
                errorList.add(errorVo);
                continue;
            }else if(Objects.equals(BaseDomain.PRODUCTIONERRORSTATUS_ONE,order.getProductionErrorStatus())){
                errorVo.setError(ConstantMsg.ORDERPRODUCTIONERROR);
                errorList.add(errorVo);
                continue;
            }else if(Objects.equals(BaseDomain.ORDER_STATUS_FIVE,order.getOrderStatus()) || Objects.equals(BaseDomain.ORDER_STATUS_SIX,order.getOrderStatus())){
                errorVo.setError(ConstantMsg.ORDERERROR);
                errorList.add(errorVo);
                continue;
            }
            orderStatusList.add(order);
            //可保存的发运数据
            OrderExpress orderExpress = new OrderExpress();
            BeanUtilsEx.copyProperties(expressExcel,orderExpress);
            orderExpress.setOrderId(order.getId());
            //快递公司默认中通快递
            orderExpress.setExpressName("中通快递");
            //快递发运物品类型
            if(StringUtils.isNotBlank(expressExcel.getExpressType())){
                if((expressExcel.getExpressType()).contains("和发票")){
                    orderExpress.setExpressType(3);
                }else if((expressExcel.getExpressType()).contains("发票")){
                    orderExpress.setExpressType(1);
                }else {
                    orderExpress.setExpressType(2);
//                    errorVo.setError(ConstantMsg.EXPRESSTYPEERROR);
//                    errorList.add(errorVo);
//                    continue;
                }
            }
            //快递发运日期
            try{
                if(StringUtils.isNotBlank(expressExcel.getDeliveryDate())){
                    orderExpress.setDeliveryDate(DateUtils.parseStringByPatternYYYYMMDD(expressExcel.getDeliveryDate()));
                }
            }catch (Exception e){
                errorVo.setError(ConstantMsg.DELIVERYDATEERROR);
                errorList.add(errorVo);
                continue;
            }
            OrderExpress expressPut = orderUserService.getOrderExpressByProductionFlowId(expressExcel.getProductionFlowId());
            if(expressPut!=null){  //存在做覆盖
                BeanUtilsEx.copyProperties(orderExpress,expressPut);
                expressesPut.add(expressPut);
            } else {
            	expressesPost.add(orderExpress);
            }
            //订单状态为生产中需要变更为发运中
            if(Objects.equals(BaseDomain.ORDER_STATUS_TWO,order.getOrderStatus())
                    || Objects.equals(BaseDomain.ORDER_STATUS_TWO_ONE,order.getOrderStatus())
                    || Objects.equals(BaseDomain.ORDER_STATUS_TWO_TWO,order.getOrderStatus())){  
                //expressesPost.add(orderExpress);
                //流转订单状态为发运中
                companyService.putOrderStatus(orderStatusList,userId,BaseDomain.ORDER_STATUS_THREE);
            }
        }
        //更新
        if(!CollectionUtils.isEmpty(expressesPut)){
            companyService.putOrderExpress(expressesPut,userId);
        }
        //保存
        if(!CollectionUtils.isEmpty(expressesPost)){

            companyService.postOrderExpress(expressesPost,userId);
        }

        if(CollectionUtils.isEmpty(errorList)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("导入成功");
            return message;
        }
        ExcelUtil<ExpressExcel> util = new ExcelUtil<ExpressExcel>(ExpressExcel.class);
        String sheetName = "导入到款信息错误反馈";
        return util.exportUserProgress(errorList,sheetName,httpfilerootOrderTemp,null,request);
    }

    @Override
    public ResultMessage convertInvoiceExpressExcelDetailData(String userId, ResultMessage message, MultipartFile file, HttpServletRequest request) throws Exception {
        String path = httpfilerootOrderTemp;
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path,fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<ExpressInvoiceExcel> paidExcelsList = new ArrayList<>();
        ExcelUtil<ExpressInvoiceExcel> excelUtil = new ExcelUtil<ExpressInvoiceExcel>(ExpressInvoiceExcel.class);
        paidExcelsList = excelUtil.importExcel("",new FileInputStream(targetFile));
        targetFile.delete();
        if(CollectionUtils.isEmpty(paidExcelsList)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("上传文件内容不能为空");
            return message;
        }
        List<ExpressInvoiceExcel> errorList = new ArrayList<>();
        List<OrderInvoiceExpress> expressesPost = new ArrayList<>(); //待新增发运数据
        List<OrderInvoiceExpress> expressesPut = new ArrayList<>(); //待更新发运数据
        for(ExpressInvoiceExcel expressExcel : paidExcelsList){
            ExpressInvoiceExcel errorVo = new ExpressInvoiceExcel();
            BeanUtilsEx.copyProperties(expressExcel,errorVo);
            if(StringUtils.isBlank(expressExcel.getFlowId())){
                errorVo.setError(ConstantMsg.FLOWIDISNULL);
                errorList.add(errorVo);
                continue;
            }else if(StringUtils.isBlank(expressExcel.getExpressNo())){
                errorVo.setError(ConstantMsg.EXPRESSNOISNULL);
                errorList.add(errorVo);
                continue;
            }
            Order order = orderUserService.getOrderByFlowId(expressExcel.getFlowId());
            if(order==null){
                errorVo.setError(ConstantMsg.FLOWIDBYORDERISNULL);
                errorList.add(errorVo);
                continue;
            }else if(Objects.equals(BaseDomain.PRODUCTIONERRORSTATUS_ONE,order.getProductionErrorStatus())){
                errorVo.setError(ConstantMsg.ORDERPRODUCTIONERROR);
                errorList.add(errorVo);
                continue;
            }else if(Objects.equals(BaseDomain.ORDER_STATUS_FIVE,order.getOrderStatus()) || Objects.equals(BaseDomain.ORDER_STATUS_SIX,order.getOrderStatus())){
                errorVo.setError(ConstantMsg.ORDERERROR);
                errorList.add(errorVo);
                continue;
            }

            //可保存的发运数据
            OrderInvoiceExpress orderExpress = new OrderInvoiceExpress();
            BeanUtilsEx.copyProperties(expressExcel,orderExpress);
            orderExpress.setOrderId(order.getId());
            //快递公司默认中通快递
            orderExpress.setExpressName("中通快递");
            //快递发运日期
            try{
                if(StringUtils.isNotBlank(expressExcel.getDeliveryDate())){
                    orderExpress.setDeliveryDate(DateUtils.parseStringByPatternYYYYMMDD(expressExcel.getDeliveryDate()));
                }
            }catch (Exception e){
                errorVo.setError(ConstantMsg.DELIVERYDATEERROR);
                errorList.add(errorVo);
                continue;
            }

            OrderInvoiceExpress expressPut = orderUserService.getOrderInvoiceExpressByFlowId(expressExcel.getFlowId());
            if(expressPut!=null){  //存在做覆盖
                BeanUtilsEx.copyProperties(orderExpress,expressPut);
                expressesPut.add(expressPut);
            }else {
                expressesPost.add(orderExpress);
            }
        }
        //更新
        if(!CollectionUtils.isEmpty(expressesPut)){
            companyService.putOrderInvoiceExpress(expressesPut,userId);
        }
        //保存
        if(!CollectionUtils.isEmpty(expressesPost)){
            companyService.postOrderInvoiceExpress(expressesPost,userId);
        }

        if(CollectionUtils.isEmpty(errorList)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("导入成功");
            return message;
        }
        ExcelUtil<ExpressInvoiceExcel> util = new ExcelUtil<ExpressInvoiceExcel>(ExpressInvoiceExcel.class);
        String sheetName = "导入到款信息错误反馈";
        return util.exportUserProgress(errorList,sheetName,httpfilerootOrderTemp,null,request);
    }
}
