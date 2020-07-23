package cn.com.common.task;

import cn.com.common.agent.BaseDomain;
import cn.com.common.cache.CaCheManager;
import cn.com.common.model.TblOrderReport;
import cn.com.common.model.TblProductList;
import cn.com.common.model.TblReportTask;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.service.CommonService;
import cn.com.common.utils.*;
import cn.com.companyUser.service.ExcelTemplateService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import cn.com.companyUser.service.ReportService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 报表定时生成存入
 * Created by Horace.zhang on 2019/12/5.
 */
@Component
public class ExecutorReport {

    private static final Logger _logger = LoggerFactory.getLogger(ExecutorReport.class);

    @Value("${httpfilerootReport}")
    private String httpfilerootReport;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ExcelTemplateService templateService;

    @Autowired
    private ExcelTemplateService excelTemplateService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private OrderQueryCompanyService companyService;

    private CaCheManager caCheManager = CaCheManager.getInstance();

    /**
     * 手动推送
     * currentMonday 本周周一日期 YYYY-MM-dd
     */
    public String reportRun(String currentMonday) throws Exception{
        if(StringUtils.isNotBlank(currentMonday)){
            reportSourceRun(currentMonday);
            return "推送成功";
        }else {
            return "输入日期不可为空";
        }
    }

    /**
     * 自动生成
     * @throws Exception
     */
    public void reportRun() throws Exception{
        reportSourceRun(DateUtils.currentMonday());
    }
    private void reportSourceRun(String currentMonday) throws Exception{

        TblReportTask tblReportTask = companyService.getTblReportTask(currentMonday);
        if(tblReportTask!=null){
        	String filePath = httpfilerootReport;
//        final ExecutorService executorService = Executors.newFixedThreadPool(3);
//        Thread.sleep(10);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
        	//生成任务单
            reportProductionRun(filePath+"production/"+ currentMonday +"/"+ tblReportTask.getId()+"/",currentMonday,tblReportTask.getId());
            //签收单
            reportReceiptRun(filePath+"receipt/"+ currentMonday+"/"+ tblReportTask.getId()+"/",currentMonday,tblReportTask.getId());
            //产品快递发运单
            reportExpressRun(filePath+"express/"+ currentMonday+"/"+ tblReportTask.getId()+"/",currentMonday,tblReportTask.getId());
            //变更报表任务状态
            tblReportTask.setTaskStatus(1);
            companyService.putTblReportTask(tblReportTask,null);
        } else {
            _logger.error("=========报表任务数据为空=======");
        }


//            }
//        });
//        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()+"========还剩线程数");
//        shutDownAndAwaitTermination(executorService);
//        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()+"========空余线程数");
    }

    //生成产品快递发运单
    public void reportExpressRun(final String filePath,final String strCurrentMonday,final String taskId) throws Exception {
        final String excelId = BaseDomain.FILETYPE_TWO.toString(); //产品快递发运单标识，组装Map使用
        //清除已生成报表数据
        reportService.deleteTblOrderReport(taskId,excelId);
        //清除已生成文件数据
        ZipUtils.deleteDir(filePath);
        final String lastMondayHour = StringUtils.isBlank(strCurrentMonday)?DateUtils.lastMondayHour():DateUtils.lastMondayHour(strCurrentMonday);
        final String lastWeekDayHour = StringUtils.isBlank(strCurrentMonday)?DateUtils.lastWeekDayHour():DateUtils.lastWeekDayHour(strCurrentMonday);
        final String currentMonday = StringUtils.isBlank(strCurrentMonday) ? DateUtils.currentMonday() : DateUtils.currentMonday(strCurrentMonday);
        System.out.println(Thread.currentThread().getName()+"开始");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            Thread.sleep(10);
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println(Thread.currentThread().getName()+"快递单");
                        List<TemPlateExcelVo> temPlateExcelVos = reportService.exportExcel(excelId,null,lastMondayHour,lastWeekDayHour);
                        Map map = excelTemplateService.setTemplateSystemExcelDownload(excelId);
                        String fileTitle = (String) map.get("fileTitle");
                        List<String> title = (List<String>) map.get("excelTitle");
                        List<String> excelData = (List<String>) map.get("excelData");
                        //文件名
                        String fileName = fileTitle + ".xls";

//                        //生成报表文件查询是否存在
//                        TblOrderReport tblOrderReport = reportService.getTblOrderReportByProductListIdStartTime(null,fileTitle,DateUtils.lastMonday(strCurrentMonday),excelId);
//                        if(tblOrderReport==null){ //为空做新增处理
                        TblOrderReport tblOrderReport = new TblOrderReport();
                        tblOrderReport.setProductListId(null);
                        tblOrderReport.setTaskId(taskId);
                        tblOrderReport.setName(fileTitle);
                        tblOrderReport.setFileName(fileName);
                        tblOrderReport.setBuilderTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.currentMonday(strCurrentMonday)));
                        tblOrderReport.setStartTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.lastMonday(strCurrentMonday)));
                        tblOrderReport.setEndTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.lastWeekDay(strCurrentMonday)));
                        tblOrderReport.setFileStatus(BaseDomain.FILESTATUS_ZERO);
                        tblOrderReport.setFileType(BaseDomain.FILETYPE_TWO);
                        tblOrderReport.setFileServerName(filePath);
                        reportService.postTblOrderReport(tblOrderReport);
