package cn.com.companyUser.service;

import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.imports.ImportPaidExcel;
import cn.com.common.utils.ResultMessage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Horace.zhang on 2019/7/23.
 */
public interface ImportExportService {
    //导出系统表
    List<TemPlateExcelVo> exportExcel(String templateExcelId,String year) throws Exception;

    //导入到款信息
    ResultMessage convertPaidExcelDetailData(String userId,ResultMessage message, MultipartFile file,HttpServletRequest request) throws Exception;

    //导入开票日期
    ResultMessage convertInvoiceExcelDetailData(String userId,ResultMessage message, MultipartFile file,HttpServletRequest request) throws Exception;

    //导入发运快递信息
    ResultMessage convertExpressExcelDetailData(String userId,ResultMessage message, MultipartFile file,HttpServletRequest request) throws Exception;

    //导入开票发运快递信息
    ResultMessage convertInvoiceExpressExcelDetailData(String userId,ResultMessage message, MultipartFile file,HttpServletRequest request) throws Exception;

}
