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
 * @Date 2019-06-11 
 */

@XmlRootElement ( name ="tblAreaLabelPrefix" )
public class TblAreaLabelPrefix extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  6988404553981809288L;


	private String areaId;

	/**
	 * 默认头部字段
	 */
	private String prefixName;

	/**
	 * 证书头部 允许编码
	 */
	private String otherPrefix;




	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getPrefixName() {
		return this.prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getOtherPrefix() {
		return this.otherPrefix;
	}

	public void setOtherPrefix(String otherPrefix) {
		this.otherPrefix = otherPrefix;
	}


}