//                        }
                        ExportUtil.exportExcel(excelId,fileTitle, title, temPlateExcelVos,filePath,fileName,excelData,"yyyy-MM-dd");
                        //生成完成更改状态为已完成
                        tblOrderReport.setFileStatus(BaseDomain.FILESTATUS_ONE);
                        reportService.putTblOrderReport(tblOrderReport);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shutDownAndAwaitTermination(executorService);
            //清除缓存
            caCheManager.removeAll("ehcache.getOrderExpressType");
        }

    }

    /**
     * 生成签收单
     * @param filePath 文件路径
     * @param strCurrentMonday 当前星期一日期
     * @param taskId 任务id
     * @throws Exception
     */
    public void reportReceiptRun(final String filePath,final String strCurrentMonday,final String taskId) throws Exception {
    	final String fileType = "1"; //签收单生成类型
        //清除已生成报表数据
        reportService.deleteTblOrderReport(taskId,fileType);
        //清除已生成文件数据
        ZipUtils.deleteDir(filePath);

        final String lastMondayHour = StringUtils.isBlank(strCurrentMonday)?DateUtils.lastMondayHour():DateUtils.lastMondayHour(strCurrentMonday);
        final String lastWeekDayHour = StringUtils.isBlank(strCurrentMonday)?DateUtils.lastWeekDayHour():DateUtils.lastWeekDayHour(strCurrentMonday);
        final String currentMonday = StringUtils.isBlank(strCurrentMonday)?DateUtils.currentMonday() : DateUtils.currentMonday(strCurrentMonday);

        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 获取产品种类集合
        List<TblProductList> tblProductLists = commonService.getTblProductList();
        try {
            for(final TblProductList tblProductList:tblProductLists){
                Thread.sleep(10);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(Thread.currentThread().getName()+"签收单"+","+tblProductList.getId()+","+tblProductList.getProductName());
                            //取生产任务单查询所有数据不以产品区分
                            List<TemPlateExcelVo> temPlateExcelVos = reportService.getReportProduction(lastMondayHour,lastWeekDayHour,tblProductList.getId());
                            if(temPlateExcelVos.size()!=0){
                                //组装生产任务单号（取固定数值）
//                                templateService.paramTemPlateExcelProductionId(temPlateExcelVos);
                                //生成录入签收单
                                for(TemPlateExcelVo tem:temPlateExcelVos){
                                    //生成word组装条件
                                    Map dataMap = new HashMap<>();
                                    dataMap.put("productionFlowId",tem.getProductionFlowId());
                                    dataMap.put("productName",tem.getProductName());
                                    dataMap.put("productTotalCount",tem.getProductTotalCount());
                                    dataMap.put("receiveUnitName",tem.getReceiveUnitName());
                                    //签收单-发货日期为本周五
                                    dataMap.put("receiptDate",DateUtils.currentFriDay(strCurrentMonday));
                                    String path = filePath + tblProductList.getProductName()+"/";
                                    String fileTitle = tem.getProductName()+tem.getProductionFlowId();
                                    String fileName = tem.getProductName()+tem.getProductionFlowId()+".doc";

//                                    //生成报表文件前查询是否存在
//                                    TblOrderReport tblOrderReport = reportService.getTblOrderReportByProductListIdStartTime(tblProductList.getId(),fileTitle,DateUtils.lastMonday(currentMonday),fileType);
//                                    if(tblOrderReport==null){ //为空做新增处理
                                    TblOrderReport tblOrderReport = new TblOrderReport();
                                    tblOrderReport.setProductListId(tblProductList.getId());
                                    tblOrderReport.setTaskId(taskId);
                                    tblOrderReport.setName(fileTitle);
                                    tblOrderReport.setFileName(fileName);
                                    tblOrderReport.setBuilderTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.currentMonday(currentMonday)));
                                    tblOrderReport.setStartTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.lastMonday(currentMonday)));
                                    tblOrderReport.setEndTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.lastWeekDay(currentMonday)));
                                    tblOrderReport.setFileStatus(BaseDomain.FILESTATUS_ZERO);
                                    tblOrderReport.setFileType(BaseDomain.FILETYPE_ONE);
                                    tblOrderReport.setFileServerName(path);
                                    reportService.postTblOrderReport(tblOrderReport);
