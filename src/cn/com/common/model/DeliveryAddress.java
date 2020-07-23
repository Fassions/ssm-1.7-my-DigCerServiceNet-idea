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

@XmlRootElement ( name ="deliveryAddress" )
public class DeliveryAddress extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5203015011173491904L;


	private String userId;

	/**
	 * 订购单位名称
	 */
	private String orderUnitName;

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
	 * 是否设为默认地址：0.否 1.是
	 */
	private Integer isDefault;


	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
