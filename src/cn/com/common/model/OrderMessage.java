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
 * @Date 2019-07-03 
 */

@XmlRootElement ( name ="OrderMessage" )
public class OrderMessage extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  8408987110135331770L;


	private String orderId;

	/**
	 * 买家用户id
	 */
	private String userId;

	/**
	 * 用户类型：0买家 1卖家
	 */
	private Integer userType;

	/**
	 * 留言内容
	 */
	private String message;

	//是否需要提醒：false否 true是
	private Boolean isWarn;



	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getWarn() {
		return isWarn;
	}

	public void setWarn(Boolean warn) {
		isWarn = warn;
	}
}
