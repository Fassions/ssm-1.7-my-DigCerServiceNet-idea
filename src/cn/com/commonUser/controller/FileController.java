package cn.com.commonUser.controller;

import cn.com.common.CommonProperty;
import cn.com.common.agent.BaseDomain;
import cn.com.common.model.*;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderInvoiceVo;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.service.CommonService;
import cn.com.common.utils.*;
import cn.com.commonUser.service.OrderUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Horace.zhang on 2019/6/24.
 */
@Controller
@RequestMapping("file")
public class FileController {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static  final ResultMessage message = new ResultMessage();

    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderUserService orderUserService;

    @Value("${httpfilerootOrderTemp}")
    private String httpfilerootOrderTemp;

    @Value("${httpfilerootOrder}")
    private String httpfilerootOrder;
    @Value("${httpfilerootOrderReceipt}")
    private String httpfilerootOrderReceipt;

    @Value("${httpfilerootHome}")
    private String httpfilerootHome;


    public static final Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 订购单下载
     * @param orderVo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST,value = "/downloadOrderInfo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String downloadOrderInfo(OrderVo orderVo, HttpServletRequest request,HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            return mapper.writeValueAsString("未登录");
        }
        File outFile = null;
        String orderId=request.getParameter("orderId");
        if(StringUtils.isNotBlank(orderId)){
            Order order = new Order();
            order.setId(orderId);
            order = orderUserService.getOrder(order);
            if(order==null){
                logger.error("订购单下载，order数据为空"+gauser.getUserId());
                return null;
            }
            Map<String,Object> dataMap=new HashMap<String,Object>();
            dataMap.put("orderUnitName",orderVo.getOrderUnitName());
            dataMap.put("orderUnitAddress",orderVo.getOrderUnitAddress());
            dataMap.put("purchaser",orderVo.getPurchaser());
            if(StringUtils.isNotBlank(orderVo.getPurchaserMobile()) && StringUtils.isNotBlank(orderVo.getPurchaserPhone()))
                dataMap.put("purchaserPhone",orderVo.getPurchaserMobile()+"/"+orderVo.getPurchaserPhone());
            else if(StringUtils.isBlank(orderVo.getPurchaserMobile()) && StringUtils.isNotBlank(orderVo.getPurchaserPhone()))
                dataMap.put("purchaserPhone",orderVo.getPurchaserPhone());
            else if(StringUtils.isNotBlank(orderVo.getPurchaserMobile()) && StringUtils.isBlank(orderVo.getPurchaserPhone()))
                dataMap.put("purchaserPhone",orderVo.getPurchaserMobile());

            //收货地址--取地址管理 设为选定地址
            DeliveryAddress data = new DeliveryAddress();
            data.setUserId(gauser.getUserId());
            data.setIsDefault(BaseDomain.IS_DEFAULT_DONE);
//            DeliveryAddress address = orderUserService.getDeliveryAddress(data)!=null?orderUserService.getDeliveryAddress(data).get(0):null;
//            dataMap.put("receiveUnitName",address.getReceiveUnitName());
//            dataMap.put("receiveUnitAddress",address.getReceiveUnitAddress());
//            dataMap.put("receiveName",address.getReceiveName());
//            dataMap.put("receivePhone",address.getReceiveMobile()+"/"+address.getReceivePhone());
            //获取最新订单页面过来数据
            dataMap.put("receiveUnitName",orderVo.getReceiveUnitName());
            dataMap.put("receiveUnitAddress",orderVo.getReceiveUnitAddress());
            dataMap.put("receiveName",orderVo.getReceiveName());

            if(StringUtils.isNotBlank(orderVo.getReceiveMobile()) && StringUtils.isNotBlank(orderVo.getReceivePhone()))
                dataMap.put("receivePhone",orderVo.getReceiveMobile()+"/"+orderVo.getReceivePhone());
            else if (StringUtils.isBlank(orderVo.getReceiveMobile()) && StringUtils.isNotBlank(orderVo.getReceivePhone()))
                dataMap.put("receivePhone",orderVo.getReceivePhone());
            else if (StringUtils.isNotBlank(orderVo.getReceiveMobile()) && StringUtils.isBlank(orderVo.getReceivePhone()))
                dataMap.put("receivePhone",orderVo.getReceiveMobile());


            if(orderVo.getRemitterType()==0){
                dataMap.put("remitter",orderVo.getRemitter());
            }else{
                dataMap.put("remitter",orderVo.getRemitter());
            }
            List<OrderInvoiceVo> orderInvoiceVos = orderUserService.getOrderInvoiceVo(order.getId());
            List<OrderProduct> orderProducts = orderUserService.getOrderProductByOrderId(orderId);
            //产品总数量
            Integer productSum = 0;
            Double productAmount = 0.00;
            Double productTotalAmount = 0.00;
            //计算产品数量，金额，总金额
            if(orderProducts!=null){
                for(OrderProduct orderProduct: orderProducts){
                    productTotalAmount += orderProduct.getProductAmount();
                    if(!"0".equals(orderProduct.getProductListId())){
                        productSum += orderProduct.getProductNumber();
                        productAmount += orderProduct.getProductAmount();
                    }
                }
            }
            //0.增值税专用发票,1.增值税普通发票
            if(Objects.equals(orderVo.getInvoiceType(), BaseDomain.INVOICE_TYPE_ZERO)){
                dataMap.put("invoiceType","增值税专用发票");
                if(orderUserService.getOrderInvoiceNotSourceNull(orderVo.getOrderId())!=null){ //验证是否符合全部为专用发票条件
                    message.setResult(ResultMessage.Result.ERROR);
                    message.setErrorMsg("您选择为增值税专用发票，开票信息必填项必须填写，请核对。");
                    return mapper.writeValueAsString(message);
                }
            }else if(Objects.equals(orderVo.getInvoiceType(), BaseDomain.INVOICE_TYPE_ONE)){
                dataMap.put("invoiceType","增值税普通发票");
            }

            dataMap.put("remark",orderVo.getRemark());
            dataMap.put("remitterType",orderVo.getRemitterType());
            dataMap.put("orderDate",DateUtils.getDateYYYYMMDD());
            dataMap.put("orderInvoiceVos",orderInvoiceVos);
            dataMap.put("orderProducts",orderProducts);
            dataMap.put("productSum",productSum);
            dataMap.put("productAmount",productAmount);
            dataMap.put("productTotalAmount",productTotalAmount);



//                Calendar cal=Calendar.getInstance();
//                List<CommonOrderNumberInfo> commonOrderNumberInfoList=commonOrderApplyInfoService.getCommonOrderNumberInfoByOrderId(order.getId());
//                dataMap.put("cerList", commonOrderNumberInfoList);
//                dataMap.put("year", String.valueOf(cal.get(Calendar.YEAR)));
//                dataMap.put("month", String.valueOf(cal.get(Calendar.MONTH)+1));
//                dataMap.put("day", String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));


//                String path = CommonProperty.applicationProperties.getProperty("httpfilerootOrderTemp") +"/"+gauser.getUserId();
            String path = httpfilerootOrderTemp +"/"+gauser.getUserId();
            String fileName = "数字证书订购单.doc";
            outFile = WordUtil.createDoc(dataMap, this, path,fileName, "orderModel.xml");
            message.setResult(ResultMessage.Result.SUCCESS);
            message.setErrorMsg(outFile.getPath());
        }else {
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("订单为空");
        }
        return mapper.writeValueAsString(message);
    }

    /**
     * 签收单上传
     */
    @RequestMapping(method = RequestMethod.POST,value = "/uploadReceiptInfo",produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String uploadReceiptInfo(OrderVo orderVo, HttpServletRequest request) throws Exception {

        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String orderId = request.getParameter("orderId");
        if(StringUtils.isBlank(orderId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("签收单上传失败请重新选择订单!");
            return mapper.writeValueAsString(message);
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
            String key = it.next();
            List<MultipartFile> multipartFiles = multipartRequest.getFiles(key);
            for(MultipartFile multipartFile : multipartFiles){
                if (multipartFile != null && StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
                    String fileName = multipartFile.getOriginalFilename();
                    String fileSuffix = StringUtils.substringAfterLast(fileName,".");
                    if(!"jpg".equals(fileSuffix) && !"jpeg".equals(fileSuffix) && !"png".equals(fileSuffix) &&
                            !"gif".equals(fileSuffix) && !"pcx".equals(fileSuffix) && !"bmp".equals(fileSuffix) &&
                            !"zip".equals(fileSuffix) && !"rar".equals(fileSuffix) && !"7z".equals(fileSuffix) ){
                        message.setResult(ResultMessage.Result.ERROR);
                        message.setErrorMsg("上传文件仅支持\n图片格式：jpg,jpeg,png,gif,pcx,bmp。\n压缩包格式：zip,rar,7z。");
                        return mapper.writeValueAsString(message);
                    }
                }
            }
        }
        Map<String, String> mapPar = new HashMap<String, String>();
        try {
            String fileName = CommonUtils.getFilterName(DateUtils.getDateYYYYMMDD());
//            mapPar = UpDownUtil.getUploadFilesReturnMap(request, CommonProperty.applicationProperties.getProperty("httpfilerootOrder")+orderVo.getOrderId()+"/");
            mapPar = UpDownUtil.getUploadFilesReturnMap(request,fileName, httpfilerootOrderReceipt+orderId);
        } catch (Exception e) {
            e.printStackTrace();
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("附件上传失败或者您没有上传附件!");
            return mapper.writeValueAsString(message);
        }
        if (mapPar.size() == 0) {
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请上传相关证书附件!");
            return mapper.writeValueAsString(message);
        }

        List<String> breakFile = new ArrayList<>();
        for (String key : mapPar.keySet()) {
//            breakFile.add(mapPar.get(key)+"/"+key);

            ReceiptUploadFiles uploadFiles = new ReceiptUploadFiles();
            uploadFiles.setIdUUID();
            uploadFiles.setOrderId(orderId);
            uploadFiles.setFileName(key);
            uploadFiles.setFileServerName(mapPar.get(key));
            orderUserService.postReceiptUploadFiles(uploadFiles,gauser.getUserId());
            breakFile.add(uploadFiles.getId());
        }
//        orderVo.setId(orderVo.getOrderId());
//        orderVo.setUserId(gauser.getUserId());
//        orderUserService.putOrderArea(orderVo);

        message.setResult(ResultMessage.Result.SUCCESS);
        message.setMessage(breakFile);
        return mapper.writeValueAsString(message);


    }



    /**
     *  订购单上传
     */
    @RequestMapping(method = RequestMethod.POST,value = "/uploadOrderInfo",produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String uploadOrderInfo(OrderVo orderVo, HttpServletRequest request) throws Exception {

        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String orderId = request.getParameter("orderId");
        if(StringUtils.isBlank(orderId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("附件上传失败请选择订单!");
            return mapper.writeValueAsString(message);
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
            String key = it.next();
            List<MultipartFile> multipartFiles = multipartRequest.getFiles(key);
            for(MultipartFile multipartFile : multipartFiles){
                if (multipartFile != null && StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
                    String fileName = multipartFile.getOriginalFilename();
                    String fileSuffix = StringUtils.substringAfterLast(fileName,".").toLowerCase();
                    if(!"jpg".equals(fileSuffix) && !"jpeg".equals(fileSuffix) && !"png".equals(fileSuffix) &&
                            !"gif".equals(fileSuffix) && !"pcx".equals(fileSuffix) && !"bmp".equals(fileSuffix) &&
                            !"zip".equals(fileSuffix) && !"rar".equals(fileSuffix) && !"7z".equals(fileSuffix) ){
                        message.setResult(ResultMessage.Result.ERROR);
                        message.setErrorMsg("附件上传文件仅支持\n图片格式：jpg,jpeg,png,gif,pcx,bmp。\n压缩包格式：zip,rar,7z。");
                        return mapper.writeValueAsString(message);
                    }
                }
            }
        }
        Map<String, String> mapPar = new HashMap<String, String>();
        try {
            String fileName = CommonUtils.getFilterName(orderVo.getOrderUnitName())+"_"+DateUtils.getDateYYYYMMDD();
//            mapPar = UpDownUtil.getUploadFilesReturnMap(request, CommonProperty.applicationProperties.getProperty("httpfilerootOrder")+orderVo.getOrderId()+"/");
            mapPar = UpDownUtil.getUploadFilesReturnMap(request,fileName, httpfilerootOrder+orderId);
        } catch (Exception e) {
            e.printStackTrace();
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("附件上传失败或者您没有上传附件!");
            return mapper.writeValueAsString(message);
        }
        if (mapPar.size() == 0) {
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请上传相关证书附件!");
            return mapper.writeValueAsString(message);
        }

        List<String> breakFile = new ArrayList<>();
        for (String key : mapPar.keySet()) {
//            breakFile.add(mapPar.get(key)+"/"+key);

            OrderUploadFiles uploadFiles = new OrderUploadFiles();
            uploadFiles.setIdUUID();
            uploadFiles.setOrderId(orderId);
            uploadFiles.setFileName(key);
            uploadFiles.setFileServerName(mapPar.get(key));
            orderUserService.postOrderUploadFiles(uploadFiles,gauser.getUserId());
            breakFile.add(uploadFiles.getId());
        }
//        orderVo.setId(orderVo.getOrderId());
//        orderVo.setUserId(gauser.getUserId());
//        orderUserService.putOrderArea(orderVo);

        message.setResult(ResultMessage.Result.SUCCESS);
        message.setMessage(breakFile);
        return mapper.writeValueAsString(message);


    }


    /**
     * 下载订购单附件
     * 下载附件 打包压缩包--删除
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/downLoadOrderFile")
    public void downLoadOrderFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("orderId");
        String orderUnitName = request.getParameter("orderUnitName");
        String flowId = request.getParameter("flowId");
        if (StringUtils.isBlank(orderId)) {
            logger.error("downLoadOrderFile ------------- orderId is null");
            return;
        }
//        String zipName = CommonProperty.applicationProperties.getProperty("httpfilerootOrder")+orderUnitName+DateUtils.getDateYYYYMMDDSS();
        String zipName = httpfilerootOrder + flowId + "_" + CommonUtils.getFilterName(orderUnitName) + "_" + DateUtils.getDateYYYYMMDDSS();
//        String filePath =  CommonProperty.applicationProperties.getProperty("httpfilerootOrder")+orderId;
        String filePath = httpfilerootOrder + orderId;
        ZipUtils.downLoadFile(zipName,filePath,response);
    }

    /**
     * 下载签收单
     * 下载附件 打包压缩包--删除
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/downLoadOrderReportFile")
    public void downLoadOrderReportFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("orderId");
        if (StringUtils.isBlank(orderId)) {
            logger.error("downLoadOrderFile ------------- orderId is null");
            return;
        }
        List<File> files = new ArrayList<>();
        Order order = orderUserService.getOrderById(orderId);
        if(order!=null){
            String zipName = "签收单"+"_"+order.getFlowId() + "_" +DateUtils.getDateYYYYMMDDSS();
            List<ReceiptUploadFiles> receiptUploadFiles = orderUserService.getReceiptUploadFilesByOrderId(orderId);
            if(receiptUploadFiles!=null){
                for(ReceiptUploadFiles receipt:receiptUploadFiles){ //添加List<File>文件进入压缩
                    files.add(new File(receipt.getFileServerName()+"\\"+receipt.getFileName()));
                }
            }
            ZipUtils.downLoadFile(zipName,files,response);
        }

    }

    /**
     * 下载附件 --删除
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/downloadTemp",produces = "application/json;charset=utf-8")
    public void downloadTemp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = request.getParameter("fileName");
        File outFile = null;
        InputStream inputStream = null;
        ServletOutputStream sos = null;
        try{
            outFile = new File(fileName);
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/*");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(outFile.getName().getBytes("GBK"), "ISO8859-1"));
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setDateHeader("Expires", System.currentTimeMillis());
            inputStream = new FileInputStream(outFile);
            sos = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int n;
            while (-1 != (n = inputStream.read(buffer))) {
                sos.write(buffer, 0, n);
            }
            sos.flush();
        } finally {
            if (sos != null) {
                sos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outFile != null) {
                outFile.delete();
            }
        }
    }
    //首页文件下载
    @RequestMapping(method = RequestMethod.GET, value = "/homeDownload",produces = "application/json;charset=utf-8")
    public void homeDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = httpfilerootHome+request.getParameter("fileName");
        File outFile = null;
        InputStream inputStream = null;
        ServletOutputStream sos = null;
        try{
            outFile = new File(fileName);
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/*");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(outFile.getName().getBytes("GBK"), "ISO8859-1"));
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setDateHeader("Expires", System.currentTimeMillis());
            inputStream = new FileInputStream(outFile);
            sos = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int n;
            while (-1 != (n = inputStream.read(buffer))) {
                sos.write(buffer, 0, n);
            }
            sos.flush();
        } finally {
            if (sos != null) {
                sos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = request.getParameter("fileName");
        File outFile = null;
        InputStream inputStream = null;
        ServletOutputStream sos = null;
        try{
            outFile = new File(fileName);
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/*");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(outFile.getName().getBytes("GBK"), "ISO8859-1"));
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setDateHeader("Expires", System.currentTimeMillis());
            inputStream = new FileInputStream(outFile);
            sos = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int n;
            while (-1 != (n = inputStream.read(buffer))) {
                sos.write(buffer, 0, n);
            }
            sos.flush();
        } finally {
            if (sos != null) {
                sos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outFile != null) {
                outFile.delete();
            }
        }
    }

    /**
     * IO 流读取签收单上传图片
     */
    @RequestMapping(value = "/ioReadImageReceipt",method = RequestMethod.GET)
    public String ioReadImageReceipt(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        ReceiptUploadFiles uploadFiles = orderUserService.getReceiptUploadFilesById(id);
        readImage(request,response,uploadFiles.getFileServerName(),uploadFiles.getFileName());
        return null;
    }

    /**
     * IO 流读取订购单上传图片
     */
    @RequestMapping(value = "/ioReadImage",method = RequestMethod.GET)
    public String ioReadImage(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        OrderUploadFiles  uploadFiles = orderUserService.getOrderUploadFilesById(id);
        readImage(request,response,uploadFiles.getFileServerName(),uploadFiles.getFileName());
        return null;
    }

    /**
     * IO 流处理
     */
    private String readImage(HttpServletRequest request,HttpServletResponse response,
                           String fileServerName,String fileName) throws Exception {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try{
            //获取图片存放路径
            String imgPath = fileServerName+"/"+fileName;
            ips = new FileInputStream(new File(imgPath));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
        return null;
    }

}
