package cn.com.commonUser.controller;

import cn.com.common.agent.BaseDomain;
import cn.com.common.model.*;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderInvoiceSave;
import cn.com.common.model.dto.OrderInvoiceVo;
import cn.com.common.model.dto.OrderProductExpressVo;
import cn.com.common.model.search.OrderMessageSearcher;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.common.service.CommonService;
import cn.com.common.utils.ResultMessage;
import cn.com.commonUser.service.OrderQueryUserService;
import cn.com.commonUser.service.OrderUserService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import cn.com.companyUser.service.impl.OrderQueryCompanyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Horace.zhang on 2019/7/1.
 */
@Controller
@RequestMapping("commonUserOrderQuery")
public class OrderQueryUserController {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static  final ResultMessage message = new ResultMessage();

    @Autowired
    private OrderQueryUserService queryUserService;
    @Autowired
    private OrderQueryCompanyService companyService;

    @Autowired
    private OrderUserService userService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET,value = "/getOrderList")
    public ModelAndView getOrderList(HttpServletRequest request) throws Exception {
        return new ModelAndView("CommonUser/orderquery/orderList");
    }
    @RequestMapping(method = RequestMethod.GET,value = "/showDetailsOrder")
    public ModelAndView showDetailsOrder(HttpServletRequest request) throws Exception {
        Order order = new Order();
        order.setId(request.getParameter("orderId"));
        order = userService.getOrder(order);
//        List<OrderProductExpressVo> orderProducts = userService.getOrderProductExpressByOrderId(order.getId());
        OrderElectronicInvoice electronicInvoice = companyService.getOrderElectronicInvoice(order.getId());
        List<OrderInvoiceVo> orderInvoiceVos = userService.getOrderInvoiceVo(order.getId());
        OrderProductionMemo productionMemo = companyService.getOrderProductionMemoByOrderId(order.getId());
        //List<OrderExpress> expresses = userService.getOrderExpressByOrderid(order.getId());
        request.setAttribute("order",order);
        request.setAttribute("orderId",order.getId());
        request.setAttribute("province",commonService.getTblAreaInfoById(order.getProvinceCode()));
        request.setAttribute("city",commonService.getTblAreaInfoById(order.getCityCode()));
//        request.setAttribute("orderProducts",orderProducts);
        request.setAttribute("electronicInvoice",electronicInvoice);
        request.setAttribute("orderInvoiceVos",orderInvoiceVos);
        request.setAttribute("productionMemo",productionMemo);
        //发票快递信息
        OrderInvoiceExpress invoiceExpress = userService.getOrderInvoiceExpressByFlowId(order.getFlowId());
        request.setAttribute("invoiceExpress",invoiceExpress);
        //request.setAttribute("expresses",expresses);
        return new ModelAndView("CommonUser/orderquery/detailsOrder");
    }

    //签收单文件查询
    @RequestMapping(method = RequestMethod.POST,value = "/getReceiptUploadFiles",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getReceiptUploadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("orderId");
        if(StringUtils.isBlank(orderId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择订单。");
            return mapper.writeValueAsString(message);
        }
        List<ReceiptUploadFiles> receiptUploadFiles = userService.getReceiptUploadFilesByOrderId(orderId);
        message.setResult(ResultMessage.Result.SUCCESS);
        message.setMessage(receiptUploadFiles);
        return mapper.writeValueAsString(message);
    }


    /**
     * 基本信息搜索
     */
    @RequestMapping(method = RequestMethod.POST,value = "/showOrderPage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showOrderPage(OrderQuerySearcher orderQuerySearcher,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        Page<OrderQuerySearcher> page = queryUserService.listOrderWithPage(orderQuerySearcher,gauser, new Limit(orderQuerySearcher.getOffset(), orderQuerySearcher.getPageCount()));

        return mapper.writeValueAsString(page);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/postOrderMessage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postOrderMessage(HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setUserId(gauser.getUserId());
        orderMessage.setOrderId(request.getParameter("orderId"));
        orderMessage.setMessage(request.getParameter("message"));
        orderMessage.setUserType(gauser.getUserType());
        if(Objects.equals(orderMessage.getUserType(), BaseDomain.USER_TYPE_ZERO)){ //买家需要提醒
            orderMessage.setWarn(BaseDomain.IS_WARN_TRUE);
        }

        if(gauser==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重试");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(orderMessage.getMessage())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请填写留言信息");
            return mapper.writeValueAsString(message);
        }else if(StringUtils.isBlank(orderMessage.getOrderId())){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择订购单。");
            return mapper.writeValueAsString(message);
        }
        userService.postOrderMessage(orderMessage);
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/putComplate",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putComplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        Order order = new Order();
        order.setId(request.getParameter("orderId"));
        order = userService.getOrder(order);
        if(order!=null){
            if(order.getComplateType()==null || order.getComplateType()==0){
                order.setComplateType(BaseDomain.COMPLATE_TYPE_ONE);
                order.setOrderStatus(BaseDomain.ORDER_STATUS_FOUR);
                userService.putOrder(order);
                //新增流转记录
                OrderHistoryStatus historyStatus = new OrderHistoryStatus();
                historyStatus.setHistoryStatus(BaseDomain.ORDER_STATUS_FOUR);
                historyStatus.setOrderId(order.getId());
                companyService.postOrderHistoryStatus(historyStatus,gauser.getUserId());
            }
        }
        message.setResult(ResultMessage.Result.SUCCESS);
        return mapper.writeValueAsString(message);
    }

    /**
     * 基本信息搜索
     */
    @RequestMapping(method = RequestMethod.POST,value = "/showOrderMessagePage",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showOrderMessagePage(OrderMessageSearcher orderMessageSearcher, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setOrderId(request.getParameter("orderId"));
        Page<OrderMessageSearcher> page = queryUserService.listOrderMessageWithPage(orderMessageSearcher, new Limit(orderMessageSearcher.getOffset(), orderMessageSearcher.getPageCount()));
        return mapper.writeValueAsString(page);
    }

}
