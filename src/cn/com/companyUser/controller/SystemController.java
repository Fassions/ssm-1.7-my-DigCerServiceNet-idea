package cn.com.companyUser.controller;

import cn.com.common.agent.BaseDomain;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.*;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.model.search.TblOrderUnitSearcher;
import cn.com.common.model.search.TblProductionProductSearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.common.service.CommonService;
import cn.com.common.utils.*;
import cn.com.commonUser.service.UserService;
import cn.com.companyUser.service.SystemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Horace.zhang on 2019/7/30.
 */
@Controller
@RequestMapping("system")
public class SystemController {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static  final ResultMessage message = new ResultMessage();

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    SystemService systemService;

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    @Value("${httpfilerootHome}")
    private String httpfilerootHome;


    @RequestMapping(method = RequestMethod.GET,value = "/getSellProduct")
    public ModelAndView getSellProduct(HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(gauser.getUserId())){
            logger.error("卖家系统管理--已售证书标号 userId 为null");
        }
        List<TblProductList> productLists = commonService.getTblProductList();
        request.setAttribute("productLists",productLists);
        return new ModelAndView("CompanyUser/system/sellProduct");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getUserMamage")
    public ModelAndView getUserMamage(HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(gauser.getUserId())){
            logger.error("卖家系统管理--用户管理 userId 为null");
        }
        return new ModelAndView("CompanyUser/system/userManage");
    }
    @RequestMapping(method = RequestMethod.GET,value = "/getfileMamage")
    public ModelAndView getfileMamage(HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(gauser.getUserId())){
            logger.error("卖家系统管理--文件上传 userId 为null");
        }
        return new ModelAndView("CompanyUser/system/fileManage");
    }
    /**
     * 文件上传--列表展示
     */
    @RequestMapping(method = RequestMethod.POST,value = "/showFileHome",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showFileHome(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        List<TblHome> tblHomes = systemService.getAllTblHome();
        return mapper.writeValueAsString(tblHomes);
    }

    /**
     * 文件上传功能--上传
     */
    @RequestMapping(method = RequestMethod.POST,value = "/uploadHomeFile",produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String uploadHomeFile(TblHome tblHome, HttpServletRequest request) throws Exception {
        String fileName = "";
        String filePath = "";
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(gauser.getUserId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("文件上传失败请重新上传!");
            return mapper.writeValueAsString(message);
        }
//        List<TblHome> values = systemService.getTblHomeByFileLevel(tblHome.getFileLevel());
//        if(values!=null){
//            message.setResult(ResultMessage.Result.ERROR);
//            message.setErrorMsg("请重新选择上传级别，该级别已被使用。");
//            return mapper.writeValueAsString(message);
//        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        Map<String, String> mapPar = new HashMap<String, String>();
        try {
            fileName = (multipartRequest.getFiles(multipartRequest.getFileNames().next())).get(0).getOriginalFilename();
//            String systemPath = request.getSession().getServletContext().getRealPath("/");
            filePath = httpfilerootHome;
            mapPar = UpDownUtil.getUploadFilesReturnMap(request, filePath);
        } catch (Exception e) {
            e.printStackTrace();
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("文件上传失败或者您没有上传文件!");
            return mapper.writeValueAsString(message);
        }
        if (mapPar.size() == 0) {
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请上传文件!");
            return mapper.writeValueAsString(message);
        }
        tblHome.setName(StringUtils.substringBeforeLast(fileName,"."));
        tblHome.setFileName(fileName);
        tblHome.setFileServerName(filePath);
        tblHome.setFileLevel(TblHomeFileLevelUtil.getHomeFileLevel(tblHome.getFileLevel()));
        systemService.postTblHome(tblHome,gauser.getUserId());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    /**
     * 文件上传---删除文件
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST,value = "/deleteTblHome",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteTblHome(HttpServletRequest request) throws Exception {
        String tblHomeId = request.getParameter("tblHomeId");
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(tblHomeId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请选择一项文件。");
            return mapper.writeValueAsString(message);
        }
        systemService.deleteTblHome(tblHomeId);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    /**
     * 文件上传---编辑文件优先级
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST,value = "/updateTblHome",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateTblHome(HttpServletRequest request,TblHome tblHome) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isAnyBlank(tblHome.getId(),tblHome.getFileLevel())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请选择一项文件。");
            return mapper.writeValueAsString(message);
        }
        tblHome.setFileLevel(TblHomeFileLevelUtil.getHomeFileLevel(tblHome.getFileLevel()));
        systemService.updateTblHomeByLevelAndId(tblHome,gauser.getUserId());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }


    /**
     * 买家用户管理--基本信息搜索
     */
    @RequestMapping(method = RequestMethod.POST,value = "/showUserUnitPage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showUserUnitPage(TblOrderUnitSearcher tblOrderUnitSearcher,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(tblOrderUnitSearcher.getOrderUnitName())
                && StringUtils.isBlank(tblOrderUnitSearcher.getUserName())
                && StringUtils.isBlank(tblOrderUnitSearcher.getCertificateCode())){
            return mapper.writeValueAsString("");
        }
        Page<TblOrderUnitSearcher> page = systemService.listUserUnitWithPage(tblOrderUnitSearcher,gauser, new Limit(tblOrderUnitSearcher.getOffset(), tblOrderUnitSearcher.getPageCount()));
        return mapper.writeValueAsString(page);
    }



    /**
     * 已售证书标号管理--基本信息搜索
     */
    @RequestMapping(method = RequestMethod.POST,value = "/showProductionProductWithPage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showProductionProductWithPage(TblProductionProductSearcher productionProductSearcher,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        Page<TblProductionProductSearcher> page = systemService.listproductionProductWithPage(productionProductSearcher,gauser, new Limit(productionProductSearcher.getOffset(), productionProductSearcher.getPageCount()));
        return mapper.writeValueAsString(page);
    }

    //已售证书标号添加
    @RequestMapping(method = RequestMethod.POST,value = "/postTblProductionProduct",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postTblProductionProduct(OrderVo vo, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isAnyBlank(vo.getLabelNumStart(),vo.getLabelNumEnd(),vo.getLabelPrefix(),vo.getProductListId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("必填字段不可为空。");
            return mapper.writeValueAsString(message);
        }
        ProductUtils.getlabelStartEndNumberAmount(vo);  //拼接需要字段值
        TblProductionProduct product = new TblProductionProduct();

        BeanUtilsEx.copyProperties(vo,product);
        systemService.postTblProductionProduct(product,gauser.getUserId());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }


    /**
     * 买家用户管理--删除订购单位关联
     */
    @RequestMapping(method = RequestMethod.POST,value = "/putUserInfo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String userId = request.getParameter("userId");
        if(StringUtils.isBlank(userId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("用户错误请重新选择");
            return mapper.writeValueAsString(message);
        }
        UserInfo userInfo = userService.getUserInfoByUserId(userId);
        if(userInfo==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("删除的用户不存在，请核对。");
            return mapper.writeValueAsString(message);
        }
        userInfo.setOrderUnitName("");
        userService.putUserInfo(userInfo);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }


    //信息登记提交
    @RequestMapping(method = RequestMethod.POST,value = "/postOrderUnit",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postProduct(UserInfo userInfo, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isAnyBlank(userInfo.getOrderUnitName(),userInfo.getUserName(),userInfo.getCertificateCode())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("必填字段不可为空。");
            return mapper.writeValueAsString(message);
        }
        UserInfo userInfo1 = userService.getUserInfoByUserNameAndCertificate(userInfo.getUserName(),userInfo.getCertificateCode());
        if(userInfo1==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("添加的用户不存在，请先查询核对。");
            return mapper.writeValueAsString(message);
        }
        //添加备注
        userInfo1.setUserMessage(userInfo.getUserMessage());
        TblOrderUnit tblOrderUnit = systemService.getTblOrderUnitByOrderUnitName(userInfo.getOrderUnitName());
        if(tblOrderUnit!=null){
            userInfo1.setOrderUnitName(tblOrderUnit.getId());
            userService.putUserInfo(userInfo1);
        }else{      //订购单位不存在，新增单位记录，更改用户订购单位
            TblOrderUnit orderUnit = new TblOrderUnit();
            orderUnit.setOrderUnitName(userInfo.getOrderUnitName());
            systemService.postTblOrderUnit(orderUnit,gauser.getUserId());
            userInfo1.setOrderUnitName(orderUnit.getId());
            userService.putUserInfo(userInfo1);
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

}
