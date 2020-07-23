package cn.com.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import cn.com.common.agent.BaseEntity;
import java.util.Date;

/**
 * @Description  
 * @Author  zhj
 * @Date 2020-02-18 
 */

@XmlRootElement ( name ="OrderInvoiceExpress" )
public class OrderInvoiceExpress extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  3987781137489419510L;



	/**
	 * 订单id
	 */
	private String orderId;

	/**
	 * 订单流水号
	 */
	private String flowId;

	/**
	 * 父订单id
	 */
	private String orderParentId;

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

	public String getExpressName() {
		return this.expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return this.expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getExpressMessage() {
		return this.expressMessage;
	}

	public void setExpressMessage(String expressMessage) {
		this.expressMessage = expressMessage;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
}
