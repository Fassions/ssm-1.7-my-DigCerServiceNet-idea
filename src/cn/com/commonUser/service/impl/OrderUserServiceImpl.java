package cn.com.commonUser.service.impl;

import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.*;
import cn.com.common.model.dto.*;
import cn.com.common.service.ProductService;
import cn.com.common.utils.*;
import cn.com.commonUser.dao.OrderUserMapper;
import cn.com.commonUser.service.OrderUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Horace.zhang on 2019/6/12.
 */
@Service("orderUserService")
public class OrderUserServiceImpl implements OrderUserService {
    @Autowired
    private OrderUserMapper orderUserMapper;

    @Autowired
    private ProductService productService;
    @Override
    public Order getOrder(Order order) throws Exception {
        List<Order> data = orderUserMapper.getOrder(order);
        if(data.size()==0){
            return null;
        }else {
            return data.get(0);
        }
    }

    @Override
    public Order getOrderById(String orderId) throws Exception {
        Order order = new Order();
        order.setId(orderId);
        List<Order> data = orderUserMapper.getOrder(order);
        if(data.size()==0){
            return null;
        }else {
            return data.get(0);
        }
    }

    @Override
    public Order getOrderByFlowId(String flowId) throws Exception {
        Order order = new Order();
        order.setFlowId(flowId);
        List<Order> data = orderUserMapper.getOrder(order);
        if(data.size()==0){
            return null;
        }else {
            return data.get(0);
        }
    }

    @Override
    public Order getOrderByIdFlowId(String orderId, String flowId) throws Exception {
        Order order = new Order();
        order.setFlowId(flowId);
        order.setId(orderId);
        List<Order> data = orderUserMapper.getOrder(order);
        if(data.size()==0){
            return null;
        }else {
            return data.get(0);
        }
    }

    @Override
    public List<Order> getOrderAll(Order order) throws Exception {
        List<Order> data = orderUserMapper.getOrder(order);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }

    @Override
    public List<Order> getOrderFinish(Order order) throws Exception {
        List<Order> data = orderUserMapper.getOrderFinish(order);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }



    @Override
    public List<OrderInvoice> getOrderInvoiceByOrderId(String orderId) throws Exception {
        if(org.apache.commons.lang3.StringUtils.isBlank(orderId)){
            return null;
        }
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderInvoice> data = orderUserMapper.getOrderInvoice(map);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }

    @Override
    public List<OrderUploadFiles> getOrderUploadFilesByOrderId(String orderId) throws Exception {
        if(org.apache.commons.lang3.StringUtils.isBlank(orderId)){
            return null;
        }
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderUploadFiles> data = orderUserMapper.getOrderUploadFiles(map);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }

    @Override
    public OrderUploadFiles getOrderUploadFilesById(String id) throws Exception {
        if(org.apache.commons.lang3.StringUtils.isBlank(id)){
            return null;
        }
        Map map = new HashMap<>();
        map.put("id",id);
        List<OrderUploadFiles> data = orderUserMapper.getOrderUploadFiles(map);
        if(data.size()==0){
            return null;
        }else {
            return data.get(0);
        }
    }

    @Override
    public ReceiptUploadFiles getReceiptUploadFilesById(String id) throws Exception {
        if(org.apache.commons.lang3.StringUtils.isBlank(id)){
            return null;
        }
        Map map = new HashMap<>();
        map.put("id",id);
        List<ReceiptUploadFiles> data = orderUserMapper.getReceiptUploadFiles(map);
        if(data.size()==0){
            return null;
        }else {
            return data.get(0);
        }
    }

    @Override
    public List<ReceiptUploadFiles> getReceiptUploadFilesByOrderId(String orderId) throws Exception {
        if(org.apache.commons.lang3.StringUtils.isBlank(orderId)){
            return null;
        }
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<ReceiptUploadFiles> data = orderUserMapper.getReceiptUploadFiles(map);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }

    @Override
    public List<OrderInvoice> getOrderInvoiceNotSourceNull(String orderId) throws Exception {
        List<OrderInvoice> data = orderUserMapper.getOrderInvoiceNotSourceNull(orderId);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }


