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
 * @Date 2020-05-18 
 */

@XmlRootElement ( name ="TblReportTask" )
public class TblReportTask extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  2402162181656128098L;

	/**
	 * 报表统计生成时间
	 */
	private Date builderTime;

	/**
	 * 任务状态(0.未运行 1.已完成)
	 */
	private Integer taskStatus;


	public Date getBuilderTime() {
		return this.builderTime;
	}

	public void setBuilderTime(Date builderTime) {
		this.builderTime = builderTime;
	}

	public Integer getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

}
