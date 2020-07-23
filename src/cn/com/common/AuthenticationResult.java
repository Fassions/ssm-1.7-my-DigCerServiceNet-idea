package cn.com.common;

import cn.com.common.model.dto.GaUser;

public class AuthenticationResult {

	private int result;

	private String description;

	private GaUser gaUser;

	public AuthenticationResult(int result, String description) {
		this.result = result;
		this.description = description;
	}

	public AuthenticationResult(int result, String description, GaUser gaUser) {
		this.result = result;
		this.description = description;
		this.gaUser = gaUser;
	}

	public final static AuthenticationResult AUTHENTICATION_RESULT_OK = new AuthenticationResult(0, "登录成功");

	public final static AuthenticationResult AUTHENTICATION_RESULT_NOT_LOGIN = new AuthenticationResult(1, "您没有权限登录,请联系管理员!");

	public int getResult() {
		return result;
	}

	public String getDescription() {
		return description;
	}

	public GaUser getGaUser() {
		return gaUser;
	}
	
}