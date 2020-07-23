package cn.com.common.model.dto;

import cn.com.common.agent.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Horace.zhang on 2019/6/12.
 */
@XmlRootElement(name = "orderVo")
public class OrderVo extends BaseEntity{

    private String userId;
    /**
     * 父订单id
     */
    private String parentId;



    /**
     * 流水号
     */
    private String flowId;

    /**
     * 是否开启子订单：0 父订单 1 子订单
     */
    private Integer childType;

    /**
     * 订购地区省
     */
    private String provinceCode;

    /**
     * 订购地区市
     */
    private String cityCode;

    /**
     * 订单类型：0订购，1代购，2项目内，3赠送，4提前发货，5合同已包含，
     */
    private Integer orderType;

    /**
     * 订单状态：(0.等待受理、1.已受理待汇款、2.生产中—待生产、2.1.生产中—生产中、2.2.生产中—生产完待发货、3.发运中、4.完成、5.退回、6.作废)
     */
    private Double orderStatus;

    /**
     * 订单作废原因
     */
    private String orderInvalid;

    /**
     * 被打回买家是否确认：0未确认，1已确认(默认)
     */
    private Integer isAffirm;

    /**
     * 退回理由
     */
    private String returnReason;

    /**
     * 汇款类型 0公司 1个人
     */
    private Integer remitterType;

    /**
     * 汇款单位名称/汇款人
     */
    private String remitter;

    /**
     * 买家备注
     */
    private String remark;

    /**
     * 订购日期
     */
    private Date orderDate;

    /**
     * 订单所有产品总金额
     */
    private Double productTotalAmount;

    /**
     * 订单所有产品总数量
     */
    private Integer productTotalCount;

    /**
     * 提交状态 0未提交 1已提交
     */
    private Integer submitStatus;

    /**
     * 是否需要发票：0不需要，1需要
     */
    private Integer needInvoice;

    //发票状态：0未开票，1已开票 ，2部分票据已开
    private Integer orderInvoiceStatus;

    /**
     * 开票日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date billingDate;

    /**
     * 发票类型 0.增值税专用发票,1.增值税普通发票
     */
    private Integer invoiceType;
    /**
     * 订单是否为先开发票：0后开票，1先开票 ，2.不开票
     */
    private Integer needBeforeInvoice;

    /**
     * 确认收货：0未确认，1用户确认2.系统确认
     */
    private Integer complateType;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 服务器文件名称
     */
    private String fileServerName;

    /**
     * 订购单位名称
     */
    private String orderUnitName;

    /**
     * 订购单位地址
     */
    private String orderUnitAddress;

    /**
     * 订购人姓名
     */
    private String purchaser;

    /**
     * 订购联系人手机号
     */
    private String purchaserMobile;

    /**
     * 订购联系人电话
     */
    private String purchaserPhone;

    /**
     * 收货单位名称
     */
    private String receiveUnitName;

    /**
     * 收货地址
     */
    private String receiveUnitAddress;

    /**
     * 收货联系人
     */
    private String receiveName;

    /**
     * 收货联系人电话
     */
    private String receiveMobile;

    /**
     * 收货人电话
     */
    private String receivePhone;

    /**
     * 备选联系人
     */
    private String standbyName;

    /**
     * 备选联系人手机
     */
    private String standbyMobile;

    /**
     * 备选联系人电话
     */
    private String standbyPhone;

    /**
     * 收货地址设置选项：0.与订购基本信息同步  1.设置收货地址
     */
    private Integer addressSetting;

//orderProduct

    /**
     * 流水生产号（相同证书类型+型号+前缀编号相同 同一批一个字段流水号，不同去查有没有其他子订单，累加数值）
     */
    private Double flowNumber;

    private String orderId;

    /**
     * 父订单id
     */
    private String orderParentId;



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

//tblareaLabelPrefix

    private String areaId;

    /**
     * 默认头部字段
     */
    private String prefixName;

    /**
     * 证书头部 允许编码
     */
    private String otherPrefix;

//tblProductList
    /**
     * 证书名称
     */
    private String productName;

    /**
     * 供货方 默认 3 辰锐
     */
    private String supplier;



    /**
     * 产品是否在售 0否 1是 （默认）
     */
    private Integer onSale;

    //数字证书KEY标号后缀表id
    private String productListId;

    //公安、警辅数字证书解锁卡 地区编码
    private String productRegionLabelInfix;


