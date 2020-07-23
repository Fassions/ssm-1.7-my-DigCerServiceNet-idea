package cn.com.common.model;

import cn.com.common.agent.BaseEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @Description  
 * @Author  zhj
 * @Date 2020-02-13 
 */

@XmlRootElement ( name ="ReceiptUploadFiles" )
public class ReceiptUploadFiles extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  2353022280912578001L;



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
