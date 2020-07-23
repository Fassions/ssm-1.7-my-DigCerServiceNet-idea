package cn.com.companyUser.dao;

import cn.com.common.model.dto.TemPlateExcelVo;

import java.util.List;

/**
 * Created by Horace.zhang on 2019/7/23.
 */
public interface ImportExportMapper {
    //导出快递单
    List<TemPlateExcelVo> exportExpressExcel();
    //导出财务开票表
    List<TemPlateExcelVo> exportInvoiceExcel();
    //导出生产任务单
    List<TemPlateExcelVo> exportProductionExcel();
    //根据年份导出订购单管理表
    List<TemPlateExcelVo> exportOrderManageExcel(String date);
}
