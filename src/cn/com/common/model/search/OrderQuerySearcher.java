package cn.com.common.model.search;

import cn.com.common.agent.BaseEntity;
import cn.com.common.model.Order;
import cn.com.common.model.OrderProduct;
import cn.com.common.model.dto.OrderProductExpressVo;
import cn.com.common.utils.IntegerUtil;
import org.codehaus.jettison.json.JSONArray;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/1.
 */
public class OrderQuerySearcher extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 8043040556366303052L;
    //每页显示总页数
    private int pageCount;
    //当前页数
    private int offset;
    private String productName;
    private String productName1;        //select下拉选择值

    //开票方式
    private String needBeforeInvoice;

    private String productNumber;
    private String orderDateStart;
    private String orderDateEnd;
    //0当前用户 1公司用户
    private String orderUser;

    private String userId;

    private String userName;
    private String certificateCode;

    /**
     * 订购单位
     */
    private String orderUnitName;

    //order
    //订单号
    private String flowId;

    private Date orderDate;

    //是否借转销 0未开启（默认） 1已开启
    private String borrowType;

    //订单状态
    private String orderStatus;

    //退回原因
    private String returnReason;

    //收货联系人
    private String receiveName;

    //订单id
    private String orderId;

    //确认收货：0未确认，1用户确认2.系统确认
    private Integer complateType;

    //orderProduct
    List<OrderProduct> orderProducts;

    //产品&快递
    List<OrderProductExpressVo> orderProductExpressVos;


    private String provinceCode;
    private String cityCode;
    private String provinceName;
    private String cityName;
    private String purchaser;
    private String purchaserMobile;
    private String  productTotalCount;
    private String productTotalAmount;
    private String orderType;
    private String orderInvoiceStatus;

    // 下单时间选择类型 "0"其他 1 今天 2本周 3本月 4本季 5本年
    private String orderDateType;

    //消息提醒 false不需要提醒 true需要提醒
    private Boolean isWarn;
    //发货日期
    private String deliveryDate;
    //订单类型
    private List<Object> orderTypeArray;

    //订单状态
    private List<Object> orderStatusArray;

    private String orderTypeArrays;
    private String orderStatusArrays;




    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getOrderDateStart() {
        return orderDateStart;
    }

    public void setOrderDateStart(String orderDateStart) {
        this.orderDateStart = orderDateStart;
    }

    public String getOrderDateEnd() {
        return orderDateEnd;
    }

    public void setOrderDateEnd(String orderDateEnd) {
        this.orderDateEnd = orderDateEnd;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }


    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getComplateType() {
        return complateType;
    }

    public void setComplateType(Integer complateType) {
        this.complateType = complateType;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {

        this.orderProducts = orderProducts;
    }

    public String getOrderUnitName() {
        return orderUnitName;
    }

    public void setOrderUnitName(String orderUnitName) {
        this.orderUnitName = orderUnitName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchaserMobile() {
        return purchaserMobile;
    }

    public void setPurchaserMobile(String purchaserMobile) {
        this.purchaserMobile = purchaserMobile;
    }

    public String getProductTotalCount() {
        return productTotalCount;
    }

    public void setProductTotalCount(String productTotalCount) {
        this.productTotalCount = productTotalCount;
    }

    public String getProductTotalAmount() {
        return productTotalAmount;
    }

    public void setProductTotalAmount(String productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderInvoiceStatus() {
        return orderInvoiceStatus;
    }

    public void setOrderInvoiceStatus(String orderInvoiceStatus) {
        this.orderInvoiceStatus = orderInvoiceStatus;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Object> getOrderTypeArray() {
        return orderTypeArray;
    }

    public void setOrderTypeArray(List<Object> orderTypeArray) {
        this.orderTypeArray = orderTypeArray;
    }

    public List<Object> getOrderStatusArray() {
        return orderStatusArray;
    }

    public void setOrderStatusArray(List<Object> orderStatusArray) {
        this.orderStatusArray = orderStatusArray;
    }

    public String getOrderTypeArrays() {
        return orderTypeArrays;
    }

    public void setOrderTypeArrays(String orderTypeArrays) {
        this.orderTypeArrays = orderTypeArrays;
    }

    public String getOrderStatusArrays() {
        return orderStatusArrays;
    }

    public void setOrderStatusArrays(String orderStatusArrays) {
        this.orderStatusArrays = orderStatusArrays;
    }

    public String getOrderDateType() {
        return orderDateType;
    }

    public void setOrderDateType(String orderDateType) {
        this.orderDateType = orderDateType;
    }

    public Boolean getWarn() {
        return isWarn;
    }

    public void setWarn(Boolean warn) {
        isWarn = warn;
    }

    public String getProductName1() {
        return productName1;
    }

    public void setProductName1(String productName1) {
        this.productName1 = productName1;
    }

    public String getNeedBeforeInvoice() {
        return needBeforeInvoice;
    }

    public void setNeedBeforeInvoice(String needBeforeInvoice) {
        this.needBeforeInvoice = needBeforeInvoice;
    }

    public List<OrderProductExpressVo> getOrderProductExpressVos() {
        return orderProductExpressVos;
    }

    public void setOrderProductExpressVos(List<OrderProductExpressVo> orderProductExpressVos) {
        this.orderProductExpressVos = orderProductExpressVos;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }
}
