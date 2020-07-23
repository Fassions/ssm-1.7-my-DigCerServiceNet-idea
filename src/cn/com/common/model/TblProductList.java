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

@XmlRootElement ( name ="tblProductList" )
public class TblProductList extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  7120478944072242651L;


	/**
	 * 证书名称
	 */
	private String productName;

	/**
	 * 供货方 默认 3 辰锐
	 */
	private String supplier;

	/**
	 * 产品型号
	 */
	private String productType;

	/**
	 * 产品标号后缀
	 */
	private String labelSuffix;

	/**
	 * 产品是否在售 0否 1是 （默认）
	 */
	private Integer onSale;

	/**
	 * 产品优先级
	 */
	private Integer levelType;





	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getLabelSuffix() {
		return this.labelSuffix;
	}

	public void setLabelSuffix(String labelSuffix) {
		this.labelSuffix = labelSuffix;
	}

	public Integer getOnSale() {
		return this.onSale;
	}

	public void setOnSale(Integer onSale) {
		this.onSale = onSale;
	}

	public Integer getLevelType() {
		return levelType;
	}

	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}
}
