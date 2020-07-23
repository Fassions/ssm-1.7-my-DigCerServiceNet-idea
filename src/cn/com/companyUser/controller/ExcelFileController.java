package cn.com.companyUser.controller;

import cn.com.common.agent.BaseDomain;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.utils.ExportUtil;
import cn.com.common.utils.IntegerUtil;
import cn.com.companyUser.service.ExcelTemplateService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Horace.zhang on 2019/7/17.
 */
@Controller
@RequestMapping("excelFile")
public class ExcelFileController {

    public static final Logger logger = LoggerFactory.getLogger(ExcelFileController.class);

    @Autowired
    ExcelTemplateService excelTemplateService;

    //模板管理模板下载
    @RequestMapping(value = "/downloadExcelTemplate", method = {RequestMethod.GET})
    public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tblTemplateExcelId = request.getParameter("id");
        if(StringUtils.isBlank(tblTemplateExcelId)){
            logger.error("模板下载 tblTemplateExcelId 为null");
        }
        Map map = excelTemplateService.setTemplateExcelDownload(tblTemplateExcelId);
        String fileTitle = (String) map.get("fileTitle");
        List<String> title = (List<String>) map.get("excelTitle");
        List<String> excelData = (List<String>) map.get("excelData");
        ExportUtil.exportExcel(fileTitle, title, null,excelData,  response,"yyyy-MM-dd");


    }
    //模板管理系统模板下载
    @RequestMapping(value = "/downloadSystemExcelTemplate", method = {RequestMethod.GET})
    public void downloadSystemExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String examType = request.getParameter("examType");
        Map map = excelTemplateService.setTemplateSystemExcelDownload(examType);
        String fileTitle = (String) map.get("fileTitle");
        List<String> title = (List<String>) map.get("excelTitle");
        List<String> excelData = (List<String>) map.get("excelData");
        ExportUtil.exportExcel(fileTitle, title, null,excelData,  response,"yyyy-MM-dd");
    }

    /**
     *     根据订单id导出定义模板
     * templateExcelId 0 生产任务单 1 财务开票单 2 快递单 or templateExcelId
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcelTemplate", method = {RequestMethod.GET},produces = "application/json;charset=utf-8")
    public void exportExcelTemplate(OrderQuerySearcher orderQuerySearcher,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String exportType = request.getParameter("exportType");
        String templateExcelId = request.getParameter("templateExcelId");
        String[] orderId = request.getParameter("orderId").split(";");
        Map map = null;
        map = excelTemplateService.exportExcelTemplate(exportType,orderId,templateExcelId,orderQuerySearcher);
        String fileTitle = (String) map.get("fileTitle");
        List<String> title = (List<String>) map.get("excelTitle");
        List<String> excelData = (List<String>) map.get("excelData");
        List<TemPlateExcelVo> temPlateExcelVos = (List<TemPlateExcelVo>) map.get("temPlateExcelVos");
        //ExportUtil.exportExcel(fileTitle, title, temPlateExcelVos,excelData,  response,"yyyy-MM-dd");
        ExportUtil.exportExcel(templateExcelId,fileTitle, title, temPlateExcelVos,excelData,  response,"yyyy-MM-dd");
    }


}
