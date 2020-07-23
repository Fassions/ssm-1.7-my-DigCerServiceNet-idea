package cn.com.companyUser.service.impl;

import cn.com.common.agent.ArrivalAccountEnum;
import cn.com.common.agent.BaseDomain;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.agent.OrderTypeEnum;
import cn.com.common.cache.CaCheManager;
import cn.com.common.model.*;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.export.ExpressExcel;
import cn.com.common.model.export.ExpressInvoiceExcel;
import cn.com.common.model.export.InvoiceExcel;
import cn.com.common.model.export.OrderManageExcel;
import cn.com.common.model.export.ProductionExcel;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.model.search.TblTemplateExcelSearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.common.page.PageUtil;
import cn.com.common.service.CommonService;
import cn.com.common.utils.DateUtils;
import cn.com.common.utils.FlowUtil;
import cn.com.common.utils.IntegerUtil;
import cn.com.common.utils.PutDateSource;
import cn.com.commonUser.service.OrderUserService;
import cn.com.companyUser.dao.ExcelTemplateMapper;
import cn.com.companyUser.dao.OrderCompanyMapper;
import cn.com.companyUser.service.ExcelTemplateService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Horace.zhang on 2019/7/16.
 */
@Service
public class ExcelTemplateImpl implements ExcelTemplateService {
    @Autowired
    ExcelTemplateMapper excelTemplateMapper;

    @Autowired
    OrderUserService orderUserService;

    @Autowired
    OrderQueryCompanyService companyService;

    @Autowired
    private OrderCompanyMapper orderCompanyMapper;

    @Autowired
    private CommonService commonService;

    private CaCheManager caCheManager = CaCheManager.getInstance();

    @Override
    public Page<TblTemplateExcelSearcher> listTblTemplateExcelWithPage(TblTemplateExcelSearcher TblTemplateExcelSearcher, Limit limit) throws Exception {
        List<TblTemplateExcelSearcher> querySearchers = excelTemplateMapper.listTblTemplateExcelWithPage(TblTemplateExcelSearcher);
        Page<TblTemplateExcelSearcher> page = PageUtil.listToPage(querySearchers,limit,querySearchers.size());
        //TODO:检索多行
        return page;
    }

    @Override
    public List<TemPlateExcelVo> getExportTemplateExcelByOrderId(String[] orderId,String templateExcelId) throws Exception {
        List<TemPlateExcelVo> data = excelTemplateMapper.getExportTemplateExcelByOrderId(orderId);
        if(data.size()!=0){
            paramTemPlateExcel(data,templateExcelId);
            return data;
        }else{
            return null;
        }
    }

    @Override
    public List<TemPlateExcelVo> getExportTemplateExcelSystemByOrderId(String[] orderId,String templateExcelId) throws Exception {
        List<TemPlateExcelVo> data = null;
        if(Objects.equals(BaseDomain.INVOICEEXCEL, templateExcelId)){  //财务开票表
            data =  excelTemplateMapper.getInvoiceExcelByOrderId(orderId);
        }else if(Objects.equals(BaseDomain.EXPRESSEXCEL, templateExcelId)){   //快递表
            data = excelTemplateMapper.getExcpressExcelByOrderId(orderId);
        }
        if(data.size()!=0){
            paramTemPlateExcel(data,templateExcelId);
            return data;
        }else{
            return null;
        }
    }


