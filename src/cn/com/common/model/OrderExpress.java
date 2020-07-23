package cn.com.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import cn.com.common.agent.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description  
 * @Author  zhj
 * @Date 2019-07-03 
 */

@XmlRootElement ( name ="orderExpress" )
public class OrderExpress extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  8452581361234168863L;

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

	public Integer getExpressType() {
		return this.expressType;
	}

	public void setExpressType(Integer expressType) {
		this.expressType = expressType;
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
		return expressMessage;
	}

	public void setExpressMessage(String expressMessage) {
		this.expressMessage = expressMessage;
	}

	public String getProductionFlowId() {
		return productionFlowId;
	}

	public void setProductionFlowId(String productionFlowId) {
		this.productionFlowId = productionFlowId;
	}
}
