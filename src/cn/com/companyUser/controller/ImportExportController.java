package cn.com.companyUser.controller;

import cn.com.common.CommonProperty;
import cn.com.common.agent.BaseDomain;
import cn.com.common.model.Order;
import cn.com.common.model.OrderPaid;
import cn.com.common.model.TblOrderReport;
import cn.com.common.model.TblProductList;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.imports.ConstantMsg;
import cn.com.common.model.imports.ImportPaidExcel;
import cn.com.common.service.CommonService;
import cn.com.common.utils.*;
import cn.com.commonUser.service.OrderUserService;
import cn.com.companyUser.dao.ExcelTemplateMapper;
import cn.com.companyUser.service.ExcelTemplateService;
import cn.com.companyUser.service.ImportExportService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import cn.com.companyUser.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by Horace.zhang on 2019/7/23.
 */
@Controller
@RequestMapping("commonCompanyImportExport")
public class ImportExportController {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static  final ResultMessage message = new ResultMessage();

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(ImportExportController.class);

    @Value("${httpfilerootOrderTemp}")
    private String httpfilerootOrderTemp;

    @Autowired
    private ImportExportService importExportService;
    @Autowired
    private ExcelTemplateService excelTemplateService;
    @Autowired
    private ReportService reportService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private OrderQueryCompanyService companyService;

    @RequestMapping(method = RequestMethod.GET,value = "/getImportExportIndex")
    public ModelAndView getImportExportIndex(HttpServletRequest request) throws Exception {
        return new ModelAndView("CompanyUser/importexport/importExportIndex");
    }

    /**
     *     根据要求 导出系统表
     * templateExcelId 0 生产任务单 2 产品快递单  4 开票快递单
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/modelExportExcel", method = {RequestMethod.GET})
    public void modelExportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String filePath = httpfilerootOrderTemp + gauser.getUserId() + "/";
        String excelId = request.getParameter("excelId");
        String startDate = DateUtils.initHour(request.getParameter("startDate"));
        String endDate = DateUtils.endHour(request.getParameter("endDate"));
        String[] productListIdAndNames = request.getParameterValues("productListId");
        List<File> files = new ArrayList<>();
        String zipName = "";
        if(Objects.equals(excelId,BaseDomain.PRODUCTIONEXCEL)){ //生产任务单
            zipName = "生产任务单"+"_"+DateUtils.currentMonday();
            if(productListIdAndNames==null) { //未选择产品默认全部
                List<TblProductList> tblProductLists = commonService.getTblProductList();
                productListIdAndNames = new String[tblProductLists.size()];
                for(int i = 0 ;i< tblProductLists.size();i++){
                    productListIdAndNames[i] = tblProductLists.get(i).getId()+","+tblProductLists.get(i).getProductName();
                }
            }
            for(String productListIdAndName:productListIdAndNames){
                String productListId = productListIdAndName.split(",")[0];
                String productName = productListIdAndName.split(",")[1];
                getListTemPlateExcelVo(excelId,productName,filePath,productListId,startDate,endDate,files);
            }
        } else if(Objects.equals(excelId,BaseDomain.EXPRESSEXCEL)) { //产品快递发运单
        	zipName = "产品快递发运单"+"_"+DateUtils.currentMonday();
            getListTemPlateExcelVo(excelId,"",filePath,"",startDate,endDate,files);
        } else if(Objects.equals(excelId,BaseDomain.INVOICEEXPRESSEXCEL)) { //开票快递发运单
        	zipName = "开票快递发运单"+"_"+DateUtils.currentMonday();
        	getListTemPlateExcelVo(excelId,"",filePath,"",startDate,endDate,files);
        }

        //下载存入文件
        ZipUtils.downLoadFile(zipName,files,response);
        //删除临时目录
        ZipUtils.deleteDir(filePath);
    }
    //组装导出数据
    private void getListTemPlateExcelVo(String excelId,String productName,String filePath,
                                        String productListId,String startDate,String endDate,
                                        List<File> files) throws Exception{
    	List<TemPlateExcelVo> temPlateExcelVos = reportService.exportExcel(excelId,
    			productListId,startDate,endDate);
        Map map = excelTemplateService.setTemplateSystemExcelDownload(excelId);
        String fileTitle = (String) map.get("fileTitle") + productName;
        List<String> title = (List<String>) map.get("excelTitle");
        List<String> excelData = (List<String>) map.get("excelData");
        //文件名
        String fileName = fileTitle + ".xls";
        //存入临时路径
        files.add(ExportUtil.exportExcel(excelId, fileTitle, title, temPlateExcelVos, 
        		filePath,fileName,excelData,"yyyy-MM-dd"));
    }

    /**
     *     根据要求 导出系统表
     * templateExcelId 1 财务开票单 3.订单管理表
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel", method = {RequestMethod.GET})
    public void exportExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String excelId = request.getParameter("excelId");
        String date = request.getParameter("year");
        if(Objects.equals(BaseDomain.ORDERMANAGEEXCEL, excelId)){  //订购单管理表
            if(StringUtils.isBlank(date)){
                logger.error("导出 订单管理表 year 选择 为 null");
                return;
            }
        }
        List<TemPlateExcelVo> temPlateExcelVos = importExportService.exportExcel(excelId,date);
        Map map = excelTemplateService.setTemplateSystemExcelDownload(excelId);
        String fileTitle = (String) map.get("fileTitle");
        List<String> title = (List<String>) map.get("excelTitle");
        List<String> excelData = (List<String>) map.get("excelData");
        ExportUtil.exportExcel(excelId,fileTitle, title, temPlateExcelVos,excelData,  response,"yyyy-MM-dd");
    }


    @RequestMapping(method = RequestMethod.POST,value = "/importPaid",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String importPaid(@RequestParam(value="uploadFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMessage message = new ResultMessage();
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(file==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("上传文件为空");
            return mapper.writeValueAsString(message);
        }
        return mapper.writeValueAsString(importExportService.convertPaidExcelDetailData(gauser.getUserId(),message,file,request));
    }
    @RequestMapping(method = RequestMethod.POST,value = "/importInvoice",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String importInvoice(@RequestParam(value="uploadFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMessage message = new ResultMessage();
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(file==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("上传文件为空");
            return mapper.writeValueAsString(message);
        }
        return mapper.writeValueAsString(importExportService.convertInvoiceExcelDetailData(gauser.getUserId(),message,file,request));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/importExpress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String importExpress(@RequestParam(value="uploadFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMessage message = new ResultMessage();
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(file==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("上传文件为空");
            return mapper.writeValueAsString(message);
        }
        return mapper.writeValueAsString(importExportService.convertExpressExcelDetailData(gauser.getUserId(),message,file,request));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/importInvoiceExpress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String importInvoiceExpress(@RequestParam(value="uploadFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMessage message = new ResultMessage();
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(file==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("上传文件为空");
            return mapper.writeValueAsString(message);
        }
        return mapper.writeValueAsString(importExportService.convertInvoiceExpressExcelDetailData(gauser.getUserId(),message,file,request));
    }



}
