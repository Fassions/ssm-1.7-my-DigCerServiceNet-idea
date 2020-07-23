package cn.com.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import cn.com.common.agent.BaseEntity;
import java.util.Date;

/**
 * @Description  
 * @Author  zhj
 * @Date 2019-06-11 
 */

@XmlRootElement ( name ="orderInvoice" )
public class OrderInvoice extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  4624670277052852050L;


	private String orderId;

	/**
	 * 父订单id
	 */
	private String orderParentId;


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




	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderParentId() {
		return this.orderParentId;
	}

	public void setOrderParentId(String orderParentId) {
		this.orderParentId = orderParentId;
	}

	public Integer getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceUnitName() {
		return this.invoiceUnitName;
	}

	public void setInvoiceUnitName(String invoiceUnitName) {
		this.invoiceUnitName = invoiceUnitName;
	}

	public String getSocialCreditCode() {
		return this.socialCreditCode;
	}

	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}

	public String getDepositBank() {
		return this.depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBillingDate() {
		return this.billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

}
