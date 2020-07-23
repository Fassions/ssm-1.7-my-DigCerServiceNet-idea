package cn.com.commonUser.controller;

import cn.com.common.agent.BaseDomain;
import cn.com.common.model.*;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.service.CommonService;
import cn.com.common.utils.ResultMessage;
import cn.com.commonUser.service.OrderUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Horace.zhang on 2019/6/14.
 */
@Controller
@RequestMapping("commonUserAddress")
public class AddressController {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static  final ResultMessage message = new ResultMessage();

    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderUserService orderUserService;

    @RequestMapping(method = RequestMethod.GET,value = "/showAddressInfo")
    public ModelAndView showAddressInfo(HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        DeliveryAddress address = new DeliveryAddress();
        address.setUserId(gauser.getUserId());
        List<DeliveryAddress> addresses = orderUserService.getDeliveryAddress(address);
        request.setAttribute("addresses", addresses);
        return new ModelAndView("CommonUser/address/chooseAddress");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/createAddress")
    public ModelAndView createAddress(HttpServletRequest request) throws Exception {
        String orderUnitName = request.getParameter("orderUnitName");
        request.setAttribute("orderUnitName",orderUnitName);
        return new ModelAndView("CommonUser/address/detailsAddress");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/editAddress")
    public ModelAndView editAddress(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        DeliveryAddress address = new DeliveryAddress();
        address.setId(id);
        request.setAttribute("address",orderUserService.getDeliveryAddress(address)!=null?orderUserService.getDeliveryAddress(address).get(0):null);
        return new ModelAndView("CommonUser/address/detailsAddress");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/postAddress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String postAddress(DeliveryAddress address, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            return "未登陆";
        }
        address.setUserId(gauser.getUserId());
        orderUserService.postAddress(address);
        return mapper.writeValueAsString("加入成功");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/putAddress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String putAddress(DeliveryAddress address, HttpServletRequest request) throws Exception {
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            return "未登陆";
        }
        address.setUserId(gauser.getUserId());
        orderUserService.putAddress(address);
        return mapper.writeValueAsString("修改成功");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/editIsDefault",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String editIsDefault(HttpServletRequest request) throws Exception {
        String addressId = request.getParameter("id");
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            return "未登陆";
        }
        orderUserService.putDeliveryAddressIsDefault(BaseDomain.IS_DEFAULT_NONE,gauser.getUserId(),null);
        orderUserService.putDeliveryAddressIsDefault(BaseDomain.IS_DEFAULT_DONE,gauser.getUserId(),addressId);
        return mapper.writeValueAsString("操作成功");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/clickOrderAddress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String clickOrderAddress(HttpServletRequest request) throws Exception {
        String addressId = request.getParameter("id");
        if(StringUtils.isBlank(addressId)){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择收货地址。");
            return mapper.writeValueAsString(message);
        }
        DeliveryAddress address = orderUserService.getDeliveryAddressById(addressId);
        if(address==null){
            message.setResult(ResultMessage.Result.ERROR);
            message.setErrorMsg("请重新选择收货地址!");
            return mapper.writeValueAsString(message);
        }
        return mapper.writeValueAsString(address);
    }

    //选定地址 添加order
//    @RequestMapping(method = RequestMethod.POST,value = "/putOrderAddress",produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public String putOrderAddress(HttpServletRequest request) throws Exception {
//        String addressId = request.getParameter("id");
//        String orderId = request.getParameter("orderId");
//        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
//        if(gauser==null){
//            return "未登陆";
//        }else if(StringUtils.isAnyBlank(addressId)){
//            return "请选择一个地址";
//        }else if(StringUtils.isAnyBlank(orderId)){
//            return "请选择订单";
//        }
//        DeliveryAddress deliveryAddress = new DeliveryAddress();
//        OrderVo orderVo = new OrderVo();
//        deliveryAddress.setId(addressId);
//        List<DeliveryAddress> address = orderUserService.getDeliveryAddress(deliveryAddress);
//        if(address!=null){
//            deliveryAddress = address.get(0);
//            BeanUtilsEx.copyProperties(deliveryAddress,orderVo);
//            orderVo.setId(orderId);
//            orderUserService.putOrderArea(orderVo);
//            return mapper.writeValueAsString("选定成功");
//        }
//        return null;
//    }

    @RequestMapping(method = RequestMethod.POST,value = "/deleteAddress",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteAddress(HttpServletRequest request) throws Exception {
        String addressId = request.getParameter("id");
        GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
        if(gauser==null){
            return "未登陆";
        }
        orderUserService.deleteDeliveryAddress(addressId);
        return mapper.writeValueAsString("删除成功");
    }

}
