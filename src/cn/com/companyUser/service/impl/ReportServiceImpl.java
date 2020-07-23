package cn.com.companyUser.service.impl;

import cn.com.common.agent.BaseDomain;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.Order;
import cn.com.common.model.OrderHistoryStatus;
import cn.com.common.model.TblOrderReport;
import cn.com.common.model.TblProductList;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.service.CommonService;
import cn.com.common.utils.DateUtils;
import cn.com.common.utils.PutDateSource;
import cn.com.common.utils.ZipUtils;
import cn.com.commonUser.service.OrderUserService;
import cn.com.companyUser.controller.ReportController;
import cn.com.companyUser.dao.ReportMapper;
import cn.com.companyUser.service.ExcelTemplateService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import cn.com.companyUser.service.ReportService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Created by Horace.zhang on 2019/12/9.
 */
@Service
public class ReportServiceImpl implements ReportService{

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    ExcelTemplateService excelTemplateService;

    @Autowired
    CommonService commonService;

    @Autowired
    OrderUserService orderUserService;

    @Autowired
    OrderQueryCompanyService queryCompanyService;

    @Override
    public List<TemPlateExcelVo> exportExcel(String templateExcelId,String productListId,String startDayHour,String endDayHour) throws Exception {
        List<TemPlateExcelVo> temPlateExcelVos = null;
        Map map = new HashMap();
        //报表生成数据开始时间与结束时间
        map.put("startDate",startDayHour);
        map.put("endDate",endDayHour);
        map.put("productListId",productListId);
        if (Objects.equals(BaseDomain.PRODUCTIONEXCEL, templateExcelId)) {  //生产任务单
            temPlateExcelVos = reportMapper.getReportProduction(map);
        } else if (Objects.equals(BaseDomain.EXPRESSEXCEL, templateExcelId)) {  //产品快递表发运单
            temPlateExcelVos = reportMapper.reportExpressExcel(map);
        } else if (Objects.equals(BaseDomain.INVOICEEXPRESSEXCEL, templateExcelId)) { //开票快递发运单
	    	temPlateExcelVos = reportMapper.reportInvoiceExpressExcel(map);
	    }
        //组装剩下字段
        excelTemplateService.paramTemPlateExcel(temPlateExcelVos,templateExcelId);
        return temPlateExcelVos;
    }

    @Override
    public List<TemPlateExcelVo> getReportProduction(String startDateHour, String endDateHour, String productListId) throws Exception {
        Map map = new HashMap();
        //报表生成数据开始时间与结束时间
        map.put("startDate",startDateHour);
        map.put("endDate",endDateHour);
        map.put("productListId",productListId);
        return reportMapper.getReportProduction(map);
    }

    @Override
    public void downLoadReportExcel(String buliderTime, String[] productListIds,String fileType,String zipName, HttpServletResponse response) throws Exception {
        List<File> files = new ArrayList<>();
        if(Objects.equals(fileType,BaseDomain.FILETYPE_TWO.toString())){ //TODO:快递发运单直接查询不以产品为单位
            List<TblOrderReport> reports = getTblOrderReportArray(null,buliderTime,fileType,BaseDomain.FILESTATUS_ONE);
            if(reports!=null){
                for(TblOrderReport report:reports){ //添加List<File>文件进入压缩
                    files.add(new File(report.getFileServerName()+report.getFileName()));
                }
            }
        }else { //非快递发运单
            if(productListIds==null){ //未选择产品默认全部
                List<TblProductList> tblProductLists = commonService.getTblProductList();
                productListIds = new String[tblProductLists.size()];
                for(int i = 0;i<tblProductLists.size();i++){
                    productListIds[i] = tblProductLists.get(i).getId();
                }
            }
            for(String productListId:productListIds){
                List<TblOrderReport> reports = getTblOrderReportArray(productListId,buliderTime,fileType,BaseDomain.FILESTATUS_ONE);
                if(reports!=null){
                    for(TblOrderReport report:reports){ //添加List<File>文件进入压缩
                        files.add(new File(report.getFileServerName()+report.getFileName()));
                    }
                }
            }
        }

        ZipUtils.downLoadFile(zipName,files,response);
    }

