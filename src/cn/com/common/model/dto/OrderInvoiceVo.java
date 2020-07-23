package cn.com.common.model.dto;

import cn.com.common.agent.BaseEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Horace.zhang on 2019/6/25.
 */
@XmlRootElement(name = "orderInvoiceVo")
public class OrderInvoiceVo extends BaseEntity {



    /**
     * 发票类型：0.增值税专用发票,1.增值税普通发票
     */
    private Integer invoiceType;

    /**
     * 开票单位全称
     */
    private String invoiceUnitName;

    /**
     * 纳税人识别号（统一社会信用代码）
     */
    private String socialCreditCode;

    /**
     * 开户行
     */
    private String depositBank;

    /**
     * 账户
     */
    private String account;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 开票日期
     */
    private Date billingDate;

    private String orderId;

    /**
     * 父订单id
     */
    private String orderParentId;

    private String orderInvoiceId;

    private String orderProductId;



    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品型号
     */
    private String productType;

    /**
     * 开票数量
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

    public String getOrderInvoiceId() {
        return orderInvoiceId;
    }

    public void setOrderInvoiceId(String orderInvoiceId) {
        this.orderInvoiceId = orderInvoiceId;
    }

    public String getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceUnitName() {
        return invoiceUnitName;
    }

    public void setInvoiceUnitName(String invoiceUnitName) {
        this.invoiceUnitName = invoiceUnitName;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }
}
