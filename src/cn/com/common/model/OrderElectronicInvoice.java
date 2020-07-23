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
 * @Date 2020-01-09 
 */

@XmlRootElement ( name ="OrderElectronicInvoice" )
public class OrderElectronicInvoice extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  4800111719485281685L;


	/**
	 * 订单id
	 */
	private String orderId;

	/**
	 * 订单流水号
	 */
	private String flowId;

	/**
	 * 首页文件名称不带后缀
	 */
	private String name;

	/**
	 * 首页文件全称
	 */
	private String fileName;

	/**
	 * 服务器文件名称
	 */
	private String fileServerName;



	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFlowId() {
		return this.flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileServerName() {
		return this.fileServerName;
	}

	public void setFileServerName(String fileServerName) {
		this.fileServerName = fileServerName;
	}

}
