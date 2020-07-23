package cn.com.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import cn.com.common.agent.BaseEntity;
import cn.com.common.utils.IntegerUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description  
 * @Author  zhj
 * @Date 2019-06-11 
 */

@XmlRootElement ( name ="order" )
public class Order extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  8192178050290945098L;


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
	 * 订单类型：0订购，1代购，2项目内，3赠送，4提前发货，5合同已包含，6.先货后款
	 */
	private Integer orderType;

	/**
	 * 是否开启借转销 0未开启（默认） 1已开启
	 */
	private Integer borrowType;

	/**
	 * 订单状态：订单状态：(0.等待受理、1.已受理待汇款、7.待生产、2.生产中—待生产、2.1.生产中—生产中、2.2.生产中—生产完待发货、3.发运中、4.完成、5.退回、6.作废)
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
	 * 汇款单位名称/汇款人
	 */
	private String remitter;

	/**
	 * 汇款类型 0公司 1个人
	 */
	private Integer remitterType;

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
	 * 发票类型 0.增值税专用发票,1.增值税普通发票
	 */
	private Integer invoiceType;

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
	 * 订单是否为先开发票：0后开票，1先开票 ，2.不开票
	 */
	private Integer needBeforeInvoice;

	/**
	 * 开票日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date billingDate;

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

	/**
	 * 生产是否异常需要重新编辑 0正常 1异常
	 */
	private Integer productionErrorStatus;


	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFlowId() {
		return this.flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public Integer getChildType() {
		return this.childType;
	}

	public void setChildType(Integer childType) {
		this.childType = childType;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Double getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Double orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getIsAffirm() {
		return this.isAffirm;
	}

	public void setIsAffirm(Integer isAffirm) {
		this.isAffirm = isAffirm;
	}

	public String getReturnReason() {
		return this.returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getRemitter() {
		return this.remitter;
	}

	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOrderDate() {
		return this.orderDate;
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
		return this.submitStatus;
	}

	public void setSubmitStatus(Integer submitStatus) {
		this.submitStatus = submitStatus;
	}

	public Integer getNeedInvoice() {
		return this.needInvoice;
	}

	public void setNeedInvoice(Integer needInvoice) {
		this.needInvoice = needInvoice;
	}

	public Integer getNeedBeforeInvoice() {
		return this.needBeforeInvoice;
	}

	public void setNeedBeforeInvoice(Integer needBeforeInvoice) {
		this.needBeforeInvoice = needBeforeInvoice;
	}

	public Integer getComplateType() {
		return this.complateType;
	}

	public void setComplateType(Integer complateType) {
		this.complateType = complateType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileServerName() {
		return this.fileServerName;
	}

	public void setFileServerName(String fileServerName) {
		this.fileServerName = fileServerName;
	}

	public String getOrderUnitName() {
		return this.orderUnitName;
	}

	public void setOrderUnitName(String orderUnitName) {
		this.orderUnitName = orderUnitName;
	}

	public String getPurchaser() {
		return this.purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getPurchaserMobile() {
		return this.purchaserMobile;
	}

	public void setPurchaserMobile(String purchaserMobile) {
		this.purchaserMobile = purchaserMobile;
	}

	public String getPurchaserPhone() {
		return this.purchaserPhone;
	}

	public void setPurchaserPhone(String purchaserPhone) {
		this.purchaserPhone = purchaserPhone;
	}

	public String getReceiveUnitName() {
		return this.receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getReceiveUnitAddress() {
		return this.receiveUnitAddress;
	}

	public void setReceiveUnitAddress(String receiveUnitAddress) {
		this.receiveUnitAddress = receiveUnitAddress;
	}

	public String getReceiveName() {
		return this.receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveMobile() {
		return this.receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public String getReceivePhone() {
		return this.receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getStandbyName() {
		return this.standbyName;
	}

	public void setStandbyName(String standbyName) {
		this.standbyName = standbyName;
	}

	public String getStandbyMobile() {
		return this.standbyMobile;
	}

	public void setStandbyMobile(String standbyMobile) {
		this.standbyMobile = standbyMobile;
	}

	public String getStandbyPhone() {
		return this.standbyPhone;
	}

	public void setStandbyPhone(String standbyPhone) {
		this.standbyPhone = standbyPhone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getProductionErrorStatus() {
		return productionErrorStatus;
	}

	public void setProductionErrorStatus(Integer productionErrorStatus) {
		this.productionErrorStatus = productionErrorStatus;
	}

	public Integer getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}
}
