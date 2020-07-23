package cn.com.common.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Horace.zhang on 2020/1/6.
 */
@XmlRootElement(name = "OrderProductExpressVo")
public class OrderProductExpressVo {

    /**
     * 流水生产号（相同证书类型+型号+前缀编号相同 同一批一个字段流水号，不同去查有没有其他子订单，累加数值）
     */
    private Double flowNumber;


    private String productListId;

    /**
     * 产品类型名称
     */
    private String productName;

    /**
     * 供货方 默认 3 辰锐
     */
    private String supplier;

    /**
     * 产品型号
     */
    private String productType;

    /**
     * 产品标号后缀
     */
    private String labelSuffix;

    /**
     * 产品标号前缀（证书省份开头）
     */
    private String labelPrefix;

    /**
     * 证书起始编号
     */
    private String labelStart;

    /**
     * 证书结束编号
     */
    private String labelEnd;

    /**
     * 证书数字部分开始编号
     */
    private String labelNumStart;

    /**
     * 证书数字部分结束编号
     */
    private String labelNumEnd;

    /**
     * 数量
     */
    private Integer productNumber;

    /**
     * 单价
     */
    private Double productPrice;

    /**
     * 产品总金额
     */
    private Double productAmount;

    /**
     * 快递信息表id
     */
    private String expressId;

    /**
     * 生产任务单号
     */
    private String productionFlowId;
    /**
     * 订单id
     */
    private String orderId;

    /**
     * 父订单id
     */
    private String orderParentId;

    /**
     * 快递发运物品：1.发票，2.证书，3.证书和发票（一起邮寄）
     */
    private Integer expressType;

    /**
     * 快递公司名称
     */
    private String expressName;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 发货日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    /**
     * 快递信息备注
     */
    private String expressMessage;

    public Double getFlowNumber() {
        return flowNumber;
    }

    public void setFlowNumber(Double flowNumber) {
        this.flowNumber = flowNumber;
    }

    public String getProductListId() {
        return productListId;
    }

    public void setProductListId(String productListId) {
        this.productListId = productListId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getLabelSuffix() {
        return labelSuffix;
    }

    public void setLabelSuffix(String labelSuffix) {
        this.labelSuffix = labelSuffix;
    }

    public String getLabelPrefix() {
        return labelPrefix;
    }

    public void setLabelPrefix(String labelPrefix) {
        this.labelPrefix = labelPrefix;
    }

    public String getLabelStart() {
        return labelStart;
    }

    public void setLabelStart(String labelStart) {
        this.labelStart = labelStart;
    }

    public String getLabelEnd() {
        return labelEnd;
    }

    public void setLabelEnd(String labelEnd) {
        this.labelEnd = labelEnd;
    }

    public String getLabelNumStart() {
        return labelNumStart;
    }

    public void setLabelNumStart(String labelNumStart) {
        this.labelNumStart = labelNumStart;
    }

    public String getLabelNumEnd() {
        return labelNumEnd;
    }

    public void setLabelNumEnd(String labelNumEnd) {
        this.labelNumEnd = labelNumEnd;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductionFlowId() {
        return productionFlowId;
    }

    public void setProductionFlowId(String productionFlowId) {
        this.productionFlowId = productionFlowId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderParentId() {
        return orderParentId;
    }

    public void setOrderParentId(String orderParentId) {
        this.orderParentId = orderParentId;
    }

    public Integer getExpressType() {
        return expressType;
    }

    public void setExpressType(Integer expressType) {
        this.expressType = expressType;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getExpressMessage() {
        return expressMessage;
    }

    public void setExpressMessage(String expressMessage) {
        this.expressMessage = expressMessage;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }
}