    public void paramTemPlateExcel(List<TemPlateExcelVo> temPlateExcelVos,String templateExcelId) throws Exception{
        //TODO:组装数据导出条件
        for(int i =0;i<temPlateExcelVos.size();i++){
            TemPlateExcelVo tem = temPlateExcelVos.get(i);
            if(Objects.equals(BaseDomain.PRODUCTIONEXCEL, templateExcelId) 
            		|| Objects.equals(BaseDomain.EXPRESSEXCEL, templateExcelId)) { //生产和发运
                //TODO:组装生产任务单(已使用固定字段)
//                paramTemPlateExcelProductionId(temPlateExcelVos);

                if(Objects.equals(BaseDomain.EXPRESSEXCEL, templateExcelId)){  //产品快递
                    //TODO:组装品名
                    if(Objects.equals(BaseDomain.NEED_BEFORE_INVOICE_ONE, IntegerUtil.parseInt(tem.getNeedBeforeInvoice()))){  //先开票品名为证书
                        // System.out.println("-----------------------"+tem.getOrderId()+"---------为先开票---------");
                        tem.setExpressType(tem.getProductName());
                    }else if("0".equals(tem.getProductListId())){ //配件发票数量一定为0，不存在订单只有配件情况，如有产品，则配件发票数量也为0
                        tem.setExpressType(tem.getProductName());
                    }else if(Objects.equals(BaseDomain.NEED_BEFORE_INVOICE_ZERO, IntegerUtil.parseInt(tem.getNeedBeforeInvoice()))){ //后开票判断所有产品
                        // System.out.println("-----------------------"+tem.getOrderId()+"---------为后开票---------");
                        String cacheKey = tem.getOrderId() +  DateUtils.lastMonday();
                        Map map = (Map) caCheManager.get("ehcache.getOrderExpressType",cacheKey);
                        String expressType = tem.getProductName()+"和发票";
                        if(map==null){ //缓存订单产品不存在，品名为证书和发票
                            // System.out.println("-----------------------"+tem.getOrderId()+"-------存入map -----------"+tem.getProductListId());
                            tem.setExpressType(expressType);
                            map = new HashMap<>();
                            map.put(tem.getProductListId(),tem.getProductListId());
                            caCheManager.put("ehcache.getOrderExpressType",cacheKey,map); //存入缓存
                        }else if(Objects.equals(map.get(tem.getProductListId()),tem.getProductListId())){ //同一订单相同产品，品名都相同
                            // System.out.println("-----------------------"+tem.getOrderId()+"-------存入map相同产品-----------");
                            tem.setExpressType(expressType);
                        }else {  //存入过更优先级 产品 则品名为证书
                            tem.setExpressType(tem.getProductName());
                        }
                    }
                }
                if(Objects.equals(BaseDomain.PRODUCTIONEXCEL, templateExcelId)){  //生产
                    //TODO:组装发票数量
                    if(Objects.equals(BaseDomain.NEED_BEFORE_INVOICE_ONE, IntegerUtil.parseInt(tem.getNeedBeforeInvoice()))){  //先开票数量为0
                    	// System.out.println("-----------------------"+tem.getOrderId()+"---------为先开票---------");
                        tem.setInvoiceNum("0");
                    }else if("0".equals(tem.getProductListId())){ //配件发票数量一定为0，不存在订单只有配件情况，如有产品，则配件发票数量也为0
                        tem.setInvoiceNum("0");
                    }else if(Objects.equals(BaseDomain.NEED_BEFORE_INVOICE_ZERO, IntegerUtil.parseInt(tem.getNeedBeforeInvoice()))){ //后开票判断所有产品
                    	// System.out.println("-----------------------"+tem.getOrderId()+"---------为后开票---------");
                        String cacheKey = tem.getOrderId() +  DateUtils.lastMonday();
                        Map map = (Map) caCheManager.get("ehcache.getOrderInvoiceNum",cacheKey);
                        if(map==null){ //缓存订单产品不存在，发票数量为1 （数量只为0或1）
                        	// System.out.println("-----------------------"+tem.getOrderId()+"-------存入map -----------"+tem.getProductListId());
                            tem.setInvoiceNum("1");
                            map = new HashMap<>();
                            map.put(tem.getProductListId(),tem.getProductListId());
                            caCheManager.put("ehcache.getOrderInvoiceNum",cacheKey,map); //存入缓存
                        }else if(Objects.equals(map.get(tem.getProductListId()),tem.getProductListId())){ //相同产品，发票数量都为1
                        	// System.out.println("-----------------------"+tem.getOrderId()+"-------存入map相同产品-----------");
                            tem.setInvoiceNum("1");
                        }else {  //存入过更优先级 产品 则发票数量为0
                            tem.setInvoiceNum("0");
                        }
                     }
                }
            } else if (Objects.equals(BaseDomain.INVOICEEXPRESSEXCEL, templateExcelId)) { //发票
            	tem.setExpressType("发票");
            } else if(!Objects.equals(BaseDomain.INVOICEEXCEL, templateExcelId)) {  //财务开票表 流水号不变
                //流水号组装
                if(i==0 && temPlateExcelVos.size()==1){
                    tem.setFlowId(FlowUtil.flowWhileChar(null,tem.getFlowId(),null));
                }else if(i==0 && temPlateExcelVos.size()!=1){
                    tem.setFlowId(FlowUtil.flowWhileChar(null,tem.getFlowId(),temPlateExcelVos.get(i+1).getFlowId()));
                }else if(i==temPlateExcelVos.size()-1){
                    tem.setFlowId(FlowUtil.flowWhileChar(temPlateExcelVos.get(i-1).getFlowId(),tem.getFlowId(),null));
                }else {
                    tem.setFlowId(FlowUtil.flowWhileChar(temPlateExcelVos.get(i-1).getFlowId(),tem.getFlowId(),temPlateExcelVos.get(i+1).getFlowId()));
                }
            } 

            //过滤产品字母 专网、解锁卡不存在产品字母
            if("3".equals(tem.getProductListId()) || "6".equals(tem.getProductListId())){
                tem.setLabelSuffix("");
            }

            //订单类型
            tem.setOrderType(OrderTypeEnum.getName(IntegerUtil.parseInt(tem.getOrderType())));
            //到款账户
            tem.setArrivalAccount(ArrivalAccountEnum.getName(IntegerUtil.parseInt(tem.getArrivalAccount())));
            if(Objects.equals(BaseDomain.INVOICEEXCEL, templateExcelId)) { //财务开票表 开票信息根据 invoiceId
                //财务开票表 开票信息
                OrderInvoice orderInvoice = orderUserService.getOrderInvoiceById(tem.getInvoiceId());
                if(orderInvoice!=null){
                    tem.setSocialCreditCode(
                            (StringUtils.isNotBlank(orderInvoice.getSocialCreditCode())?"代码："+orderInvoice.getSocialCreditCode()+"\n":"")
                                    +(StringUtils.isNotBlank(orderInvoice.getAddress())?"地址:"+orderInvoice.getAddress()+"\n":"")
                                    +(StringUtils.isNotBlank(orderInvoice.getPhone())?"电话:"+orderInvoice.getPhone()+"\n":"")
                                    +(StringUtils.isNotBlank(orderInvoice.getDepositBank())?"开户行:"+orderInvoice.getDepositBank()+"\n":"")
                                    +(StringUtils.isNotBlank(orderInvoice.getAccount())?"账号:"+orderInvoice.getAccount()+"\n":"")
                    );
                }
            }else {
                //开票信息
                List<OrderInvoice> invoices = orderUserService.getOrderInvoiceByOrderId(tem.getOrderId());
                if(invoices!=null){
                    for(OrderInvoice orderInvoice:invoices){
                        tem.setSocialCreditCode(
                                (StringUtils.isNotBlank(orderInvoice.getSocialCreditCode())?"代码："+orderInvoice.getSocialCreditCode()+"\n":"")
                                        +(StringUtils.isNotBlank(orderInvoice.getAddress())?"地址:"+orderInvoice.getAddress()+"\n":"")
                                        +(StringUtils.isNotBlank(orderInvoice.getPhone())?"电话:"+orderInvoice.getPhone()+"\n":"")
                                        +(StringUtils.isNotBlank(orderInvoice.getDepositBank())?"开户行:"+orderInvoice.getDepositBank()+"\n":"")
                                        +(StringUtils.isNotBlank(orderInvoice.getAccount())?"账号:"+orderInvoice.getAccount()+"\n":"")
                        );
                    }
                }
            }

            //备忘录
            List<OrderMemo> memos = companyService.getOrderMemoByOrderId(tem.getOrderId());
            String memoValue = "";
            if(memos!=null){
                for(OrderMemo memo:memos){
                    memoValue += StringUtils.isNotBlank(memo.getMemo())?memo.getMemo()+"、":"";
                }
                tem.setMemo(memoValue);
            }

            //快递订单号
//            List<OrderExpress> orderExpresses = orderUserService.getOrderExpressByOrderid(tem.getOrderId());
//            String invoiceExpressNo ="";
//            String productExpressNo = "";
//            String productInvoiceExpressNo = "";
//            if(orderExpresses!=null){
//                for(OrderExpress express:orderExpresses){
//                    //快递发运物品：1.发票，2证书，3.证书与发票（一起邮寄）
//                    if(IntegerUtil.equals(1,express.getExpressType())){
//                        invoiceExpressNo +=  StringUtils.isNotBlank(express.getExpressNo())?express.getExpressNo()+"、":"";
//                    }else if(IntegerUtil.equals(2,express.getExpressType())){
//                        productExpressNo += StringUtils.isNotBlank(express.getExpressNo())?express.getExpressNo()+"、":"";
//                    }else if(IntegerUtil.equals(3,express.getExpressType())){
//                        productInvoiceExpressNo +=StringUtils.isNotBlank(express.getExpressNo())?express.getExpressNo()+"、":"";
//                    }
//                }
//                tem.setInvoiceExpressNo(invoiceExpressNo);
//                tem.setProductExpressNo(productExpressNo);
//                tem.setProductInvoiceExpressNo(productInvoiceExpressNo);
//            }
        }
    }


