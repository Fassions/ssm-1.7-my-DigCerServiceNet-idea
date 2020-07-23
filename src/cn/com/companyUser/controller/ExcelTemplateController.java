package cn.com.companyUser.controller;

import cn.com.common.model.OrderMemo;
import cn.com.common.model.TblAreaInfo;
import cn.com.common.model.TblTemplateExcel;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.search.OrderMemoSearcher;
import cn.com.common.model.search.TblTemplateExcelSearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.common.utils.ResultMessage;
import cn.com.companyUser.service.ExcelTemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/16.
 */
@Controller
@RequestMapping("excelTemplate")
public class ExcelTemplateController {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static  final ResultMessage message = new ResultMessage();

    public static final Logger logger = LoggerFactory.getLogger(ExcelTemplateController.class);

    @Autowired
    ExcelTemplateService excelTemplateService;

    @RequestMapping(method = RequestMethod.GET,value = "/getTemplateIndex")
    public ModelAndView chooseInvoice(HttpServletRequest request) throws Exception {
        return new ModelAndView("CompanyUser/exceltemplate/templateIndex");
    }
    @RequestMapping(method = RequestMethod.GET,value = "/createTemplate")
    public ModelAndView createTemplate(HttpServletRequest request) throws Exception {
        request.setAttribute("status","save");
        return new ModelAndView("CompanyUser/exceltemplate/detailsTemplate");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/editTemplate")
    public ModelAndView editTemplate(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        if(StringUtils.isBlank(id)){
            logger.error("模板管理 跳转编辑模板 templateExcel id为 null");
        }
        TblTemplateExcel tblTemplateExcel = excelTemplateService.getTblTemPlateExcelById(id);
        request.setAttribute("status","edit");
        request.setAttribute("templateExcel",mapper.writeValueAsString(tblTemplateExcel));
        return new ModelAndView("CompanyUser/exceltemplate/detailsTemplate");
    }


    @RequestMapping(method = RequestMethod.POST,value = "/showTblTemplateExcelPage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showTblTemplateExcelPage(TblTemplateExcelSearcher TblTemplateExcelSearcher, HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            logger.error("模板管理 gaUser为 null");
        }
        TblTemplateExcelSearcher.setUserId(gauser.getUserId());
        Page<TblTemplateExcelSearcher> page = excelTemplateService.listTblTemplateExcelWithPage(TblTemplateExcelSearcher, new Limit(TblTemplateExcelSearcher.getOffset(), TblTemplateExcelSearcher.getPageCount()));
        return mapper.writeValueAsString(page);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/postTblTemplateExcel",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postTblTemplateExcel(TblTemplateExcel tblTemplateExcel,HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }
        tblTemplateExcel.setUserId(gauser.getUserId());
        Map map = new HashMap<>();
        map.put("userId",tblTemplateExcel.getUserId());
        map.put("templateName",tblTemplateExcel.getTemplateName());
        List<TblTemplateExcel> tblTemplateExcels = excelTemplateService.getTblTemPlateExcel(map);
        if(tblTemplateExcels!=null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("自定义模板名称不能重复。");
            return mapper.writeValueAsString(message);
        }

        excelTemplateService.postTblTemplateExcel(tblTemplateExcel);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/putTblTemplateExcel",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putTblTemplateExcel(TblTemplateExcel tblTemplateExcel,HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }
        tblTemplateExcel.setUserId(gauser.getUserId());
        excelTemplateService.putTblTemplateExcel(tblTemplateExcel);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/deleteTblTemplateExcel",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteTblTemplateExcel(HttpServletRequest request) throws Exception {
        String tblTemplateExcelId = request.getParameter("id");
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(tblTemplateExcelId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请选择一项模板。");
            return mapper.writeValueAsString(message);
        }
        for(String id : tblTemplateExcelId.split(";")){
            excelTemplateService.deleteTblTemplateExcel(id);
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }






}
