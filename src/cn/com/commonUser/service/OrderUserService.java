package cn.com.commonUser.service;

import cn.com.common.model.*;
import cn.com.common.model.dto.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/6/12.
 */
public interface OrderUserService {

    Order getOrder(Order order) throws Exception;

    Order getOrderById(String orderId) throws Exception;

    Order getOrderByFlowId(String flowId) throws Exception;

    Order getOrderByIdFlowId(String orderId,String flowId) throws Exception;

    List<Order> getOrderAll(Order order) throws Exception;

//  待完成订单数据
    List<Order> getOrderFinish(Order order) throws Exception;

    List<OrderProduct> getOrderProductByOrderId(String orderId) throws Exception;

    List<OrderProductExpressVo> getOrderProductExpressByOrderId(String orderId) throws Exception;

    List<OrderInvoice> getOrderInvoiceByOrderId(String orderId) throws Exception;

    List<OrderUploadFiles> getOrderUploadFilesByOrderId(String orderId) throws Exception;

    OrderUploadFiles getOrderUploadFilesById(String id) throws Exception;

    ReceiptUploadFiles getReceiptUploadFilesById(String id) throws Exception;

    List<ReceiptUploadFiles> getReceiptUploadFilesByOrderId(String orderId) throws Exception;

    ////获取发票类型为专用 字段值是否存在空
    List<OrderInvoice> getOrderInvoiceNotSourceNull(String orderId) throws Exception;

    OrderInvoice getOrderInvoiceById(String id) throws Exception;

    List<OrderInvoiceProduct> getOrderInvoiceProductByOrderId(String orderId) throws Exception;

    List<OrderInvoiceProduct> getOrderInvoiceProductByOrderInvoiceId(String orderInvoiceId) throws Exception;

    //获取orerInvoice and orderInvoiceProduct
    List<OrderInvoiceVo> getOrderInvoiceVo(String orderId)throws Exception;

    List<DeliveryAddress> getDeliveryAddress(DeliveryAddress address) throws Exception;

    DeliveryAddress getDeliveryAddressById(String addressId) throws Exception;

    DeliveryAddress getDeliveryAddressIsDefault(String userId) throws Exception;

    List<OrderExpress> getOrderExpressByOrderid(String orderId) throws Exception;

    OrderExpress getOrderExpressByProductionFlowId(String productionFlowId) throws Exception;

    OrderInvoiceExpress getOrderInvoiceExpressByFlowId(String flowId) throws Exception;


    //根据orderId,productListId排序
    List<OrderProduct> getOrderProduct(String orderId) throws Exception;

    OrderProduct getOrderProductByproductionFlowId(String productionFlowId) throws Exception;




    String setPostInvoice(OrderInvoiceSave orderInvoiceSave,String userId) throws Exception;

    String setPutInvoice(OrderInvoiceSave orderInvoiceSave,String userId) throws Exception;



    void postOrder(OrderVo order) throws Exception;

    String postOrderProduct(OrderVo product,GaUser gauser) throws Exception;

    String postProduct(OrderVo order,GaUser gauser) throws Exception;

    void postInvoice(OrderInvoice orderInvoice,String userId) throws Exception;

    void postInvoiceProduct(OrderInvoiceProduct orderInvoiceProduct,String userId) throws Exception;

    void  postAddress(DeliveryAddress address) throws Exception;

    void postOrderMessage(OrderMessage orderMessage) throws Exception;

    void postOrderUploadFiles(OrderUploadFiles uploadFiles,String userId) throws Exception;

    void postReceiptUploadFiles(ReceiptUploadFiles uploadFiles,String userId) throws Exception;


    void putOrder(List<Order> orders) throws Exception;

    void putOrder(Order order) throws Exception;

    void putOrderArea(OrderVo orderVo) throws Exception;

    void putOrderProduct(OrderProduct orderProduct) throws Exception;

    void putAddress(DeliveryAddress address) throws Exception;

    void putOrderInvoice(OrderInvoice orderInvoice,String userId) throws Exception;

    void putDeliveryAddressIsDefault(Integer isDefault,String userId,String id) throws Exception;

    void putOrderMessageWarn(String orderId,Boolean isWarn) throws Exception;


    void deleteOrderProduct(String orderProductId,String userId) throws Exception;

    void deleteOrderInvoiceById(String orderInvoice) throws Exception;

    void deleteDeliveryAddress(String id) throws Exception;

    void deleteOrderUploadFilesById(String id,String userId) throws Exception;

    void deleteReceiptUploadFilesById(String id,String userId) throws Exception;


    //获取未填写开票信息产品
    List<OrderProduct> getInvoiceNotOrderProduct() throws Exception;

    Boolean verifyOrderProduct(OrderProduct product) throws Exception;



}
