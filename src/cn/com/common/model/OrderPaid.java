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
 * @Date 2019-06-11 
 */

@XmlRootElement ( name ="orderPaid" )
public class OrderPaid extends BaseEntity {



	private String orderId;

	/**
	 * 到款日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date arrivalDate;

	/**
	 * 到款金额
	 */
	private Double arrivalAmount;

	//是否全部到款 0否 1是
	private Integer isTotalArrival;

	/**
	 * 到款账号 0.辰锐，1.三所
	 */
	private Integer arrivalAccount;

	/**
	 * 汇款公司/汇款人 名称
	 */
	private String remitter;

	/**
	 * 卖家到款备注内容
	 */
	private String arrivalMessage;



	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getArrivalDate() {
		return this.arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Integer getArrivalAccount() {
		return this.arrivalAccount;
	}

	public void setArrivalAccount(Integer arrivalAccount) {
		this.arrivalAccount = arrivalAccount;
	}

	public String getArrivalMessage() {
		return this.arrivalMessage;
	}

	public void setArrivalMessage(String arrivalMessage) {
		this.arrivalMessage = arrivalMessage;
	}

	public Integer getIsTotalArrival() {
		return isTotalArrival;
	}

	public void setIsTotalArrival(Integer isTotalArrival) {
		this.isTotalArrival = isTotalArrival;
	}

	public Double getArrivalAmount() {
		return arrivalAmount;
	}

	public void setArrivalAmount(Double arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public String getRemitter() {
		return remitter;
	}

	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}
}
