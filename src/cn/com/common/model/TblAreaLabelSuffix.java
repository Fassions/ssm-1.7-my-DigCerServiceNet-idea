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
 * @Date 2019-08-27 
 */

@XmlRootElement ( name ="TblAreaLabelSuffix" )
public class TblAreaLabelSuffix extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  5254355853169407611L;


	private String productListId;

	private String labelPrefixId;

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
	 * 产品单价如存在则用户无法更改，管理员允许更改。
	 */
	private Double productPrice;




	public String getProductListId() {
		return this.productListId;
	}

	public void setProductListId(String productListId) {
		this.productListId = productListId;
	}

	public String getLabelPrefixId() {
		return this.labelPrefixId;
	}

	public void setLabelPrefixId(String labelPrefixId) {
		this.labelPrefixId = labelPrefixId;
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

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

}
