package cn.com.companyUser.controller;

import cn.com.common.agent.BaseDomain;
import cn.com.common.model.TblOrderReport;
import cn.com.common.model.dto.GaUser;
import cn.com.common.utils.*;
import cn.com.companyUser.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Objects;

/**
 * Created by Horace.zhang on 2019/12/6.
 */
@Controller
@RequestMapping("report")
public class ReportController {

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReportController.class);

    private static final ObjectMapper mapper = new ObjectMapper();
    private static  final ResultMessage message = new ResultMessage();
    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET,value = "/getReportDownload")
    public ModelAndView getReportDownload(HttpServletRequest request) throws Exception {
        return new ModelAndView("CompanyUser/importexport/reportDownload");
    }

    //查询报表文件数据是否存在
    @RequestMapping(method = RequestMethod.POST,value = "/getOrderReport",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getOrderReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String startDate = request.getParameter("startDate");
        String[] productListIds = request.getParameterValues("productListId");
        String fileType = request.getParameter("fileType");  //文件类型
        if (StringUtils.isBlank(startDate)) {
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("选择日期为空请重新选择");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(fileType)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择");
            return mapper.writeValueAsString(message);
        }
        if(!Objects.equals(fileType,BaseDomain.FILETYPE_TWO.toString())){  //快递发运单没有产品类型
            if(productListIds==null){  //TODO:未选择产品，选择公安数字证书验证
                productListIds = CommonUtils.getProductListId();
            }
        }

        //验证builderTime
        TblOrderReport tblOrderReport = reportService.getTblOrderReportByProductListIdStartTime(productListIds,startDate,fileType);
        if(tblOrderReport==null){ //报表数据不存在,查最近一条有效数据返回页面
            tblOrderReport = reportService.getTblOrderReport(productListIds,fileType);
            if(tblOrderReport==null){ //最新生成也为空
                message.setErrorMsg("您选择日期未生成数据，请重新选择。");
            }else if(tblOrderReport.getBuilderTime()==null){
                message.setErrorMsg("您选择日期未生成数据，请重新选择。");
            }else {
                message.setErrorMsg("您选择日期未生成数据，最近一条数据日期为："+DateUtils.getDateYYYYMMDD(tblOrderReport.getBuilderTime())+" 请重新选择。");
            }
            message.setResult(ResultMessage.Result.ERROR);
            return mapper.writeValueAsString(message);
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    /**
     * 下载报表
     *  打包压缩包--删除
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/downLoadReportExcel")
    public void downLoadReportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String buliderTime = request.getParameter("startDate");
        //选取时间上周一 00:00:00
        String lastMonday = DateUtils.initHour(DateUtils.lastMonday(DateUtils.parseStringByPatternYYYYMMDD(buliderTime)));
        //选取时间上周日 23:59:59
        String endDate = DateUtils.endHour(DateUtils.addSixDay(buliderTime));
        String[] productListIds = request.getParameterValues("productListId");
        String fileType = request.getParameter("fileType");
        if (StringUtils.isBlank(buliderTime)) {
            logger.error("downLoadOrderFile ------------- startDate is null");
            return;
        }
        if(Objects.equals(IntegerUtil.parseInt(fileType), BaseDomain.FILESTATUS_ZERO)){ //下载生产任务单
            String zipName = "生产任务单"+"_"+DateUtils.currentMonday();
            reportService.downLoadReportExcel(buliderTime,productListIds,fileType,zipName,response);
            //订单状态及流转记录变更  需要取上周日期变更订单状态

            reportService.putOrderStatusAndHistory(lastMonday,endDate ,gauser.getUserId());
        }else if(Objects.equals(IntegerUtil.parseInt(fileType),BaseDomain.FILESTATUS_ONE)){ //下载签收单
            String zipName = "签收单"+"_"+DateUtils.currentMonday();
            reportService.downLoadReportExcel(buliderTime,productListIds,fileType,zipName,response);
        }else if(Objects.equals(IntegerUtil.parseInt(fileType),BaseDomain.FILETYPE_TWO)){ //下载产品快递发运单
            String zipName = "产品快递发运单"+"_"+DateUtils.currentMonday();
            reportService.downLoadReportExcel(buliderTime,productListIds,fileType,zipName,response);
        }

    }
}
