package cn.com.common.model.dto;

import cn.com.common.model.OrderInvoice;
import cn.com.common.model.OrderProduct;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/6/20.
 */
@XmlRootElement(name = "orderInvoiceSave")
public class OrderInvoiceSave {
    private OrderInvoice orderInvoice;
    private Map orderProductsMap;

    public OrderInvoice getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoice orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public Map getOrderProductsMap() {
        return orderProductsMap;
    }

    public void setOrderProductsMap(Map orderProductsMap) {
        this.orderProductsMap = orderProductsMap;
    }


}