    @Override
    public OrderInvoice getOrderInvoiceById(String id) throws Exception {
        if(StringUtils.isBlank(id)){
            return null;
        }
        Map map = new HashMap<>();
        map.put("id",id);
        List<OrderInvoice> data = orderUserMapper.getOrderInvoice(map);
        if(data.size()==0){
            return null;
        }else {
            return data.get(0);
        }
    }


    @Override
    public List<OrderInvoiceProduct> getOrderInvoiceProductByOrderId(String orderId) throws Exception {
        if(StringUtils.isBlank(orderId)){
            return null;
        }
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderInvoiceProduct> data = orderUserMapper.getOrderInvoiceProduct(map);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }

    }

    @Override
    public List<OrderInvoiceProduct> getOrderInvoiceProductByOrderInvoiceId(String orderInvoiceId) throws Exception {
        if(StringUtils.isBlank(orderInvoiceId)){
            return null;
        }

        Map map = new HashMap<>();
        map.put("orderInvoiceId",orderInvoiceId);
        List<OrderInvoiceProduct> data = orderUserMapper.getOrderInvoiceProduct(map);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }

    @Override
    public List<OrderInvoiceVo> getOrderInvoiceVo(String orderId) throws Exception {
        List<OrderInvoiceVo> data = orderUserMapper.getOrderInvoiceVo(orderId);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }

    @Override
    public void postOrder(OrderVo order) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(order.getId(),order.getUserId()),order);
        orderUserMapper.postOrder(order);
    }

    @Override
    public String postOrderProduct(OrderVo vo,GaUser gauser) throws Exception {

        ProductUtils.getlabelStartEndNumberAmount(vo);  //拼接需要字段值
        if (!"0".equals(vo.getProductListId())) {  //配件不需要验证
			OrderProduct product = new OrderProduct();
			BeanUtilsEx.copyProperties(vo,product);
			// 管理员允许证书编号重复添加
			if(verifyOrderProduct(product)) {
				if (gauser.getUserType()!=1) {
					return "订购的产品编号已被使用，请重新选购。";
				} else {
					//更新订单地区
					orderUserMapper.putOrderArea(vo);
					vo.setIdUUID();
			        BeanUtilsEx.copyProperties(PutDateSource.createDate(vo.getId(),vo.getUserId()),vo);
			        orderUserMapper.postOrderProduct(vo);
			        return "请注意，订购的产品编号已被使用。";
				}
			} else {
				//更新订单地区
				orderUserMapper.putOrderArea(vo);
			}
        }
        vo.setIdUUID();
        BeanUtilsEx.copyProperties(PutDateSource.createDate(vo.getId(),vo.getUserId()),vo);
        orderUserMapper.postOrderProduct(vo);
        return null;
    }


    /**
     * 验证证书产品是否已经被使用
     * @param product
     * @return true 已使用 false 未使用
     * @throws Exception
     */
    public Boolean verifyOrderProduct(OrderProduct product) throws Exception{

        return productService.verifyOrderProductBetween(product);
    }

    /**
     * 验证证书产品是否需要被合并
//     * @param vo
     * @return true 已使用 false 未使用
     * @throws Exception
     */
