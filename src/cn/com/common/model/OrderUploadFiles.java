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
 * @Date 2019-08-16 
 */

@XmlRootElement ( name ="OrderUploadFiles" )
public class OrderUploadFiles extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  9132375864012270342L;


	/**
	 * 订单id
	 */
	private String orderId;

	/**
	 * 附件名称
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
