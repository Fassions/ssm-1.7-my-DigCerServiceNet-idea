package cn.com.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import cn.com.common.agent.BaseEntity;
import java.util.Date;

/**
 * @Description  
 * @Author  zhj
 * @Date 2019-12-05 
 */

@XmlRootElement ( name ="TblOrderReport" )
public class TblOrderReport extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  799032983641434336L;

	/**
	 * 数字证书KEY标号后缀表Id
	 */
	private String productListId;

	/**
	 * teskId
	 */
	private String taskId;

	/**
	 * 报表数据生成时间
	 */
	private Date builderTime;

	/**
	 * 报表统计结果开始时间
	 *
	 */
	private Date startTime;

	/**
	 * 报表统计结果结束时间
	 */
	private Date endTime;

	/**
	 * 生成状态(0.生成中 1.完成)
	 */
	private Integer fileStatus;

	/**
	 * 文件类型 0.生产任务单 1.签收单2.产品快递发运单
	 */
	private Integer fileType;

	/**
	 * 文件名称不带后缀
	 */
	private String name;

	/**
	 * 文件全称
	 */
	private String fileName;

	/**
	 * 服务器文件路径
	 */
	private String fileServerName;


	public String getProductListId() {
		return productListId;
	}

	public void setProductListId(String productListId) {
		this.productListId = productListId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getFileStatus() {
		return this.fileStatus;
	}

	public void setFileStatus(Integer fileStatus) {
		this.fileStatus = fileStatus;
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

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Date getBuilderTime() {
		return builderTime;
	}

	public void setBuilderTime(Date builderTime) {
		this.builderTime = builderTime;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}
