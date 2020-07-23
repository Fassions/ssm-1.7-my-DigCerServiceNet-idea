package cn.com.common.controller;

import cn.com.common.cache.CaCheManager;
import cn.com.common.model.TblAreaInfo;
import cn.com.common.model.TblAreaLabelPrefix;
import cn.com.common.model.TblAreaLabelSuffix;
import cn.com.common.model.TblReportTask;
import cn.com.common.service.CommonService;
import cn.com.common.task.ExecutorReport;
import cn.com.common.utils.DateUtils;
import cn.com.companyUser.dao.OrderCompanyMapper;
import cn.com.companyUser.service.OrderQueryCompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.sun.xml.rpc.processor.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

/**
 * Created by Horace.zhang on 2019/6/10.
 */
@Controller
@RequestMapping("common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private ExecutorReport executorReport;

    @Autowired
    private OrderQueryCompanyService companyService;

    private CaCheManager caCheManager = CaCheManager.getInstance();


    private static final ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(method = RequestMethod.GET,value = "/showCity",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showOrderProcess(HttpServletRequest request) throws Exception {
        String areaProviceId = request.getParameter("areaProviceId");
        List<TblAreaInfo> city = commonService.getTblAreaInfoByParentId(areaProviceId);
        return mapper.writeValueAsString(city);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/showPrefix",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showCityPrefix(HttpServletRequest request) throws Exception {
        String areaProviceId = request.getParameter("areaProviceId");
        TblAreaLabelPrefix tblAreaLabelPrefix= commonService.getTblProductPreffixByAreaId(areaProviceId);
        return mapper.writeValueAsString(tblAreaLabelPrefix);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/showTblAreaLabelSuffix",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showTblAreaLabelSuffix(HttpServletRequest request) throws Exception {
        String productListId = request.getParameter("productListId");
        String tblAreaLabelPrefixId = request.getParameter("tblAreaLabelPrefixId");
        List<TblAreaLabelSuffix> tblAreaLabelPrefix= commonService.getTblAreaLabelSuffixByProductListIdAndLabelPreffix(productListId,tblAreaLabelPrefixId);
        return mapper.writeValueAsString(tblAreaLabelPrefix);
    }

    //查询缓存
    @RequestMapping(method = RequestMethod.GET,value = "/getEhcache",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getEhcache(HttpServletRequest request)throws Exception{
        String ehcacheName = request.getParameter("ehcacheName");
        String ehcacheValue = request.getParameter("ehcacheValue");
        Object object = caCheManager.get(ehcacheName,ehcacheValue);
        if(object==null){
            return mapper.writeValueAsString("不存在");
        }else {
            return mapper.writeValueAsString(object);
        }
    }

    //手动推报表
    @RequestMapping(method = RequestMethod.GET,value = "/pushReport",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String pushReport(HttpServletRequest request) throws Exception{

        String currentMonday = request.getParameter("currentMonday");
        if(org.apache.commons.lang3.StringUtils.isBlank(currentMonday)){
            return mapper.writeValueAsString("推送日期不能为空，为需要推送周一日期");
        }else {
            String resource = executorReport.reportRun(currentMonday);
            return mapper.writeValueAsString(resource);
        }
    }



    //清除缓存
    @RequestMapping(method = RequestMethod.GET,value = "/removeEhcache",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String removeEhcache(HttpServletRequest request)throws Exception{
        String ehcacheName = request.getParameter("ehcacheName");
        String ehcacheValue = request.getParameter("ehcacheValue");
        Object object = caCheManager.get(ehcacheName);
        if(object==null){
            return mapper.writeValueAsString("不存在");
        }else {
            //清除缓存
            caCheManager.removeAll(ehcacheName);
            return mapper.writeValueAsString(caCheManager.get(ehcacheName,ehcacheValue));
        }
    }

}
