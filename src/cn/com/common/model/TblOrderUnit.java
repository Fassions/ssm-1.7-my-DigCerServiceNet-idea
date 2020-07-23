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
 * @Date 2019-07-04 
 */

@XmlRootElement ( name ="TblOrderUnit" )
public class TblOrderUnit extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  55742346147876531L;


	/**
	 * 订购单位
	 */
	private String orderUnitName;

	/**
	 * 备注
	 */
	private String remark;


	public String getOrderUnitName() {
		return this.orderUnitName;
	}

	public void setOrderUnitName(String orderUnitName) {
		this.orderUnitName = orderUnitName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
