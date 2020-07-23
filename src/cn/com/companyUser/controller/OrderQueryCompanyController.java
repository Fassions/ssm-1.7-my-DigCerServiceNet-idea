package cn.com.companyUser.controller;

import cn.com.common.agent.BaseDomain;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.*;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderProductExpressVo;
import cn.com.common.model.search.OrderHistoryStatusSearcher;
import cn.com.common.model.search.OrderMemoSearcher;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.common.service.CommonService;
import cn.com.common.utils.*;
import cn.com.commonUser.service.OrderUserService;
import cn.com.companyUser.service.ExcelTemplateService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Horace.zhang on 2019/7/8.
 */
@Controller
@RequestMapping("commonCompanyOrderQuery")
public class OrderQueryCompanyController {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static  final ResultMessage message = new ResultMessage();

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderQueryCompanyController.class);

    @Value("${httpfilerootEInvoice}")
    private String httpfilerootEInvoice;

    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderQueryCompanyService companyService;

    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private ExcelTemplateService excelTemplateService;

    @RequestMapping(method = RequestMethod.GET,value = "/getOrderIndex")
    public ModelAndView chooseInvoice(HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(gauser.getUserId())){
            logger.error("卖家证书查询 userId 为null");
        }
        List<TblAreaInfo> areaInfos = commonService.getTblAreaInfoByLevel("1");
        List<TblTemplateExcel> templateExcelList = excelTemplateService.getTblTemPlateExcelByUserId(gauser.getUserId());
        request.setAttribute("area",areaInfos);
        request.setAttribute("templateExcelList",templateExcelList);
        return new ModelAndView("CompanyUser/orderquery/orderIndex");
    }

    /**
     * 基本信息搜索
     */
    @RequestMapping(method = RequestMethod.POST,value = "/showOrderPage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showOrderPage(OrderQuerySearcher orderQuerySearcher,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        Page<OrderQuerySearcher> page = companyService.listOrderCompanyWithPage(
        		orderQuerySearcher, gauser, new Limit(orderQuerySearcher.getOffset(), 
				orderQuerySearcher.getPageCount()));
        return mapper.writeValueAsString(page);
    }

    //批量 发货
    @RequestMapping(method = RequestMethod.POST,value = "/postPutOrderExcpress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postPutOrderExcpress(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String orderStatus = request.getParameter("orderStatus");
        String deliveryDate = request.getParameter("deliveryDate");
        String expressType = request.getParameter("expressType");
        String[] orderId = request.getParameter("orderId").split(";");
        List<Order> orders = companyService.getOrderByOrderIdList(orderId);
        if(orders==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("选择订单为空请重新选择");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(expressType) || StringUtils.isBlank(deliveryDate)){  //批处理发货日期，发运类型。
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("必须填写发货日期或发运类型");
            return mapper.writeValueAsString(message);
        }
        for(Order order:orders){
            OrderExpress express = new OrderExpress();
            express.setOrderId(order.getId());
            express.setExpressType(IntegerUtil.parseInt(expressType));
            express.setDeliveryDate(DateUtils.parseStringByPatternYYYYMMDD(deliveryDate));
            companyService.postOrderExpress(express,gauser.getUserId());
        }
        companyService.putOrderStatus(orders,gauser.getUserId(),Double.parseDouble(orderStatus));
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }
    //批量操作
    @RequestMapping(method = RequestMethod.POST,value = "/putOrderStatus",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putOrderStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String orderStatus = request.getParameter("orderStatus");
        String[] orderId = request.getParameter("orderId").split(";");
        List<Order> orders = companyService.getOrderByOrderIdList(orderId);
        if(orders==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("选择订单为空请重新选择");
            return mapper.writeValueAsString(message);
        }
        companyService.putOrderStatus(orders,gauser.getUserId(),Double.parseDouble(orderStatus));
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //订单退回
    @RequestMapping(method = RequestMethod.POST,value = "/putOrderReturnReason",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putOrderReturnReason(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String returnReason = request.getParameter("returnReason");
        String orderId = request.getParameter("orderId");
        Order order = orderUserService.getOrderById(orderId);
        if(order==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("选择订单为空请重新选择");
            return mapper.writeValueAsString(message);
        }
        order.setOrderStatus(BaseDomain.ORDER_STATUS_FIVE);
        order.setReturnReason(returnReason);
        companyService.putDetailsOrderStatus(order,gauser.getUserId(),order.getOrderStatus());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //订单作废
    @RequestMapping(method = RequestMethod.POST,value = "/putOrderinvalid",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putOrderinvalid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String orderInvalid = request.getParameter("orderInvalid");
        String orderId = request.getParameter("orderId");
        Order order = orderUserService.getOrderById(orderId);
        if(order==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("选择订单为空请重新选择");
            return mapper.writeValueAsString(message);
        }
        order.setOrderStatus(BaseDomain.ORDER_STATUS_SIX);
        order.setOrderInvalid(orderInvalid);
        companyService.putDetailsOrderStatus(order,gauser.getUserId(),order.getOrderStatus());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }
    //更改订单状态
    @RequestMapping(method = RequestMethod.POST,value = "/putDetailsOrderStatus",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putDetailsOrderStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String orderStatus = request.getParameter("orderStatus");
        String orderId = request.getParameter("orderId");
        Order order = orderUserService.getOrderById(orderId);
        if(order==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("选择订单为空请重新选择");
            return mapper.writeValueAsString(message);
        }
        String value = companyService.putDetailsOrderStatus(order,gauser.getUserId(),Double.parseDouble(orderStatus));
        if(value!=null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg(value);
            return mapper.writeValueAsString(message);
        }

//        order.setOrderStatus(Double.parseDouble(orderStatus));

        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //更改借转销类型
    @RequestMapping(method = RequestMethod.POST,value = "/putBorrowType",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putBorrowType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String borrowType = request.getParameter("borrowType");
        String orderId = request.getParameter("orderId");
        Order order = orderUserService.getOrderById(orderId);
        order.setBorrowType(IntegerUtil.parseInt(borrowType));
        if(order==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("选择订单为空请重新选择");
            return mapper.writeValueAsString(message);
        }
        orderUserService.putOrder(order);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //更改生产异常状态
    @RequestMapping(method = RequestMethod.POST,value = "/putDetailsProductionErrorStatus",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putDetailsProductionErrorStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String productionErrorStatus = request.getParameter("productionErrorStatus");
        String orderId = request.getParameter("orderId");
        Order order = orderUserService.getOrderById(orderId);
        order.setProductionErrorStatus(IntegerUtil.parseInt(productionErrorStatus));
        if(order==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("选择订单为空请重新选择");
            return mapper.writeValueAsString(message);
        }
        orderUserService.putOrder(order);
        //新增流转记录
        OrderHistoryStatus historyStatus = new OrderHistoryStatus();
        Double putOrderStatus = IntegerUtil.parseInt(productionErrorStatus)==1?BaseDomain.HISTROY_STATUS_FOURTEEN:BaseDomain.HISTROY_STATUS_FIFTEEN;
        historyStatus.setHistoryStatus(putOrderStatus);
        historyStatus.setOrderId(order.getId());
        companyService.postOrderHistoryStatus(historyStatus,gauser.getUserId());
        //TODO:关闭退回重审，重新生成任务单号
        companyService.postProductionFlowId(order.getId());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //留言消息提醒取消
    @RequestMapping(method = RequestMethod.POST,value = "/putOrderMessageWarn",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putOrderMessageWarn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("orderId");
        orderUserService.putOrderMessageWarn(orderId,BaseDomain.IS_WARN_FALSE);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    /**
     * 订单状态流转记录查询
     */
    @RequestMapping(method = RequestMethod.POST,value = "/showOrderHistoryStatusPage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showOrderHistoryStatusPage(OrderHistoryStatusSearcher orderHistoryStatusSearcher, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Page<OrderHistoryStatusSearcher> page = companyService.listOrderHistoryStatusWithPage(orderHistoryStatusSearcher, new Limit(orderHistoryStatusSearcher.getOffset(), orderHistoryStatusSearcher.getPageCount()));
        return mapper.writeValueAsString(page);
    }

    /**
     * 订单备忘记录查询
     */
    @RequestMapping(method = RequestMethod.POST,value = "/showOrderMemoPage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showOrderMemoPage(OrderMemoSearcher orderMemoSearcher, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Page<OrderMemoSearcher> page = companyService.listOrderMemoWithPage(orderMemoSearcher, new Limit(orderMemoSearcher.getOffset(), orderMemoSearcher.getPageCount()));
        return mapper.writeValueAsString(page);
    }

    //提交生产注意事项
    @RequestMapping(method = RequestMethod.POST,value = "/postOrderProductionMemo")
    @ResponseBody
    public String postOrderProductionMemo(HttpServletRequest request) throws Exception {
        GaUser gaUser = (GaUser) request.getSession().getAttribute("gaUser");
        OrderProductionMemo memo = new OrderProductionMemo();
        memo.setMemo(request.getParameter("productionMemo"));
        memo.setOrderId(request.getParameter("orderId"));
        if(gaUser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(memo.getMemo())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请填写备忘内容信息");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(memo.getOrderId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择订购单。");
            return mapper.writeValueAsString(message);
        }
        OrderProductionMemo memo1 = companyService.getOrderProductionMemoByOrderId(memo.getOrderId());
        if(memo1==null){
            companyService.postOrderProductionMemo(memo,gaUser.getUserId());
        }else{
            BeanUtilsEx.copyProperties(memo,memo1);
            companyService.putOrderProductionMemo(memo1,gaUser.getUserId());
        }

        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //提交备忘记录
    @RequestMapping(method = RequestMethod.POST,value = "/postOrderMemo")
    @ResponseBody
    public String postOrderMemo(HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        OrderMemo memo = new OrderMemo();
        memo.setUserCreated(gauser.getUserId());
        memo.setOrderId(request.getParameter("orderId"));
        memo.setMemo(request.getParameter("memo"));
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(memo.getMemo())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请填写备忘内容信息");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(memo.getOrderId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择订购单。");
            return mapper.writeValueAsString(message);
        }

        companyService.postOrderMemo(memo,gauser.getUserId());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //到款信息登记提交
    @RequestMapping(method = RequestMethod.POST,value = "/postOrderPaid",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postProduct(OrderPaid orderPaid, HttpServletRequest request) throws Exception {
    	GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(orderPaid.getOrderId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择订购单。");
            return mapper.writeValueAsString(message);
        }
        companyService.postOrderPaid(orderPaid,gauser.getUserId());
        // 提前发货类型订单，全部到款后自动转为借转销，4提前发货订单
        Order order = orderUserService.getOrderById(orderPaid.getOrderId());
        if(order.getOrderType()==4) {
        	if(orderPaid.getIsTotalArrival()==1) {
            	order.setBorrowType(1);
            	orderUserService.putOrder(order);
            }
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }
    // 编辑到款信息
    @RequestMapping(method = RequestMethod.POST,value = "/editOrderPaid",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String editOrderPaid(OrderPaid orderPaid,HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(orderPaid.getId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择到款信息。");
            return mapper.writeValueAsString(message);
        }
        companyService.editOrderPaid(orderPaid,gauser.getUserId());
        // 提前发货类型订单，全部到款后自动转为借转销，4提前发货订单
        Order order = orderUserService.getOrderById(orderPaid.getOrderId());
        if(order.getOrderType()==4) {
        	if(orderPaid.getIsTotalArrival()==1) {
            	order.setBorrowType(1);
            	orderUserService.putOrder(order);
            }
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/editOrderExpress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String editOrderExpress(OrderExpress orderExpress,HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(orderExpress.getId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择到款信息。");
            return mapper.writeValueAsString(message);
        }
        companyService.editOrderExpress(orderExpress,gauser.getUserId());

        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //产品快递信息提交
    @RequestMapping(method = RequestMethod.POST,value = "/postOrderExpress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postOrderExpress(OrderExpress orderExpress, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(orderExpress.getOrderId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择订购单。");
            return mapper.writeValueAsString(message);
        }
        //TODO:只做登记 不做限制。
//        Order order = orderUserService.getOrderById(orderExpress.getOrderId());
//        if(order==null){
//            message.setResult(ResultMessage.Result.ERROR);
//            message.setErrorMsg("您选择的订单有误。");
//            return mapper.writeValueAsString(message);
//        }
//        if(!Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_TWO_TWO)){
//            message.setResult(ResultMessage.Result.ERROR);
//            message.setErrorMsg("订单当前状态不是生产中--已完成，请变更订单状态。");
//            return mapper.writeValueAsString(message);
//        }
//        companyService.postOrderExpress(orderExpress,gauser.getUserId());
//        //只有填写 证书、证书和发票品名 变更订单状态为发运中。订单状态不能为 已完成，已退回，已作废。
//        if(Objects.equals(orderExpress.getExpressType(), BaseDomain.EXPRESS_TYPE_TWO)
//                || Objects.equals(orderExpress.getExpressType(), BaseDomain.EXPRESS_TYPE_THREE)){
//            if(!Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_FOUR)
//                    || !Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_FIVE)
//                    || !Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_SIX)){
//                companyService.putDetailsOrderStatus(order,gauser.getUserId(),BaseDomain.ORDER_STATUS_THREE);
//            }
//
//        }
        companyService.postOrderExpress(orderExpress,gauser.getUserId());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }
    // 开票快递信息提交
    @RequestMapping(method = RequestMethod.POST,value = "/postOrderInvoiceExpress",
    		produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postOrderInvoiceExpress(OrderInvoiceExpress orderInvoiceExpress, 
    		HttpServletRequest request) throws Exception {
    	GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
    	if(StringUtils.isBlank(orderInvoiceExpress.getOrderId())){
    		message.setResult(ResultMessage.Result.ERROR);
    		message.setErrorMsg("请重新选择订购单。");
    		return mapper.writeValueAsString(message);
    	}
    	OrderInvoiceExpress expressPut = orderUserService.getOrderInvoiceExpressByFlowId(orderInvoiceExpress.getFlowId());
    	List<OrderInvoiceExpress> loie = new ArrayList<OrderInvoiceExpress>();
    	if (expressPut!=null){  //存在更新
    		orderInvoiceExpress.setId(expressPut.getId());
    		loie.add(orderInvoiceExpress);
    		companyService.putOrderInvoiceExpress(loie, gauser.getUserId());
    	} else { //不存在插入
    		loie.add(orderInvoiceExpress);
        	companyService.postOrderInvoiceExpress(loie, gauser.getUserId());
    	}
    	
    	message.setResult(ResultMessage.Result.SUCCESS);
    	return mapper.writeValueAsString(message);
    }

    //信息登记提交记录查询
    @RequestMapping(method = RequestMethod.POST,value = "/showOrderPaidList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showOrderPaidList(OrderPaid orderPaid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<OrderPaid> page = companyService.getOrderPaidByOrderIdList(orderPaid.getOrderId());
        return mapper.writeValueAsString(page);
    }

    //快递信息提交记录查询
    @RequestMapping(method = RequestMethod.POST,value = "/showOrderExpressList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showOrderPaidList(OrderExpress orderExpress, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<OrderProductExpressVo> page = orderUserService.getOrderProductExpressByOrderId(orderExpress.getOrderId());
//        List<OrderExpress> page = orderUserService.getOrderExpressByOrderid(orderExpress.getOrderId());
        return mapper.writeValueAsString(page);
    }


    //信息登记：开票方式，开票日期修改
    @RequestMapping(method = RequestMethod.POST,value = "/putOrder",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putOrder(Order order, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(order.getId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择订购单。");
            return mapper.writeValueAsString(message);
        }
        //开票日期不为空，发票状态变更为已开票
        if(order.getBillingDate()!=null){
            order.setOrderInvoiceStatus(BaseDomain.ORDER_INVOICE_STATUS_ONE);
        }
        orderUserService.putOrder(order);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }


    /**
     * 电子发票文件上传功能--上传
     */
    @RequestMapping(method = RequestMethod.POST,value = "/uploadInvoiceFile",produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String uploadInvoiceFile(OrderElectronicInvoice electronicInvoice, HttpServletRequest request) throws Exception {
        String fileName = "";
        String filePath = "";
        String name = "";  //文件名称不带后缀
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(StringUtils.isBlank(gauser.getUserId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("文件上传失败请重新上传!");
            return mapper.writeValueAsString(message);
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, String> mapPar = new HashMap<String, String>();
        try {
            fileName = (multipartRequest.getFiles(multipartRequest.getFileNames().next())).get(0).getOriginalFilename();
            name = StringUtils.substringBeforeLast(fileName,".");
            filePath = httpfilerootEInvoice+electronicInvoice.getOrderId();
            String fileSuffix = StringUtils.substringAfterLast(fileName,".").toLowerCase();
            if(!"pdf".equals(fileSuffix) &&
                    !"zip".equals(fileSuffix) && !"rar".equals(fileSuffix) && !"7z".equals(fileSuffix) ){
                message.setResult(ResultMessage.Result.ERROR);
                message.setErrorMsg("附件上传文件仅支持\n图片格式：pdf。\n压缩包格式：zip,rar,7z。");
                return mapper.writeValueAsString(message);
            }
            //验证流水号是否正确
            Order order = orderUserService.getOrderByIdFlowId(electronicInvoice.getOrderId(),name);
            if(order==null){
                message.setResult(ResultMessage.Result.ERROR);
                message.setErrorMsg("上传文件流水号不存在，或流水号与当前订单不对应。");
                return mapper.writeValueAsString(message);
            }
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
        electronicInvoice.setName(name);
        electronicInvoice.setFlowId(name);
        electronicInvoice.setFileName(fileName);
        electronicInvoice.setFileServerName(filePath);
        OrderElectronicInvoice electronicInvoice1 = companyService.getOrderElectronicInvoice(electronicInvoice.getOrderId());
        if(electronicInvoice1==null){
            companyService.postOrderElectronicInvoice(electronicInvoice,gauser.getUserId());
        }else{
            BeanUtilsEx.copyProperties(electronicInvoice,electronicInvoice1);
            companyService.putOrderElectronicInvoice(electronicInvoice1,gauser.getUserId());
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    /**
     * 电子发票文件上传功能--下载
     * 下载附件 打包压缩包--删除
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/downLoadInvoiceFile")
    public void downLoadInvoiceFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("orderId");
        if (StringUtils.isBlank(orderId)) {
            logger.error("downLoadOrderFile ------------- orderId is null");
            return;
        }
        OrderElectronicInvoice orderElectronicInvoice = companyService.getOrderElectronicInvoice(orderId);
        String zipName =  orderElectronicInvoice.getName() + "_" + DateUtils.getDateYYYYMMDDSS();
        String filePath = orderElectronicInvoice.getFileServerName();
        ZipUtils.downLoadFile(zipName,filePath,response);
    }







}
