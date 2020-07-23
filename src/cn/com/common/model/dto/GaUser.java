package cn.com.common.model.dto;

import cn.com.common.agent.BaseEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * 用户表
 * 
 * @author Horace.zhang
 * 
 */
@XmlRootElement(name = "gaUser")
public class GaUser extends BaseEntity {

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 身份证
	 */
	private String certificateCode;

	//用户类型 0.买家 1买家
	private Integer userType;

	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色描述
	 */
	private String roleDescription;


	/**
	 * 订购单位
	 */
	private String orderUnitName;

	/**
	 * 买家备注
	 */
	private String userMessage;

	private String cerSerno;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserIdUUID() {
                this.userId = UUID.randomUUID().toString().replace("-","");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

	public String getOrderUnitName() {
		return orderUnitName;
	}

	public void setOrderUnitName(String orderUnitName) {
		this.orderUnitName = orderUnitName;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getCerSerno() {
		return cerSerno;
	}

	public void setCerSerno(String cerSerno) {
		this.cerSerno = cerSerno;
	}
}