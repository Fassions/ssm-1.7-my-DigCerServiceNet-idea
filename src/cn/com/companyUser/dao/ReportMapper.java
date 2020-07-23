package cn.com.companyUser.dao;

import cn.com.common.model.TblOrderReport;
import cn.com.common.model.dto.TemPlateExcelVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/12/9.
 */
public interface ReportMapper {
    //报表生成产品快递单
    List<TemPlateExcelVo> reportExpressExcel(Map map);
    //生成开票快递单
    List<TemPlateExcelVo> reportInvoiceExpressExcel(Map map);
    //报表生产生产任务单待生产数据查询
    List<TemPlateExcelVo> getReportProduction(Map map);

    List<TblOrderReport> getTblOrderReport(Map map);

    void postTblOrderReport(TblOrderReport tblOrderReport);

    void putTblOrderReport(TblOrderReport tblOrderReport);

    void deleteTblOrderReport(Map  map);
}