    @Override
    public void putOrderStatusAndHistory(String startDate, String endDate, String userId) throws Exception {
        Map map = new HashMap();
        //报表生成数据开始时间与结束时间
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("groupBy",true);//过滤orderId
        List<TemPlateExcelVo> temPlateExcelVos = reportMapper.getReportProduction(map);
        if(temPlateExcelVos.size()!=0){
            for(TemPlateExcelVo tem : temPlateExcelVos){
                Order order = orderUserService.getOrderById(tem.getOrderId());
                if(order!=null){
                    if(Objects.equals(order.getOrderStatus(),BaseDomain.ORDER_STATUS_SEVEN)){  //判断订单是否为待生产状态
                        //更改订单状态
                        order.setOrderStatus(BaseDomain.ORDER_STATUS_TWO);
                        orderUserService.putOrder(order);
                        //新增流转记录
                        OrderHistoryStatus historyStatus = new OrderHistoryStatus();
                        historyStatus.setHistoryStatus(BaseDomain.ORDER_STATUS_TWO);
                        historyStatus.setOrderId(order.getId());
                        queryCompanyService.postOrderHistoryStatus(historyStatus,userId);
                    }
                }
            }
        }else {
            logger.error("需要状态变更，数据为空 startDate:{0},endDate:{1}",startDate,endDate);
        }
    }

    @Override
    public TblOrderReport getTblOrderReportByProductListIdStartTime(String productListId,String name,String startTime,String fileType) throws Exception {
        Map map = new HashMap<>();
        if(StringUtils.isBlank(productListId)){
            map.put("productListId",new String[]{});
        }else {
            map.put("productListId",new String[]{productListId});
        }

        map.put("startTime",startTime);
        map.put("fileType",fileType);
        map.put("name",name);
        List<TblOrderReport> param = reportMapper.getTblOrderReport(map);
        if(param.size()!=0){
            return param.get(0);
        }else {
            return null;
        }
    }
    @Override
    public TblOrderReport getTblOrderReportByProductListIdStartTime(String[] productListId,String builderTime,String fileType) throws Exception {
        Map map = new HashMap<>();
        map.put("productListId",productListId);
        map.put("builderTime",builderTime);
        map.put("fileType",fileType);
        List<TblOrderReport> param = reportMapper.getTblOrderReport(map);
        if(param.size()!=0){
            return param.get(0);
        }else {
            return null;
        }
    }

    @Override
    public TblOrderReport getTblOrderReport(String[] productListId,String fileType) throws Exception {
        Map map = new HashMap<>();
        map.put("fileStatus",BaseDomain.FILESTATUS_ONE); //生成完成有效数据
        map.put("fileType",fileType);
        map.put("productListId",productListId);
        List<TblOrderReport> param = reportMapper.getTblOrderReport(map);
        if(param.size()!=0){
            return param.get(0);
        }else {
            return null;
        }
    }

    @Override
    public TblOrderReport getTblOrderReport(String productListId, String startTime, String fileType, Integer fileStatus) throws Exception {
        Map map = new HashMap<>();
        map.put("productListId",new String[]{productListId});
        map.put("startTime",startTime);
        map.put("fileType",fileType);
        map.put("fileStatus",fileStatus);
        List<TblOrderReport> param = reportMapper.getTblOrderReport(map);
        if(param.size()!=0){
            return param.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<TblOrderReport> getTblOrderReportArray(String productListId, String startTime, String fileType, Integer fileStatus) throws Exception {
        Map map = new HashMap<>();
        if(StringUtils.isBlank(productListId)){
            map.put("productListId",new String[]{});
        }else {
            map.put("productListId",new String[]{productListId});
        }
        map.put("builderTime",startTime);
        map.put("fileType",fileType);
        map.put("fileStatus",fileStatus);
        List<TblOrderReport> param = reportMapper.getTblOrderReport(map);
        if(param.size()!=0){
            return param;
        }else {
            return null;
        }
    }

    @Override
    public void postTblOrderReport(TblOrderReport orderReport) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(orderReport.getId(),""),orderReport);
        reportMapper.postTblOrderReport(orderReport);
    }

    @Override
    public void postTblOrderReport(List<TblOrderReport> tblOrderReport) throws Exception {
        for(TblOrderReport orderReport:tblOrderReport){
            BeanUtilsEx.copyProperties(PutDateSource.createDate(orderReport.getId(),""),orderReport);
            reportMapper.postTblOrderReport(orderReport);
        }

    }

    @Override
    public void putTblOrderReport(TblOrderReport orderReport) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(orderReport.getId(),""),orderReport);
        reportMapper.putTblOrderReport(orderReport);
    }

    @Override
    public void putTblOrderReport(List<TblOrderReport> tblOrderReport) throws Exception {
        for(TblOrderReport orderReport:tblOrderReport){
            BeanUtilsEx.copyProperties(PutDateSource.updateDate(orderReport.getId(),""),orderReport);
            reportMapper.putTblOrderReport(orderReport);
        }

    }

    @Override
    public void deleteTblOrderReport(String taskId,String fileType) throws Exception {
        Map map = new HashMap();
        map.put("taskId",taskId);
        map.put("fileType",fileType);
        reportMapper.deleteTblOrderReport(map);
    }
}
