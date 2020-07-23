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
 * @Date 2019-06-20 
 */

@XmlRootElement ( name ="orderInvoiceProduct" )
public class OrderInvoiceProduct extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  9104946330660509012L;

	private String id;

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


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getOrderInvoiceId() {
		return this.orderInvoiceId;
	}

	public void setOrderInvoiceId(String orderInvoiceId) {
		this.orderInvoiceId = orderInvoiceId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getProductNumber() {
		return this.productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Double getProductAmount() {
		return this.productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

	public String getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}
}