    /**
     * 已售证书老数据生产日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;

    private String getNotNull(String data){
        if(StringUtils.isBlank(data)){
            return "";
        }else {
            return data;
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Integer getChildType() {
        return childType;
    }

    public void setChildType(Integer childType) {
        this.childType = childType;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Double getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Double orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsAffirm() {
        return isAffirm;
    }

    public void setIsAffirm(Integer isAffirm) {
        this.isAffirm = isAffirm;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getRemitter() {
        return remitter;
    }

    public void setRemitter(String remitter) {
        this.remitter = remitter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getProductTotalAmount() {
        return productTotalAmount;
    }

    public void setProductTotalAmount(Double productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public Integer getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(Integer submitStatus) {
        this.submitStatus = submitStatus;
    }

    public Integer getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(Integer needInvoice) {
        this.needInvoice = needInvoice;
    }

    public Integer getNeedBeforeInvoice() {
        return needBeforeInvoice;
    }

    public void setNeedBeforeInvoice(Integer needBeforeInvoice) {
        this.needBeforeInvoice = needBeforeInvoice;
    }

    public Integer getComplateType() {
        return complateType;
    }

    public void setComplateType(Integer complateType) {
        this.complateType = complateType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileServerName() {
        return fileServerName;
    }

    public void setFileServerName(String fileServerName) {
        this.fileServerName = fileServerName;
    }

    public String getOrderUnitName() {
        return orderUnitName;
    }

    public void setOrderUnitName(String orderUnitName) {
        this.orderUnitName = orderUnitName;
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

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone;
    }

    public String getReceiveUnitName() {
        return receiveUnitName;
    }

    public void setReceiveUnitName(String receiveUnitName) {
        this.receiveUnitName = receiveUnitName;
    }

    public String getReceiveUnitAddress() {
        return receiveUnitAddress;
    }

    public void setReceiveUnitAddress(String receiveUnitAddress) {
        this.receiveUnitAddress = receiveUnitAddress;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getStandbyName() {
        return standbyName;
    }

    public void setStandbyName(String standbyName) {
        this.standbyName = standbyName;
    }

    public String getStandbyMobile() {
        return standbyMobile;
    }

    public void setStandbyMobile(String standbyMobile) {
        this.standbyMobile = standbyMobile;
    }

    public String getStandbyPhone() {
        return standbyPhone;
    }

    public void setStandbyPhone(String standbyPhone) {
        this.standbyPhone = standbyPhone;
    }

    public Double getFlowNumber() {
        return flowNumber;
    }

    public void setFlowNumber(Double flowNumber) {
        this.flowNumber = flowNumber;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getLabelSuffix() {
        return getNotNull(labelSuffix);
    }

    public void setLabelSuffix(String labelSuffix) {
        this.labelSuffix = labelSuffix;
    }

    public String getLabelPrefix() {
        return getNotNull(labelPrefix);
    }

    public void setLabelPrefix(String labelPrefix) {
        this.labelPrefix = labelPrefix;
    }

    public String getLabelStart() {
        return getNotNull(labelStart);
    }

    public void setLabelStart(String labelStart) {
        this.labelStart = labelStart;
    }

    public String getLabelEnd() {
        return getNotNull(labelEnd);
    }

    public void setLabelEnd(String labelEnd) {
        this.labelEnd = labelEnd;
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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getOtherPrefix() {
        return otherPrefix;
    }

    public void setOtherPrefix(String otherPrefix) {
        this.otherPrefix = otherPrefix;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplier() {

        return getNotNull(supplier);
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }

    public String getProductListId() {
        return productListId;
    }

    public void setProductListId(String productListId) {
        this.productListId = productListId;
    }

    public String getProductRegionLabelInfix() {
        return getNotNull(productRegionLabelInfix);
    }

    public void setProductRegionLabelInfix(String productRegionLabelInfix) {
        this.productRegionLabelInfix = productRegionLabelInfix;
    }

    public Integer getRemitterType() {
        return remitterType;
    }

    public void setRemitterType(Integer remitterType) {
        this.remitterType = remitterType;
    }

    public Integer getProductTotalCount() {
        return productTotalCount;
    }

    public void setProductTotalCount(Integer productTotalCount) {
        this.productTotalCount = productTotalCount;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getOrderInvoiceStatus() {
        return orderInvoiceStatus;
    }

    public void setOrderInvoiceStatus(Integer orderInvoiceStatus) {
        this.orderInvoiceStatus = orderInvoiceStatus;
    }

    public String getOrderInvalid() {
        return orderInvalid;
    }

    public void setOrderInvalid(String orderInvalid) {
        this.orderInvalid = orderInvalid;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
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

    public String getOrderUnitAddress() {
        return orderUnitAddress;
    }

    public void setOrderUnitAddress(String orderUnitAddress) {
        this.orderUnitAddress = orderUnitAddress;
    }

    public Integer getAddressSetting() {
        return addressSetting;
    }

    public void setAddressSetting(Integer addressSetting) {
        this.addressSetting = addressSetting;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}