//    private Boolean verifyOrderProduct(OrderVo vo) throws Exception{
//        return productService.verifyOrderProductBetween(vo.getLabelStart(),vo.getLabelEnd());
//    }


    @Override
    public String postProduct(OrderVo orderVo,GaUser gauser) throws Exception {
        Order paramOrder = new Order();
//        paramOrder.setUserId(orderVo.getUserId());
//        paramOrder.setSubmitStatus(orderVo.getSubmitStatus());
        paramOrder.setId(orderVo.getId());
        Order order = getOrder(paramOrder);
        if(order==null){
            return "订单错误，请重试。";
//            postOrder(orderVo);
//            order = getOrder(paramOrder);
        }
        orderVo.setOrderId(order.getId());
        return postOrderProduct(orderVo, gauser);
    }

    @Override
    public void postInvoice(OrderInvoice orderInvoice,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(orderInvoice.getId(),userId),orderInvoice);
        orderUserMapper.postInvoice(orderInvoice);
    }

    @Override
    public String setPostInvoice(OrderInvoiceSave orderInvoiceSave,String userId) throws Exception {
        orderInvoiceSave.getOrderInvoice().setIdUUID();
        Map<String,Object> map = orderInvoiceSave.getOrderProductsMap();
        if(map.size()==0){
            return "开票所需证书不能为空";
        }
        for(String key:map.keySet()){
            OrderInvoiceProduct product = new OrderInvoiceProduct();
            product.setOrderInvoiceId(orderInvoiceSave.getOrderInvoice().getId());
            String str = (String) map.get(key);
            if(org.apache.commons.lang3.StringUtils.isNotBlank(str)&&str.contains(";")){
                String[] arr = str.split(";");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[0]))
                    product.setOrderId(arr[0]);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[1]))
                    product.setOrderProductId(arr[1]);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[2]))
                    product.setProductName(arr[2]);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[3]))
                    product.setProductType(arr[3]);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[4]))
                    product.setProductNumber(Integer.parseInt(arr[4]));
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[5]))
                    product.setProductPrice(Double.parseDouble(arr[5]));
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[6]))
                    product.setProductAmount(Double.parseDouble(arr[6]));
            }
//            OrderInvoiceProduct invoiceProduct = getOrderInvoiceProduct(product);
//            if(invoiceProduct!=null) {
//                return "已添加，请勿重复操作";
//            }else{
                postInvoiceProduct(product, userId);
//            }
        }

        postInvoice(orderInvoiceSave.getOrderInvoice(),userId);
        return null;
    }
    @Override
    public String setPutInvoice(OrderInvoiceSave orderInvoiceSave,String userId) throws Exception {
        if(StringUtils.isBlank(orderInvoiceSave.getOrderInvoice().getId())){
            return "请选择开票信息";
        }
        Map<String,Object> map = orderInvoiceSave.getOrderProductsMap();
        if(map.size()==0){
            return "开票所需证书不能为空";
        }
        orderUserMapper.deleteOrderInvoiceProductByOrderInvoiceId(orderInvoiceSave.getOrderInvoice().getId());
        for(String key:map.keySet()){
            OrderInvoiceProduct product = new OrderInvoiceProduct();
            product.setOrderInvoiceId(orderInvoiceSave.getOrderInvoice().getId());
            String str = (String) map.get(key);
            if(org.apache.commons.lang3.StringUtils.isNotBlank(str)&&str.contains(";")){
                String[] arr = str.split(";");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[0]))
                    product.setOrderId(arr[0]);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[1]))
                    product.setOrderProductId(arr[1]);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[2]))
                    product.setProductName(arr[2]);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[3]))
                    product.setProductType(arr[3]);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[4]))
                    product.setProductNumber(Integer.parseInt(arr[4]));
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[5]))
                    product.setProductPrice(Double.parseDouble(arr[5]));
                if(org.apache.commons.lang3.StringUtils.isNotBlank(arr[6]))
                    product.setProductAmount(Double.parseDouble(arr[6]));
            }
//            OrderInvoiceProduct invoiceProduct = getOrderInvoiceProduct(product);
//            if(invoiceProduct!=null) {
//                return "已添加，请勿重复操作";
//            }else{
            postInvoiceProduct(product, userId);
