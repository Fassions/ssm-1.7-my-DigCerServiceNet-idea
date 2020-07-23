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

@XmlRootElement ( name ="userLogs" )
public class UserLogs extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  4634423342292572899L;


	private String userId;

	/**
	 * 证书编码
	 */
	private String cerSerno;

	/**
	 * ip信息
	 */
	private String ip;

	/**
	 * 用户log信息
	 */
	private String broswerInfo;

	/**
	 * 登录时间
	 */
	private Date loginDate;

	/**
	 * 登出时间
	 */
	private Date logoutDate;

	/**
	 * 登录方式.1为通过数字证书登录,2为客户端跳转登录.
	 */
	private Integer loginType;

	/**
	 * 登出方式.1为系统自动超时剔除,2为用户手动点击登出按钮或者直接关闭浏览器.
	 */
	private Integer logoutType;


	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCerSerno() {
		return this.cerSerno;
	}

	public void setCerSerno(String cerSerno) {
		this.cerSerno = cerSerno;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBroswerInfo() {
		return this.broswerInfo;
	}

	public void setBroswerInfo(String broswerInfo) {
		this.broswerInfo = broswerInfo;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLogoutDate() {
		return this.logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public Integer getLoginType() {
		return this.loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public Integer getLogoutType() {
		return this.logoutType;
	}

	public void setLogoutType(Integer logoutType) {
		this.logoutType = logoutType;
	}

}
