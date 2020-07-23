package cn.com.companyUser.service;

import cn.com.common.model.TblOrderReport;
import cn.com.common.model.dto.TemPlateExcelVo;
import org.aspectj.weaver.patterns.TypeVariablePatternList;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Horace.zhang on 2019/12/9.
 */
public interface ReportService {

    //可拓展报表下载，和导出导入
    List<TemPlateExcelVo> exportExcel(String templateExcelId,String productListId,String startDayHour,String endDayHour) throws Exception;

    //获取生产任务单符合条件数据
    List<TemPlateExcelVo> getReportProduction(String startDateHour, String endDateHour,String productListId) throws Exception;

    void downLoadReportExcel(String buliderTime, String[] productListIds,String fileType,String zipName, HttpServletResponse response) throws Exception;

    //生产任务单报表 点击下载更改订单状态，记录流转记录
    void putOrderStatusAndHistory(String startDate,String endDate,String userId) throws Exception;

    /**
     * 查询报表文件信息
     * @param productListId
     * @param name  文件名称，存在录入
     * @param startTime 生成文件时间以天为单位
     * @param fileType
     * @return
     * @throws Exception
     */
    TblOrderReport getTblOrderReportByProductListIdStartTime(String productListId,String name,String startTime,String fileType) throws Exception;

    TblOrderReport getTblOrderReportByProductListIdStartTime(String[] productListId,String builderTime,String fileType) throws Exception;

    TblOrderReport getTblOrderReport(String[] productListId,String fileType) throws Exception;

    TblOrderReport getTblOrderReport(String productListId,String startTime,String fileType,Integer fileStatus) throws Exception;

    List<TblOrderReport> getTblOrderReportArray(String productListId,String startTime,String fileType,Integer fileStatus) throws Exception;

    void postTblOrderReport(TblOrderReport orderReport) throws Exception;

    void postTblOrderReport(List<TblOrderReport> orderReport) throws Exception;

    void putTblOrderReport(TblOrderReport orderReport) throws Exception;

    void putTblOrderReport(List<TblOrderReport> orderReport) throws Exception;

    void deleteTblOrderReport(String taskId,String fileType) throws Exception;
}