//            }
        }
        putOrderInvoice(orderInvoiceSave.getOrderInvoice(),userId);
        return null;
    }

    @Override
    public void postInvoiceProduct(OrderInvoiceProduct orderInvoiceProduct,String userId) throws Exception {

        BeanUtilsEx.copyProperties(PutDateSource.createDate(orderInvoiceProduct.getId(),userId),orderInvoiceProduct);
        orderUserMapper.postInvoiceProduct(orderInvoiceProduct);
    }

    @Override
    public void deleteOrderProduct(String orderProductId,String userId) throws Exception {
        Map map = new HashMap<>();
        map.put("orderProductId",orderProductId);
        map.put("dateModified",new Date());
        map.put("userModified",userId);
        orderUserMapper.deleteOrderProduct(map);
    }

    @Override
    public void deleteOrderInvoiceById(String orderInvoice) throws Exception {
        orderUserMapper.deleteOrderInvoiceProductByOrderInvoiceId(orderInvoice);
        orderUserMapper.deleteOrderInvoiceById(orderInvoice);
    }

    @Override
    public List<OrderProduct> getOrderProductByOrderId(String orderId) throws Exception {
        if(org.apache.commons.lang3.StringUtils.isBlank(orderId)){
            return null;
        }
        List<OrderProduct> products = orderUserMapper.getOrderProductByOrderId(orderId);
        if(products.size()==0){
            return null;
        }else {
            return products;
        }
    }

    @Override
    public List<OrderProductExpressVo> getOrderProductExpressByOrderId(String orderId) throws Exception {
        if(org.apache.commons.lang3.StringUtils.isBlank(orderId)){
            return null;
        }
        List<OrderProductExpressVo> products = orderUserMapper.getOrderProductExpressByOrderId(orderId);
        if(products.size()==0){
            return null;
        }else {
            return products;
        }
    }


    @Override
    public List<OrderProduct> getInvoiceNotOrderProduct() throws Exception {
        List<OrderProduct> products = orderUserMapper.getInvoiceNotOrderProduct();
        if(products.size()==0){
            return null;
        }else {
            return products;
        }
    }

    @Override
    public void postAddress(DeliveryAddress address) throws Exception {
//        if(Objects.equals(BaseDomain.IS_DEFAULT_DONE,address.getIsDefault())){ //选择默认地址 需变更其他地址
//            putDeliveryAddressIsDefault(BaseDomain.IS_DEFAULT_NONE,address.getUserId(),null);
//        }
        BeanUtilsEx.copyProperties(PutDateSource.createDate(address.getId(),address.getUserId()),address);
        orderUserMapper.postAddress(address);
    }

    @Override
    public void postOrderMessage(OrderMessage orderMessage) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(orderMessage.getId(),orderMessage.getUserId()),orderMessage);
        orderUserMapper.postOrderMessage(orderMessage);
    }

    @Override
    public void postOrderUploadFiles(OrderUploadFiles uploadFiles,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(uploadFiles.getId(),userId),uploadFiles);
        orderUserMapper.postOrderUploadFiles(uploadFiles);
    }

    @Override
    public void postReceiptUploadFiles(ReceiptUploadFiles uploadFiles,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(uploadFiles.getId(),userId),uploadFiles);
        orderUserMapper.postReceiptUploadFiles(uploadFiles);
    }

    @Override
    public void putOrder(List<Order> orders) throws Exception {
        for(Order order:orders){
            orderUserMapper.putOrder(order);
        }
    }

    @Override
    public void putOrder(Order order) throws Exception {
        orderUserMapper.putOrder(order);
    }
    @Override
    public void putOrderArea(OrderVo orderVo) throws Exception {
        orderUserMapper.putOrderArea(orderVo);
    }

    @Override
    public void putOrderProduct(OrderProduct orderProduct) throws Exception {
        orderUserMapper.putOrderProduct(orderProduct);
    }

    @Override
    public void putAddress(DeliveryAddress address) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(address.getId(),address.getUserId()),address);
        orderUserMapper.putAddress(address);
    }

    @Override
    public void putOrderInvoice(OrderInvoice orderInvoice,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(orderInvoice.getId(),userId),orderInvoice);
        orderUserMapper.putOrderInvoice(orderInvoice);
    }

    @Override
    public List<DeliveryAddress> getDeliveryAddress(DeliveryAddress address) throws Exception {

        List<DeliveryAddress> deliveryAddress = orderUserMapper.getDeliveryAddress(address);
        if(deliveryAddress.size()==0) {
            return null;
        }else {
            return deliveryAddress;
        }
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(String addressId) throws Exception {
        Map map = new HashMap<>();
        map.put("id",addressId);
        DeliveryAddress deliveryAddress = orderUserMapper.getDeliveryAddressByMap(map);
            return deliveryAddress;
    }

    @Override
    public DeliveryAddress getDeliveryAddressIsDefault(String userId) throws Exception {
        return orderUserMapper.getDeliveryAddressIsDefault(userId);
    }

    @Override
    public List<OrderExpress> getOrderExpressByOrderid(String orderId) throws Exception {
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderExpress> data = orderUserMapper.getOrderExpress(map);
        if(data.size()==0) {
            return null;
        }else{
            return data;
        }

    }

    @Override
    public OrderExpress getOrderExpressByProductionFlowId(String productionFlowId) throws Exception {
        Map map = new HashMap<>();
        map.put("productionFlowId",productionFlowId);
        List<OrderExpress> data = orderUserMapper.getOrderExpress(map);
        if(data.size()==0) {
            return null;
        }else{
            return data.get(0);
        }
    }

    @Override
    public OrderInvoiceExpress getOrderInvoiceExpressByFlowId(String flowId) throws Exception{
        if (flowId==null) {
        	return null;
        }
    	Map map = new HashMap<>();
        map.put("flowId",flowId);
        List<OrderInvoiceExpress> data = orderUserMapper.getOrderInvoiceExpress(map);
        if(data.size()==0) {
            return null;
        }else{
            return data.get(0);
        }
    }

    @Override
    public List<OrderProduct> getOrderProduct(String orderId) throws Exception {
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderProduct> data = orderUserMapper.getOrderProduct(map);
        if(data.size()==0) {
            return null;
        }else{
            return data;
        }
    }

    @Override
    public OrderProduct getOrderProductByproductionFlowId(String productionFlowId) throws Exception {
        Map map = new HashMap<>();
        map.put("productionFlowId",productionFlowId);
        List<OrderProduct> data = orderUserMapper.getOrderProduct(map);
        if(data.size()==0) {
            return null;
        }else{
            return data.get(0);
        }
    }


    @Override
    public void putDeliveryAddressIsDefault(Integer isDefault,String userId,String id) throws Exception {
        Map map = new HashMap<>();
        map.put("userId",userId);
        map.put("id",id);
        map.put("isDefault",isDefault);
        orderUserMapper.putDeliveryAddressIsDefault(map);
    }

    @Override
    public void putOrderMessageWarn(String orderId, Boolean isWarn) throws Exception {
        OrderMessage message = new OrderMessage();
        message.setOrderId(orderId);
        message.setWarn(isWarn);
        orderUserMapper.putOrderMessage(message);
    }

    @Override
    public void deleteDeliveryAddress(String id) throws Exception {
        orderUserMapper.deleteDeliveryAddress(id);
    }

    @Override
    public void deleteOrderUploadFilesById(String id,String userId) throws Exception {
        OrderUploadFiles orderUploadFiles = getOrderUploadFilesById(id);
        if(orderUploadFiles!=null){
            ZipUtils.deleteDir(orderUploadFiles.getFileServerName());
        }
        Map map = new HashMap<>();
        map.put("id",id);
        map.put("dateModified",new Date());
        map.put("userModified",userId);
        orderUserMapper.deleteOrderUploadFilesById(map);
    }

    @Override
    public void deleteReceiptUploadFilesById(String id,String userId) throws Exception {
        ReceiptUploadFiles orderUploadFiles = getReceiptUploadFilesById(id);
        if(orderUploadFiles!=null){
            ZipUtils.deleteDir(orderUploadFiles.getFileServerName());
        }
        Map map = new HashMap<>();
        map.put("id",id);
        map.put("dateModified",new Date());
        map.put("userModified",userId);
        orderUserMapper.deleteReceiptUploadFilesById(map);
    }


    private Order assemOrder(OrderVo orderVo){
        Order order = new Order();
        BeanUtilsEx.copyProperties(orderVo,order);
        return order;
    }
}
