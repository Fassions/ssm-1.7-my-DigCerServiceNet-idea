package cn.com.common.model.dto;

import cn.com.common.agent.BaseEntity;

import java.io.Serializable;

/**
*CreatedbyHorace.zhangon2019/7/17.
*excel数据导出总字段
*/
public class TemPlateExcelVo extends BaseEntity implements Serializable{

//private static final long serialVersionUID =-1316032673274384585L;

    private String orderId;

    //生产任务单号
    private String productionFlowId;
    //生产注意事项
    private String productionMemo;
    //生产审单人
    private String auditor;
    //生产审单日期
    private String auditorDate;
    //开票方式(0后开票，1先开票)
    private String needBeforeInvoice;
    //证书编号
    private String labelCode;
    //订购日期
    private String orderDate;
    //省份
    private String provinceName;
    //地市
    private String cityName;
    //订购单位
    private String orderUnitName;
    //开票单位
    private String invoiceUnitName;
    //流水号
    private String flowId;
    //数量
    private String productTotalCount;
    //单价
    private String productPrice;
    //金额
    private String productTotalAmount;
    //产品省份字母
    private String prefixName;
    //供货方
    private String supplier;
    //起始号
    private String labelStart;
    //末尾号
    private String labelEnd;
    //产品字母
    private String labelSuffix;
    //发货日期
    private String deliveryDate;
    //发货数
    private String expressTotalCount;
    //发货金额
    private String expressTotalAmount;
    //类型
    private String orderType;
    //到款账户
    private String arrivalAccount;
    //发票数量
    private String invoiceNum;
    //开票日期
    private String billingDate;
    //到款日期
    private String arrivalDate;
    //证书型号Id
    private String productListId;
    //证书类别
    private String productName;
    //证书型号
    private String productType;
    //大区
    private String regionName;
    //销售经理
    private String saleManager;
    //收件人
    private String receiveName;
    //收件固定电话
    private String receivePhone;
    //收件手机号
    private String receiveMobile;
    //收件地址
    private String receiveUnitAddress;
    //开票信息
    private String socialCreditCode;
    //发票快递单
    private String invoiceExpressNo;
    //证书快递单
    private String productExpressNo;
    //证书和发票快递单
    private String productInvoiceExpressNo;
    //备忘录
    private String memo;

    //发运物品（品名）
    private String expressType;
    //收件单位
    private String receiveUnitName;
    //开票类型
    private String invoiceType;
    //发票金额
    private String invoiceTotalAmount;
    //发货单号
    private String expressNo;
    /**
     * 快递信息备注
     */
    private String expressMessage;

    //系统模板多出字段
    private String county;
    private String empty;
    private String empty1;
    private String bourn;
    private String invoiceCode; //代码
    private String invoiceId;


    public String getInvoiceId() {
        return invoiceId;
    }

    public String getInvoiceUnitName() {
        return invoiceUnitName;
    }

    public void setInvoiceUnitName(String invoiceUnitName) {
        this.invoiceUnitName = invoiceUnitName;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getBourn() {
        return bourn;
    }

    public void setBourn(String bourn) {
        this.bourn = bourn;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getEmpty1() {
        return empty1;
    }

    public void setEmpty1(String empty1) {
        this.empty1 = empty1;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    public String getOrderUnitName() {
        return orderUnitName;
    }

    public void setOrderUnitName(String orderUnitName) {
        this.orderUnitName = orderUnitName;
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

    public String getProductTotalAmount() {
        return productTotalAmount;
    }

    public void setProductTotalAmount(String productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public String getLabelSuffix() {
        return labelSuffix;
    }

    public void setLabelSuffix(String labelSuffix) {
        this.labelSuffix = labelSuffix;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getExpressTotalCount() {
        return expressTotalCount;
    }

    public void setExpressTotalCount(String expressTotalCount) {
        this.expressTotalCount = expressTotalCount;
    }

    public String getExpressTotalAmount() {
        return expressTotalAmount;
    }

    public void setExpressTotalAmount(String expressTotalAmount) {
        this.expressTotalAmount = expressTotalAmount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getArrivalAccount() {
        return arrivalAccount;
    }

    public void setArrivalAccount(String arrivalAccount) {
        this.arrivalAccount = arrivalAccount;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
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

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getReceiveUnitAddress() {
        return receiveUnitAddress;
    }

    public void setReceiveUnitAddress(String receiveUnitAddress) {
        this.receiveUnitAddress = receiveUnitAddress;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getInvoiceExpressNo() {
        return invoiceExpressNo;
    }

    public void setInvoiceExpressNo(String invoiceExpressNo) {
        this.invoiceExpressNo = invoiceExpressNo;
    }

    public String getProductExpressNo() {
        return productExpressNo;
    }

    public void setProductExpressNo(String productExpressNo) {
        this.productExpressNo = productExpressNo;
    }

    public String getProductInvoiceExpressNo() {
        return productInvoiceExpressNo;
    }

    public void setProductInvoiceExpressNo(String productInvoiceExpressNo) {
        this.productInvoiceExpressNo = productInvoiceExpressNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public String getReceiveUnitName() {
        return receiveUnitName;
    }

    public void setReceiveUnitName(String receiveUnitName) {
        this.receiveUnitName = receiveUnitName;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTotalAmount() {
        return invoiceTotalAmount;
    }

    public void setInvoiceTotalAmount(String invoiceTotalAmount) {
        this.invoiceTotalAmount = invoiceTotalAmount;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressMessage() {
        return expressMessage;
    }

    public void setExpressMessage(String expressMessage) {
        this.expressMessage = expressMessage;
    }

    public String getProductListId() {
        return productListId;
    }

    public void setProductListId(String productListId) {
        this.productListId = productListId;
    }

    public String getProductionFlowId() {
        return productionFlowId;
    }

    public void setProductionFlowId(String productionFlowId) {
        this.productionFlowId = productionFlowId;
    }

    public String getProductionMemo() {
        return productionMemo;
    }

    public void setProductionMemo(String productionMemo) {
        this.productionMemo = productionMemo;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getNeedBeforeInvoice() {
        return needBeforeInvoice;
    }

    public void setNeedBeforeInvoice(String needBeforeInvoice) {
        this.needBeforeInvoice = needBeforeInvoice;
    }

    public String getAuditorDate() {
        return auditorDate;
    }

    public void setAuditorDate(String auditorDate) {
        this.auditorDate = auditorDate;
    }
}
