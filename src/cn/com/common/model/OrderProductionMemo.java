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
 * @Date 2019-12-09 
 */

@XmlRootElement ( name ="OrderProductionMemo" )
public class OrderProductionMemo extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  897138039682158887L;

	private String orderId;

	/**
	 * 生产部注意事项内容
	 */
	private String memo;




	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


}
