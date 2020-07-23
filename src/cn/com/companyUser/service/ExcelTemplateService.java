package cn.com.companyUser.service;

import cn.com.common.model.OrderProduct;
import cn.com.common.model.TblTemplateExcel;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.model.search.TblTemplateExcelSearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.companyUser.dao.ExcelTemplateMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/16.
 */
public interface ExcelTemplateService {
    Page<TblTemplateExcelSearcher> listTblTemplateExcelWithPage(TblTemplateExcelSearcher TblTemplateExcelSearcher,Limit limit) throws Exception;

    //导出自定义模板字段查询
    List<TemPlateExcelVo> getExportTemplateExcelByOrderId(String[] orderId,String templateExcelId) throws Exception;
    //导出系统模板字段查询
    List<TemPlateExcelVo> getExportTemplateExcelSystemByOrderId(String[] orderId,String templateExcelId) throws Exception;
    //组装导出需要的其他字段
    void paramTemPlateExcel(List<TemPlateExcelVo> temPlateExcelVos,String templateExcelId) throws Exception;



    //自定义模板 导出 需要的字段转换
    Map setTemplateExcelDownload(String tblTemplateExcelId) throws Exception;

//    查询界面导出表格
    Map exportExcelTemplate(String exportType,String[] orderId,String templateExcelId,OrderQuerySearcher orderQuerySearcher) throws Exception;

    //获取orderId
    String[] arrayOrderIdByOrderQuerySearcher(OrderQuerySearcher querySearchers) throws Exception;

    //系统三张模板表 导出 需要的字段转换 0生产任务单 1.财务开票表 2.任务单
    Map setTemplateSystemExcelDownload(String excelType) throws Exception;

    TblTemplateExcel getTblTemPlateExcelById(String id) throws Exception;

    List<TblTemplateExcel> getTblTemPlateExcelByUserId(String userId) throws Exception;

    List<TblTemplateExcel> getTblTemPlateExcel(Map map) throws Exception;

    void postTblTemplateExcel(TblTemplateExcel tblTblTemplateExcel) throws Exception;

    void putTblTemplateExcel(TblTemplateExcel tblTemplateExcel) throws Exception;

    void deleteTblTemplateExcel(String tblTemplateExcelId) throws Exception;
}
