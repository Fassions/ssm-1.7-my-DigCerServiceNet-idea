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
 * @Date 2019-10-25 
 */

@XmlRootElement ( name ="TblHome" )
public class TblHome extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  8936221147585264300L;



	/**
	 * 图标名称
	 */
	private String fileLabel;

	/**
	 * 首页文件名称不带后缀
	 */
	private String name;

	/**
	 * 首页文件全称
	 */
	private String fileName;


	/**
	 * 文件排列顺序
	 *
	 */
	private String fileLevel;

	/**
	 * 文件上传服务器路径
	 */
	private String fileServerName;

	public String getFileLabel() {
		return this.fileLabel;
	}

	public void setFileLabel(String fileLabel) {
		this.fileLabel = fileLabel;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(String fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getFileServerName() {
		return fileServerName;
	}

	public void setFileServerName(String fileServerName) {
		this.fileServerName = fileServerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