    @Override
    public TblTemplateExcel getTblTemPlateExcelById(String id) throws Exception {
        Map map = new HashMap<>();
        map.put("id",id);
        List<TblTemplateExcel> data = excelTemplateMapper.getTblTemPlateExcel(map);
        if(data.size()!=0){
            return data.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<TblTemplateExcel> getTblTemPlateExcelByUserId(String userId) throws Exception {
        Map map = new HashMap<>();
        map.put("userId",userId);
        List<TblTemplateExcel> data = excelTemplateMapper.getTblTemPlateExcel(map);
        if(data.size()!=0){
            return data;
        }else{
            return null;
        }
    }

    @Override
    public List<TblTemplateExcel> getTblTemPlateExcel(Map map) throws Exception {
        List<TblTemplateExcel> data = excelTemplateMapper.getTblTemPlateExcel(map);
        if(data.size()!=0){
            return data;
        }else{
            return null;
        }
    }

    @Override
    public void postTblTemplateExcel(TblTemplateExcel tblTblTemplateExcel) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(tblTblTemplateExcel.getId(),tblTblTemplateExcel.getUserId()),tblTblTemplateExcel);
        excelTemplateMapper.postTblTemplateExcel(tblTblTemplateExcel);
    }

    @Override
    public void putTblTemplateExcel(TblTemplateExcel tblTemplateExcel) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(tblTemplateExcel.getId(),tblTemplateExcel.getUserId()),tblTemplateExcel);
        excelTemplateMapper.putTblTemplateExcel(tblTemplateExcel);
    }

    @Override
    public void deleteTblTemplateExcel(String tblTemplateExcelId) throws Exception {
        excelTemplateMapper.deleteTblTemplateExcel(tblTemplateExcelId);
    }

    @Override
    public Map setTemplateExcelDownload(String tblTemplateExcelId) throws Exception {

        TblTemplateExcel tblTemplateExcel = getTblTemPlateExcelById(tblTemplateExcelId);
        if(tblTemplateExcel==null){
            return null;
        }
        Map map = new HashMap<>();
        List<String> excelTitle = new ArrayList<>();
        List<String> excelData = new ArrayList<>();
        map.put("fileTitle",tblTemplateExcel.getTemplateName());
        map.put("excelTitle",excelTitle);
        map.put("excelData",excelData);
        String[] excelFieldArray = tblTemplateExcel.getExcelField().split(";");
        for (String excelField : excelFieldArray){
            ExcelDownLoadParam(excelTitle,excelData,excelField);
        }
        return map;
    }

    /**
     * 根据订单id 或查询条件 导出定义模板
     * @param exportType 导出类型 0根据查询条件导出 1根据选中orderId导出
     * @param orderId
     * @param templateExcelId
     * @param orderQuerySearcher    查询条件
     * @return
     * @throws Exception
     */
    @Override
    public Map exportExcelTemplate(String exportType, String[] orderId, String templateExcelId, OrderQuerySearcher orderQuerySearcher) throws Exception {
        Map map = null;
        List<TemPlateExcelVo> temPlateExcelVos = null;
        if(exportType.equals("0")){ //0根据查询条件导出
            orderId = arrayOrderIdByOrderQuerySearcher(orderQuerySearcher);
        }
        if(Objects.equals(BaseDomain.PRODUCTIONEXCEL, templateExcelId)){  //生产任务单同自定义模板
            temPlateExcelVos = getExportTemplateExcelByOrderId(orderId,templateExcelId);
            map = setTemplateSystemExcelDownload(templateExcelId);

        }else if(Objects.equals(BaseDomain.INVOICEEXCEL, templateExcelId)){  //财务开票表
            temPlateExcelVos = getExportTemplateExcelSystemByOrderId(orderId,templateExcelId);
            map = setTemplateSystemExcelDownload(templateExcelId);

        }else if(Objects.equals(BaseDomain.EXPRESSEXCEL, templateExcelId)){   //快递表
            temPlateExcelVos = getExportTemplateExcelSystemByOrderId(orderId,templateExcelId);
            map = setTemplateSystemExcelDownload(templateExcelId);

        }else {
            temPlateExcelVos = getExportTemplateExcelByOrderId(orderId,templateExcelId);
            map = setTemplateExcelDownload(templateExcelId);
        }
        map.put("temPlateExcelVos",temPlateExcelVos);
        return map;
    }

    @Override
    public String[] arrayOrderIdByOrderQuerySearcher(OrderQuerySearcher querySearcher) throws Exception {
        List<OrderQuerySearcher> querySearchers = orderCompanyMapper.companyListWithOrderQuery(querySearcher);
        String[] orderId = new String[querySearchers.size()];
        for(int i = 0;i<querySearchers.size();i++){
            orderId[i] = querySearchers.get(i).getOrderId();
        }
        return orderId;
    }

    /**
     *
     * @param excelType 0生产任务单 1.财务开票表 2.产品快递单 3.订单管理表 4.开票快递单
     * @return
     * @throws Exception
     */
    @Override
    public Map setTemplateSystemExcelDownload(String excelType) throws Exception {
        String title = "";
        Field[] fields = null;
        if(Objects.equals(BaseDomain.PRODUCTIONEXCEL, excelType)){
            title = "生产任务单_";
            fields =ProductionExcel.class.getDeclaredFields();
        }else if(Objects.equals(BaseDomain.INVOICEEXCEL, excelType)){
            title = "财务开票单_"+DateUtils.getDateYYYYMMDD();
            fields =InvoiceExcel.class.getDeclaredFields();
        }else if(Objects.equals(BaseDomain.EXPRESSEXCEL, excelType)){
            title = "产品快递单_"+DateUtils.getDateYYYYMMDD();
            fields =ExpressExcel.class.getDeclaredFields();
        }else if(Objects.equals(BaseDomain.ORDERMANAGEEXCEL, excelType)){
            title = "订购单管理表_"+DateUtils.getDateYYYYMMDD();
            fields =OrderManageExcel.class.getDeclaredFields();
        }else if(Objects.equals(BaseDomain.INVOICEEXPRESSEXCEL, excelType)){
            title = "开票快递单_"+DateUtils.getDateYYYYMMDD();
            fields =ExpressInvoiceExcel.class.getDeclaredFields();
        }
        Map map = new HashMap<>();
        List<String> excelTitle = new ArrayList<>();
        List<String> excelData = new ArrayList<>();
        map.put("fileTitle",title);
        map.put("excelTitle",excelTitle);
        map.put("excelData",excelData);
        for (Field excelField : fields){
            ExcelDownLoadParam(excelTitle,excelData,excelField.getName());
        }
        return map;
    }
    private void ExcelDownLoadParam(List excelTitle,List excelData,String excelField){
        if("orderDate".equals(excelField)){
            excelTitle.add("订购日期");
            excelData.add(excelField);
        }else if("provinceName".equals(excelField)){
            excelTitle.add("省份");
            excelData.add(excelField);
        }else if("cityName".equals(excelField)){
            excelTitle.add("地市");
            excelData.add(excelField);
        }else if("orderUnitName".equals(excelField)){
            excelTitle.add("订购单位");
            excelData.add(excelField);
        }else if("invoiceUnitName".equals(excelField)){
            excelTitle.add("开票单位");
            excelData.add(excelField);
        }else if("flowId".equals(excelField)){
            excelTitle.add("流水号");
            excelData.add(excelField);
        }else if("productionFlowId".equals(excelField)){
            excelTitle.add("生产任务单号");
            excelData.add(excelField);
        }else if("auditor".equals(excelField)){
            excelTitle.add("审单人");
            excelData.add(excelField);
        }else if("auditorDate".equals(excelField)){
            excelTitle.add("审单日期");
            excelData.add(excelField);
        }else if("productPrice".equals(excelField)){
            excelTitle.add("单价");
            excelData.add(excelField);
        }else if("prefixName".equals(excelField)){
            excelTitle.add("产品省份字母");
            excelData.add(excelField);
        }else if("labelSuffix".equals(excelField)){
            excelTitle.add("产品字母");
            excelData.add(excelField);
        }else if("supplier".equals(excelField)){
            excelTitle.add("供货方");
            excelData.add(excelField);
        }else if("labelStart".equals(excelField)){
            excelTitle.add("起始号");
            excelData.add(excelField);
        }else if("labelCode".equals(excelField)){
            excelTitle.add("证书编号");
            excelData.add(excelField);
        }else if("labelEnd".equals(excelField)){
            excelTitle.add("末尾号");
            excelData.add(excelField);
        }else if("deliveryDate".equals(excelField)){
            excelTitle.add("发货日期");
            excelData.add(excelField);
        }else if("productTotalCount".equals(excelField)){
            excelTitle.add("数量");
            excelData.add(excelField);
        }else if("expressTotalCount".equals(excelField)){
            excelTitle.add("发货数");
            excelData.add(excelField);
        }else if("productTotalAmount".equals(excelField)){
            excelTitle.add("金额");
            excelData.add(excelField);
        }else if("expressTotalAmount".equals(excelField)){
            excelTitle.add("发货金额");
            excelData.add(excelField);
        }else if("orderType".equals(excelField)){
            excelTitle.add("类型");
            excelData.add(excelField);
        }else if("arrivalAccount".equals(excelField)){
            excelTitle.add("到款账户");
            excelData.add(excelField);
        }
        else if("invoiceNum".equals(excelField)){
            excelTitle.add("发票数量");
            excelData.add(excelField);
        }
        else if("billingDate".equals(excelField)){
            excelTitle.add("开票日期");
            excelData.add(excelField);
        }else if("arrivalDate".equals(excelField)){     //到款日期只需要年月日
            excelTitle.add("到款日期");
            excelData.add(excelField);
        }else if("productName".equals(excelField)){
            excelTitle.add("证书类别");
            excelData.add(excelField);
        }else if("productType".equals(excelField)){
            excelTitle.add("证书型号");
            excelData.add(excelField);
        }else if("regionName".equals(excelField)){
            excelTitle.add("大区");
            excelData.add(excelField);
        }else if("saleManager".equals(excelField)){
            excelTitle.add("销售经理");
            excelData.add(excelField);
        }else if("receiveName".equals(excelField)){
            excelTitle.add("收件人");
            excelData.add(excelField);
        }else if("receivePhone".equals(excelField)){
            excelTitle.add("收件固定电话");
            excelData.add(excelField);
        }else if("receiveMobile".equals(excelField)){
            excelTitle.add("收件手机号");
            excelData.add(excelField);
        }else if("receiveUnitAddress".equals(excelField)){
            excelTitle.add("收件地址");
            excelData.add(excelField);
        }else if("socialCreditCode".equals(excelField)){
            excelTitle.add("开票信息");
            excelData.add(excelField);
        }else if("invoiceExpressNo".equals(excelField)){
            excelTitle.add("发票快递单");
            excelData.add(excelField);
        }else if("productExpressNo".equals(excelField)){
            excelTitle.add("证书快递单");
            excelData.add(excelField);
        }else if("productInvoiceExpressNo".equals(excelField)){
            excelTitle.add("证书和发票快递单");
            excelData.add(excelField);
        }else if("memo".equals(excelField)){
            excelTitle.add("备忘录");
            excelData.add(excelField);
        }else if("productionMemo".equals(excelField)){
            excelTitle.add("生产注意事项");
            excelData.add(excelField);
        }else if("expressType".equals(excelField)){
            excelTitle.add("品名");
            excelData.add(excelField);
        }else if("empty".equals(excelField)){
            excelTitle.add("空");      //快递表值为空
            excelData.add(excelField);
        }else if("empty1".equals(excelField)){
            excelTitle.add("空");      //快递表值为空
            excelData.add(excelField);
        }else if("expressNo".equals(excelField)){
            excelTitle.add("快递单号");
            excelData.add(excelField);
        }else if("deliveryDate".equals(excelField)){
            excelTitle.add("发货日期");
            excelData.add(excelField);
        }else if("expressMessage".equals(excelField)){
            excelTitle.add("发货备注");
            excelData.add(excelField);
        }else if("county".equals(excelField)){
            excelTitle.add("区县");   //快递表值为空
            excelData.add(excelField);
        }else if("bourn".equals(excelField)){
            excelTitle.add("目的地");   //快递表值为空
            excelData.add(excelField);
        }else if("invoiceCode".equals(excelField)){
            excelTitle.add("代码");   //开票表值为空
            excelData.add(excelField);
        }else if("receiveUnitName".equals(excelField)){
            excelTitle.add("收件单位");
            excelData.add(excelField);
        }else if("invoiceType".equals(excelField)){
            excelTitle.add("开票类型");
            excelData.add(excelField);
        }else if("invoiceTotalAmount".equals(excelField)){
            excelTitle.add("发票金额");
            excelData.add(excelField);
        }
    }
}
