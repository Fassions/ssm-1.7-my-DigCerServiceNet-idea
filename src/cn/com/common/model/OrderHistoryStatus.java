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

@XmlRootElement ( name ="orderHistoryStatus" )
public class OrderHistoryStatus extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  4981654983743227333L;


	private String orderId;

	/**
	 * 父订单id
	 */
	private String orderParentId;

	/**
	 * 流转状态：(0.等待受理、1.已受理待汇款、2.生产中—待生产、2.1.生产中—生产中、2.2.生产中—生产完待发货、3.发运中、4.完成、5.退回、6.作废 7.待生产（在生产未下单之前）
	 10.确定下单，
	 11.管理员订单编辑保存
	 12.编辑保存到款信息操作
	 13.编辑保存快递信息操作
	 14.开启生产中订单退回重审操作
	 15.关闭生产中订单退回重审操作
	 */
	private Double historyStatus;

	/**
	 * 退回理由
	 */
	private String returnReason;


	/**
	 * 订单作废原因
	 */
	private String orderInvalid;







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

	public Double getHistoryStatus() {
		return historyStatus;
	}

	public void setHistoryStatus(Double historyStatus) {
		this.historyStatus = historyStatus;
	}

	public String getReturnReason() {
		return this.returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getOrderInvalid() {
		return orderInvalid;
	}

	public void setOrderInvalid(String orderInvalid) {
		this.orderInvalid = orderInvalid;
	}
}
