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
 * @Date 2019-07-16 
 */

@XmlRootElement ( name ="TblTblTemplateExcel" )
public class TblTemplateExcel extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  1912707047072257942L;



	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 自定义模板名称
	 */
	private String templateName;

	/**
	 * 配置的excel 字段
	 */
	private String excelField;




	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getExcelField() {
		return this.excelField;
	}

	public void setExcelField(String excelField) {
		this.excelField = excelField;
	}


}
