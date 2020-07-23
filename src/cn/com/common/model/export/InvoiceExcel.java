package cn.com.common.model.export;

import cn.com.common.utils.ExcelVOAtteribute;

/**
 * 财务开票表
 * Created by Horace.zhang on 2019/7/17.
 */
public class InvoiceExcel {
    //开票日期
    @ExcelVOAtteribute(name="开票日期",column = "A")
    private String billingDate;
    //省份
    @ExcelVOAtteribute(name="省份",column = "B")
    private String provinceName;
    //地市
    @ExcelVOAtteribute(name="地市",column = "C")
    private String cityName;
    //开票单位
    @ExcelVOAtteribute(name="开票单位",column = "D")
    private String invoiceUnitName;
    //订购单位
//    @ExcelVOAtteribute(name="订购单位",column = "D")
//    private String orderUnitName;
    //代码
    @ExcelVOAtteribute(name="代码",column = "E")
    private String invoiceCode;
    //流水号
    @ExcelVOAtteribute(name="流水号",column = "F")
    private String flowId;
    //产品数量
    @ExcelVOAtteribute(name="产品数量",column = "G")
    private String productTotalCount;
    //单价
    @ExcelVOAtteribute(name="单价",column = "H")
    private String productPrice;
    //发票金额
    @ExcelVOAtteribute(name="发票金额",column = "I")
    private String invoiceTotalAmount;
    //到款日期
    @ExcelVOAtteribute(name="到款日期",column = "J")
    private String arrivalDate;
    //证书类别
    @ExcelVOAtteribute(name="证书类别",column = "K")
    private String productName;
    //证书型号
    @ExcelVOAtteribute(name="证书型号",column = "L")
    private String productType;
    //开票类型
    @ExcelVOAtteribute(name="开票类型",column = "M")
    private String invoiceType;
    //纳税人识别号（开票信息）
    @ExcelVOAtteribute(name="纳税人识别号（开票信息）",column = "N")
    private String socialCreditCode;
    //大区
    @ExcelVOAtteribute(name="大区",column = "O")
    private String regionName;
    //销售经理
    @ExcelVOAtteribute(name="销售经理",column = "P")
    private String saleManager;

    //导入失败原因
    @ExcelVOAtteribute(name="失败原因",column = "Q")
    private String error;

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
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

    public String getInvoiceUnitName() {
        return invoiceUnitName;
    }

    public void setInvoiceUnitName(String invoiceUnitName) {
        this.invoiceUnitName = invoiceUnitName;
    }

    //    public String getOrderUnitName() {
//        return orderUnitName;
//    }
//
//    public void setOrderUnitName(String orderUnitName) {
//        this.orderUnitName = orderUnitName;
//    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getProductTotalCount() {
        return productTotalCount;
    }

    public void setProductTotalCount(String productTotalCount) {
        this.productTotalCount = productTotalCount;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getInvoiceTotalAmount() {
        return invoiceTotalAmount;
    }

    public void setInvoiceTotalAmount(String invoiceTotalAmount) {
        this.invoiceTotalAmount = invoiceTotalAmount;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getSaleManager() {
        return saleManager;
    }

    public void setSaleManager(String saleManager) {
        this.saleManager = saleManager;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
