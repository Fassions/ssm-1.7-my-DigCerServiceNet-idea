package cn.com.commonUser.controller;

import cn.com.common.agent.BaseDomain;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.*;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderInvoiceSave;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.service.CommonService;
import cn.com.common.service.ProductService;
import cn.com.common.utils.ResultMessage;
import cn.com.commonUser.service.OrderUserService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Horace.zhang on 2019/6/6.
 */
@Controller
@RequestMapping("commonUserOrder")
public class OrderController {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static  final ResultMessage message = new ResultMessage();

    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private OrderQueryCompanyService companyService;

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET,value = "/showOrderProcess")
    public ModelAndView showOrderProcess(HttpServletRequest request) throws Exception {
        //用户首页勾选跳转产品
        String product = request.getParameter("product");
        request.setAttribute("product",product);
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        Order orderParam = new Order();
        orderParam.setUserId(gauser.getUserId());
        orderParam.setSubmitStatus(BaseDomain.SUBMIT_STATUS_NONE);
        List<TblAreaInfo> areaInfos = null;
        List<TblAreaInfo> city = null;
        Order order = orderUserService.getOrder(orderParam);
        //收货地址首填
        DeliveryAddress data = new DeliveryAddress();
        data.setUserId(gauser.getUserId());
        data.setIsDefault(BaseDomain.IS_DEFAULT_DONE);
        DeliveryAddress address = orderUserService.getDeliveryAddress(data)!=null?orderUserService.getDeliveryAddress(data).get(0):null;
        request.setAttribute("address",address);
        //不存在 create
        if(order==null){
            OrderVo orderVo = new OrderVo();
            BeanUtilsEx.copyProperties(orderParam,orderVo);
            orderUserService.postOrder(orderVo);
            order = orderUserService.getOrder(orderParam);
            city = commonService.getTblAreaInfoByParentId("110000");
        }
        if(order!=null){
            List<OrderProduct> orderProducts = orderUserService.getOrderProductByOrderId(order.getId());
            city = commonService.getTblAreaInfoByParentId(order.getProvinceCode()!=null?order.getProvinceCode():"");
            if(city.size()==0){
                city = commonService.getTblAreaInfoByParentId("110000");
            }
            request.setAttribute("orderId",order.getId());
            request.setAttribute("order",order);
            request.setAttribute("provinceCode",order.getProvinceCode());
            request.setAttribute("cityCode",order.getCityCode());
            request.setAttribute("orderProducts",orderProducts);
        }
        areaInfos = commonService.getTblAreaInfoByLevel("1");
        List<TblProductList> productLists = commonService.getTblProductList();
        List<OrderUploadFiles> uploadFiles = orderUserService.getOrderUploadFilesByOrderId(order.getId());
        request.setAttribute("productLists",productLists);
        request.setAttribute("area",areaInfos);
        request.setAttribute("city",city);
        request.setAttribute("orderUploadFiles",uploadFiles);
        request.setAttribute("gauser",gauser);
        return new ModelAndView("CommonUser/order/OrderSubmit");
	}
	@RequestMapping(method = RequestMethod.GET,value = "/backEditOrder")
    public ModelAndView backEditOrder(HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        String orderId = request.getParameter("orderId");
        Order orderParam = new Order();
//        orderParam.setUserId(gauser.getUserId());
        orderParam.setId(orderId);
        List<TblAreaInfo> areaInfos = null;
        List<TblAreaInfo> city = null;
        Order order = orderUserService.getOrder(orderParam);
        if(order!=null){
            List<OrderProduct> orderProducts = orderUserService.getOrderProductByOrderId(order.getId());
            city = commonService.getTblAreaInfoByParentId(order.getProvinceCode());
            if(city.size()==0){
                city = commonService.getTblAreaInfoByParentId("110000");
            }
            request.setAttribute("orderId",order.getId());
            request.setAttribute("order",order);
            request.setAttribute("provinceCode",order.getProvinceCode());
            request.setAttribute("cityCode",order.getCityCode());
            request.setAttribute("orderProducts",orderProducts);
        }
        if(Objects.equals(gauser.getUserType(), BaseDomain.USER_TYPE_ONE)){  //卖家编辑不显示确定下单按钮
            request.setAttribute("hidePostButton",true);
        }

        areaInfos = commonService.getTblAreaInfoByLevel("1");
        List<TblProductList> productLists = commonService.getTblProductList();
        List<OrderUploadFiles> uploadFiles = orderUserService.getOrderUploadFilesByOrderId(order.getId());
        request.setAttribute("productLists",productLists);
        request.setAttribute("area",areaInfos);
        request.setAttribute("city",city);
        request.setAttribute("orderUploadFiles",uploadFiles);
        request.setAttribute("gauser",gauser);
        if(!Objects.equals(order.getOrderStatus(),BaseDomain.ORDER_STATUS_FIVE) && !Objects.equals(gauser.getUserType(), BaseDomain.USER_TYPE_ONE)){  //买家订单状态不是已退回无法进入编辑
            return new ModelAndView("CommonUser/orderquery/orderList");
        }
        return new ModelAndView("CommonUser/order/OrderSubmit");
}

    @RequestMapping(method = RequestMethod.GET,value = "/chooseInvoice")
    public ModelAndView chooseInvoice(HttpServletRequest request) throws Exception {
        String orderId = request.getParameter("orderId");
        //获取全部开票信息
        List<OrderInvoice> invoices = orderUserService.getOrderInvoiceByOrderId(orderId);
        //获取全部开票产品
        List<OrderInvoiceProduct> invoiceProducts = orderUserService.getOrderInvoiceProductByOrderId(orderId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("invoices", invoices);
        request.setAttribute("invoiceProducts", invoiceProducts);
        return new ModelAndView("CommonUser/order/chooseInvoice");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/createInvoice")
    public ModelAndView createInvoice(HttpServletRequest request) throws Exception {
        String orderId = request.getParameter("orderId");
        String orderUnitName = request.getParameter("orderUnitName");


        //获取未填写开票信息产品 （已弃用，不用判断依据，每次显示全部，下订购单时验证）
//        List<OrderProduct> invoiceOrderProducts = orderUserService.getInvoiceNotOrderProduct();
        List<OrderProduct> invoiceOrderProducts = orderUserService.getOrderProductByOrderId(orderId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("invoiceOrderProducts",invoiceOrderProducts);
        request.setAttribute("orderUnitName",orderUnitName);
        return new ModelAndView("CommonUser/order/detailsInvoice");
    }


    @RequestMapping(method = RequestMethod.POST,value = "/postInvoice")
    @ResponseBody
    public String postInvoice(@RequestBody OrderInvoiceSave orderInvoiceSave, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }
        String result = orderUserService.setPostInvoice(orderInvoiceSave,gauser.getUserId());
        if(result!=null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg(result);
            return mapper.writeValueAsString(message);
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/putInvoice")
    @ResponseBody
    public String putInvoice(@RequestBody OrderInvoiceSave orderInvoiceSave, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }
        String result = orderUserService.setPutInvoice(orderInvoiceSave,gauser.getUserId());
        if(result!=null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg(result);
            return mapper.writeValueAsString(message);
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }
    // 提交选购证书
    @RequestMapping(method = RequestMethod.POST,value = "/postProduct",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postProduct(OrderVo orderVo, HttpServletRequest request) throws Exception {
    	message.setMessage(null);
    	GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            return "未登陆";
        }
        if(StringUtils.isBlank(orderVo.getId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择订单");
            return mapper.writeValueAsString(message);
        }
        orderVo.setUserId(gauser.getUserId());
        // 证书编号是否重复
        String value = orderUserService.postProduct(orderVo, gauser);
        if(value!=null){
        	if(gauser.getUserType()!=1) {
        		message.setResult(ResultMessage.Result.ERROR);
        		message.setErrorMsg(value);
        		return mapper.writeValueAsString(message);
        	} else {
        		// 管理员允许证书编号重复
                List<OrderProduct> orderProducts = orderUserService.getOrderProductByOrderId(orderVo.getOrderId());
                message.setResult(ResultMessage.Result.SUCCESS);
                message.setMessage(value);
                message.setErrorMsg(mapper.writeValueAsString(orderProducts));
                return mapper.writeValueAsString(message);
        	}
        }
        // 页面显示选购证书
        List<OrderProduct> orderProducts = orderUserService.getOrderProductByOrderId(orderVo.getOrderId());
        message.setResult(ResultMessage.Result.SUCCESS);
        message.setErrorMsg(mapper.writeValueAsString(orderProducts));
        return mapper.writeValueAsString(message);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/postOrder",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postOrder(OrderVo orderVo, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("未登陆");
            return mapper.writeValueAsString(message);
        }
//        orderVo.setId(request.getParameter("orderId"));



        //校验开票类型 是否符合要求
//        String invoiceType = request.getParameter("invoiceType");

        orderVo.setChildType(BaseDomain.IS_CHILD_TYPE_NONE);
        if(Objects.equals(gauser.getUserType(), BaseDomain.USER_TYPE_ZERO)){   //买家
            //订单类型 0订购
            orderVo.setOrderType(0);
        }
        orderVo.setOrderStatus(0.0);
        orderVo.setOrderDate(new Date());
        orderVo.setNeedInvoice(1);
        if(orderVo.getNeedBeforeInvoice()==null){
            //默认值后开票
            orderVo.setNeedBeforeInvoice(BaseDomain.NEED_BEFORE_INVOICE_ZERO);
        }
       if(orderVo.getOrderInvoiceStatus()==null){
           //订单发票状态 默认值 卖家未开票
           orderVo.setOrderInvoiceStatus(BaseDomain.ORDER_INVOICE_STATUS_ZERO);
       }
//        orderVo.setInvoiceType(Integer.parseInt(request.getParameter("invoiceType")));
//        orderVo.setOrderUnitName(request.getParameter("orderUnitName"));
//        orderVo.setPurchaser(request.getParameter("purchaser"));
//        orderVo.setPurchaserMobile(request.getParameter("purchaserMobile"));
//        orderVo.setPurchaserPhone(request.getParameter("purchaserPhone"));
//        orderVo.setRemitterType(Integer.parseInt(request.getParameter("remitterType")));
//        orderVo.setRemitter(request.getParameter("remitter"));
//        orderVo.setRemark(request.getParameter("remark"));
//        orderVo.setProductTotalAmount(Double.parseDouble(request.getParameter("productTotalAmount")));
//        orderVo.setProductTotalCount(Integer.parseInt(request.getParameter("productTotalCount")));
        orderVo.setUserId(gauser.getUserId());
        orderVo.setSubmitStatus(BaseDomain.SUBMIT_STATUS_DONE);

        //验证订单id
        if(StringUtils.isBlank(orderVo.getId())){
//            Order getOrder = new Order();
//            getOrder.setUserId(gauser.getUserId());
//            getOrder.setSubmitStatus(BaseDomain.SUBMIT_STATUS_NONE);
//            getOrder = orderUserService.getOrder(getOrder);
//            if(getOrder!=null){
//                orderVo.setId(getOrder.getId());
//            }else {
//                message.setResult(ResultMessage.Result.ERROR);
//                message.setErrorMsg("请选择订单");
//                return mapper.writeValueAsString(message);
//            }
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请选择订单");
            return mapper.writeValueAsString(message);
        }
        //专用发票验证是否符合条件
        if(Objects.equals(orderVo.getInvoiceType(), BaseDomain.INVOICE_TYPE_ZERO)){
            if(orderUserService.getOrderInvoiceNotSourceNull(orderVo.getId())!=null){
                message.setResult(ResultMessage.Result.ERROR);
                message.setErrorMsg("您选择为增值税专用发票，开票信息必填项必须填写，请核对。");
                return mapper.writeValueAsString(message);
            }
        }
        //获取选定收货地址
//        DeliveryAddress deliveryAddress = orderUserService.getDeliveryAddressIsDefault(orderVo.getUserId());
//        if(deliveryAddress!=null){
//            orderVo.setReceiveUnitName(deliveryAddress.getReceiveUnitName());
//            orderVo.setReceiveUnitAddress(deliveryAddress.getReceiveUnitAddress());
//            orderVo.setReceiveName(deliveryAddress.getReceiveName());
//            orderVo.setReceiveMobile(deliveryAddress.getReceiveMobile());
//            orderVo.setReceivePhone(deliveryAddress.getReceivePhone());
//            orderVo.setStandbyName(deliveryAddress.getStandbyName());
//            orderVo.setStandbyMobile(deliveryAddress.getStandbyMobile());
//            orderVo.setStandbyPhone(deliveryAddress.getStandbyPhone());
//        }
        Order order = orderUserService.getOrderById(orderVo.getId());
        if(order==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("您操作的订单错误，请重试。");
            return mapper.writeValueAsString(message);
        }
        List<OrderUploadFiles> uploadFiles = orderUserService.getOrderUploadFilesByOrderId(order.getId());
        if(uploadFiles==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请进行文件上传操作。");
            return mapper.writeValueAsString(message);
        }
        if(productService.verifyOrderProductByOrderId(order.getId())){
        	if (gauser.getUserType()!=1) {
        		message.setResult(ResultMessage.Result.ERROR);
                message.setErrorMsg("订购的产品编号已被使用，请重新选购。");
                return mapper.writeValueAsString(message);
			}
        }
        orderUserService.putOrderArea(orderVo);

        //增加操作流转记录
        BeanUtilsEx.copyProperties(orderVo,order);
        companyService.putDetailsOrderStatus(order,order.getUserId(),BaseDomain.HISTROY_STATUS_TEN);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    //保存
    @RequestMapping(method = RequestMethod.POST,value = "/postOrderSave",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postOrderSave(OrderVo orderVo, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(orderVo.getUserId()==null) {
        	orderVo.setUserId(gauser.getUserId());
        }
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("未登陆");
            return mapper.writeValueAsString(message);
        }
//        orderVo.setUserId(gauser.getUserId());
        //验证订单id
        if(StringUtils.isBlank(orderVo.getId())){
//            Order getOrder = new Order();
//            getOrder.setUserId(gauser.getUserId());
//            getOrder.setSubmitStatus(BaseDomain.SUBMIT_STATUS_NONE);
//            getOrder = orderUserService.getOrder(getOrder);
//            if(getOrder!=null){
//                orderVo.setId(getOrder.getId());
//            }else {
//                message.setResult(ResultMessage.Result.ERROR);
//                message.setErrorMsg("请选择订单");
//                return mapper.writeValueAsString(message);
//            }
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请选择订单");
            return mapper.writeValueAsString(message);
        }
        //专用发票验证是否符合条件
//        if(orderVo.getInvoiceType()==BaseDomain.INVOICE_TYPE_ZERO){
//            if(orderUserService.getOrderInvoiceNotSourceNull(orderVo.getId())!=null){
//                message.setResult(ResultMessage.Result.ERROR);
//                message.setErrorMsg("您选择为增值税专用发票，开票信息必填项必须填写，请核对。");
//                return mapper.writeValueAsString(message);
//            }
//        }
//        //获取选定收货地址
//        DeliveryAddress deliveryAddress = orderUserService.getDeliveryAddressIsDefault(orderVo.getUserId());
//        if(deliveryAddress!=null){
//            orderVo.setReceiveUnitName(deliveryAddress.getReceiveUnitName());
//            orderVo.setReceiveUnitAddress(deliveryAddress.getReceiveUnitAddress());
//            orderVo.setReceiveName(deliveryAddress.getReceiveName());
//            orderVo.setReceiveMobile(deliveryAddress.getReceiveMobile());
//            orderVo.setReceivePhone(deliveryAddress.getReceivePhone());
//            orderVo.setStandbyName(deliveryAddress.getStandbyName());
//            orderVo.setStandbyMobile(deliveryAddress.getStandbyMobile());
//            orderVo.setStandbyPhone(deliveryAddress.getStandbyPhone());
//        }

        orderUserService.putOrderArea(orderVo);
        if(Objects.equals(gauser.getUserType(), BaseDomain.USER_TYPE_ONE)) {   //卖家订单编辑 保存 增加操作流转记录
            Order order = new Order();
            BeanUtilsEx.copyProperties(orderVo,order);
            companyService.putDetailsOrderStatus(order,order.getUserId(),BaseDomain.HISTROY_STATUS_ELEVEN);
        }

        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/deleteProduct",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteProduct( HttpServletRequest request) throws Exception {
        String orderProductId = request.getParameter("orderProductId");
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            return mapper.writeValueAsString("未登录");
        }
        orderUserService.deleteOrderProduct(orderProductId, gauser.getUserId());
        return mapper.writeValueAsString("删除成功");
    }


    @RequestMapping(method = RequestMethod.POST,value = "/deleteInvoice",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteInvoice( HttpServletRequest request) throws Exception {
        String orderInviceId = request.getParameter("id");
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            return mapper.writeValueAsString("未登录");
        }
        orderUserService.deleteOrderInvoiceById(orderInviceId);
        return mapper.writeValueAsString("删除成功");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/editInvoice")
    public ModelAndView editAddress(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        OrderInvoice orderInvoice = orderUserService.getOrderInvoiceById(id);
        if(orderInvoice!=null){
            List<OrderInvoiceProduct> invoiceOrderProducts = orderUserService.getOrderInvoiceProductByOrderInvoiceId(orderInvoice.getId());
            request.setAttribute("orderId", orderInvoice.getOrderId());
            request.setAttribute("invoiceDetails", orderInvoice);
            request.setAttribute("invoiceOrderProducts",invoiceOrderProducts);
            return new ModelAndView("CommonUser/order/detailsInvoice");
        }
        return null;

    }

    @RequestMapping(method = RequestMethod.POST,value = "/deleteOrderUnloadFiles")
    @ResponseBody
    public String deleteOrderUnloadFiles(HttpServletRequest request) throws Exception {
        String orderUnloadFilesId = request.getParameter("id");
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("未登陆");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(orderUnloadFilesId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择文件。");
            return mapper.writeValueAsString(message);
        }
        orderUserService.deleteOrderUploadFilesById(orderUnloadFilesId,gauser.getUserId());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/deleteReceiptUnloadFiles")
    @ResponseBody
    public String deleteReceiptUnloadFiles(HttpServletRequest request) throws Exception {
        String receiptUnloadFilesId = request.getParameter("id");
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("未登陆");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(receiptUnloadFilesId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择文件。");
            return mapper.writeValueAsString(message);
        }
        orderUserService.deleteReceiptUploadFilesById(receiptUnloadFilesId,gauser.getUserId());
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }







}
