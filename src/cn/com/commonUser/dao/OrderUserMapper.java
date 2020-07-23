package cn.com.commonUser.dao;

import cn.com.common.model.*;
import cn.com.common.model.dto.OrderInvoiceVo;
import cn.com.common.model.dto.OrderProductExpressVo;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.search.OrderMessageSearcher;
import cn.com.common.model.search.OrderQuerySearcher;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/6/12.
 */
public interface OrderUserMapper {
    List<Order> getOrder(Order order);

    List<Order> getOrderFinish(Order order);

    List<OrderQuerySearcher> listWithOrderQuery(OrderQuerySearcher searcher);

    List<OrderMessageSearcher> listWithOrderMessage(OrderMessageSearcher message);



    void postOrder(OrderVo order);

    void postInvoice(OrderInvoice orderInvoice);

    void postInvoiceProduct(OrderInvoiceProduct orderInvoiceProduct);

    List<OrderInvoiceVo> getOrderInvoiceVo(String orderId);

    void postOrderProduct(OrderVo orderProduct);

    void postOrderMessage(OrderMessage orderMessage);

    void postOrderUploadFiles(OrderUploadFiles orderUploadFiles);


    void postReceiptUploadFiles(ReceiptUploadFiles receiptUploadFiles);




    void putOrder(Order order);

    void putOrderArea(OrderVo order);

    void putOrderProduct(OrderProduct orderProduct);

    void putOrderInvoice(OrderInvoice invoice);

    void putDeliveryAddressIsDefault(Map userId);

    void putAddress(DeliveryAddress address);

    void putOrderMessage(OrderMessage orderMessage);


    List<OrderProduct> getOrderProductByOrderId(String orderId);

    List<OrderProductExpressVo> getOrderProductExpressByOrderId(String orderId);

    List<OrderExpress> getOrderExpress(Map map);

    List<OrderInvoiceExpress> getOrderInvoiceExpress(Map map);

    //排序orderId,productListId
    List<OrderProduct> getOrderProduct(Map map);

    List<OrderUploadFiles> getOrderUploadFiles(Map map);

    List<ReceiptUploadFiles> getReceiptUploadFiles(Map map);

    //弃用
    List<OrderProduct> getInvoiceNotOrderProduct();


    List<OrderInvoice> getOrderInvoice(Map map);

    List<OrderInvoiceProduct> getOrderInvoiceProduct(Map map);

    //获取发票类型为专用 字段值是否存在空
    List<OrderInvoice> getOrderInvoiceNotSourceNull(String orderId);

    void deleteOrderProduct(Map map);

    void deleteOrderInvoiceProductByOrderInvoiceId(String orderInvoiceId);

    void deleteOrderInvoiceById(String id);

    void deleteOrderUploadFilesById(Map map);

    void deleteReceiptUploadFilesById(Map map);

    void postAddress(DeliveryAddress address);


    List<DeliveryAddress> getDeliveryAddress(DeliveryAddress address);

    DeliveryAddress getDeliveryAddressByMap(Map map);

    DeliveryAddress getDeliveryAddressIsDefault(String userId);



    void deleteDeliveryAddress(String id);
}
