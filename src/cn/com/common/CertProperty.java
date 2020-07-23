package cn.com.common;

import java.util.Date;

/**
 * 证书对象
 * 
 * @author
 * 
 */
public class CertProperty {

	/**
	 * 证书使用者
	 */
	private String certUser;
	/**
	 * 证书序列号
	 */
	private String certNumber;
	/**
	 * 证书颁发者
	 */
	private String certParentUser;
	/**
	 * 证书开始时间
	 */
	private Date certStartDate;
	/**
	 * 证书结束时间
	 */
	private Date certEndDate;

	public String getCertUser() {
		return certUser;
	}

	public void setCertUser(String certUser) {
		this.certUser = certUser;
	}

	public String getCertNumber() {
		return certNumber;
	}

	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
	}

	public String getCertParentUser() {
		return certParentUser;
	}

	public void setCertParentUser(String certParentUser) {
		this.certParentUser = certParentUser;
	}

	public Date getCertStartDate() {
		return certStartDate;
	}

	public void setCertStartDate(Date certStartDate) {
		this.certStartDate = certStartDate;
	}

	public Date getCertEndDate() {
		return certEndDate;
	}

	public void setCertEndDate(Date certEndDate) {
		this.certEndDate = certEndDate;
	}

}