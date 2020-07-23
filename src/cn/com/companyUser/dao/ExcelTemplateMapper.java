package cn.com.companyUser.dao;

import cn.com.common.model.TblTemplateExcel;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.search.TblTemplateExcelSearcher;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/16.
 */
public interface ExcelTemplateMapper {

    List<TblTemplateExcelSearcher> listTblTemplateExcelWithPage(TblTemplateExcelSearcher TblTemplateExcelSearcher);

    List<TblTemplateExcel> getTblTemPlateExcel(Map map);

    void postTblTemplateExcel(TblTemplateExcel tblTblTemplateExcel);

    void putTblTemplateExcel(TblTemplateExcel tblTemplateExcel);

    void deleteTblTemplateExcel(String tblTemplateExcelId);

    //根据订单id导出自定义模板字段查询
    List<TemPlateExcelVo> getExportTemplateExcelByOrderId(String[] orderId);
    //根据订单id导出产品快递发运单
    List<TemPlateExcelVo> getExcpressExcelByOrderId(String[] orderId);
    //根据订到id导出财务开票表
    List<TemPlateExcelVo> getInvoiceExcelByOrderId(String[] orderId);


}