//                                    }
                                    //生成
                                    WordUtil.createDoc(dataMap, this, path,fileName, "receipt.xml");
                                    //生成完成更改状态为已完成
                                    tblOrderReport.setFileStatus(BaseDomain.FILESTATUS_ONE);
                                    reportService.putTblOrderReport(tblOrderReport);

                                }

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shutDownAndAwaitTermination(executorService);
            //清除缓存
            caCheManager.removeAll("ehcache.getOrderInvoiceNum");
        }
        long end = System.currentTimeMillis();
        _logger.debug("系统报表生成文件,定时任务结束。耗时：" + (end - start) +" ms");
    }

    //生成生产任务单
    public void reportProductionRun(final String filePath, final String strCurrentMonday,final String taskId) throws Exception{
        final String excelId = "0"; //生产任务单标识，组装Map使用
        //清除已生成报表数据
        reportService.deleteTblOrderReport(taskId,excelId);
        //清除已生成文件数据
        ZipUtils.deleteDir(filePath);
//        Thread.sleep(10);
        final String lastMondayHour = StringUtils.isBlank(strCurrentMonday)?DateUtils.lastMondayHour():DateUtils.lastMondayHour(strCurrentMonday);
        final String lastWeekDayHour = StringUtils.isBlank(strCurrentMonday)?DateUtils.lastWeekDayHour():DateUtils.lastWeekDayHour(strCurrentMonday);
        final String currentMonday = StringUtils.isBlank(strCurrentMonday)?DateUtils.currentMonday() : DateUtils.currentMonday(strCurrentMonday);
        System.out.println(Thread.currentThread().getName()+"开始");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        List<TblProductList> tblProductLists = commonService.getTblProductList();
        try {
            for(final TblProductList tblProductList:tblProductLists){
                Thread.sleep(10);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(Thread.currentThread().getName()+"生产"+","+tblProductList.getId()+","+tblProductList.getProductName());
                            List<TemPlateExcelVo> temPlateExcelVos = reportService.exportExcel(excelId,tblProductList.getId(),lastMondayHour,lastWeekDayHour);
                            Map map = excelTemplateService.setTemplateSystemExcelDownload(excelId);
                            String fileTitle = (String) map.get("fileTitle") + tblProductList.getProductName();
                            List<String> title = (List<String>) map.get("excelTitle");
                            List<String> excelData = (List<String>) map.get("excelData");
                            //文件名
                            String fileName = fileTitle + ".xls";

                            //生成报表文件查询是否存在
//                            TblOrderReport tblOrderReport = reportService.getTblOrderReportByProductListIdStartTime(tblProductList.getId(),fileTitle,DateUtils.lastMonday(strCurrentMonday),excelId);
//                            if(tblOrderReport==null){ //为空做新增处理
                            TblOrderReport tblOrderReport = new TblOrderReport();
                            tblOrderReport.setProductListId(tblProductList.getId());
                            tblOrderReport.setTaskId(taskId);
                            tblOrderReport.setName(fileTitle);
                            tblOrderReport.setFileName(fileName);
                            tblOrderReport.setBuilderTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.currentMonday(strCurrentMonday)));
                            tblOrderReport.setStartTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.lastMonday(strCurrentMonday)));
                            tblOrderReport.setEndTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.lastWeekDay(strCurrentMonday)));
                            tblOrderReport.setFileStatus(BaseDomain.FILESTATUS_ZERO);
                            tblOrderReport.setFileType(BaseDomain.FILETYPE_ZERO);
                            tblOrderReport.setFileServerName(filePath);
                            reportService.postTblOrderReport(tblOrderReport);
//                            }
                            ExportUtil.exportExcel(excelId, fileTitle, title, temPlateExcelVos, 
                            		filePath, fileName, excelData, "yyyy-MM-dd");
                            //生成完成更改状态为已完成
                            tblOrderReport.setFileStatus(BaseDomain.FILESTATUS_ONE);
                            reportService.putTblOrderReport(tblOrderReport);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shutDownAndAwaitTermination(executorService);
            //清除缓存
            caCheManager.removeAll("ehcache.getOrderInvoiceNum");
        }
    }

    static void shutDownAndAwaitTermination(ExecutorService pool){
        pool.shutdown(); //防止新任务提交
        try{
            if(!pool.awaitTermination(60, TimeUnit.SECONDS)){
                pool.shutdownNow();
                if(!pool.awaitTermination(60,TimeUnit.SECONDS)){
                    _logger.error("Pool did not terminate");
                }
            }
        }catch (InterruptedException e){
            //线程被打断，重新发起请求
            pool.shutdownNow();
            //保持中断
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        String titleString = "";
        String titleTrim = null;
        titleTrim = titleString.substring(0,(titleString.lastIndexOf("\r\n")+1)).trim();

//        String titleTrim = titleString.substring(0,(titleString.lastIndexOf("\r\n")+1)).trim();
//        reportRun();
//        for(int i = 0 ; i<5;i++){
//            System.out.println(Thread.currentThread().getName()+",");
//            try {
//                Thread.sleep(10);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

}
