package cn.com.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import cn.com.common.agent.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description  
 * @Author  zhj
 * @Date 2019-08-02 
 */

@XmlRootElement ( name ="TblProductionProduct" )
public class TblProductionProduct extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  3931061384409837486L;

	//已售证书老数据流水号
	private String flowId;

	/**
	 * 数字证书KEY标号后缀表Id
	 */
	private String productListId;



	/**
	 * 产品类型名称
	 */
	private String productName;

	/**
	 * 供货方 默认 3 辰锐
	 */
	private String supplier;


	/**
	 * 产品标号后缀
	 */
	private String labelSuffix;

	/**
	 * 产品标号前缀（证书省份开头）
	 */
	private String labelPrefix;
	/**
	 * 产品开始编号
	 */
	private String labelStart;
	/**
	 * 产品结束编号
	 */
	private String labelEnd;

	/**
	 * 证书数字部分开始编号
	 */
	private String labelNumStart;

	/**
	 * 证书数字部分结束编号
	 */
	private String labelNumEnd;


	/**
	 * 已售证书老数据生产日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date productionDate;



	public String getProductListId() {
		return this.productListId;
	}

	public void setProductListId(String productListId) {
		this.productListId = productListId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getProductionDate() {
		return this.productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String getLabelStart() {
		return labelStart;
	}

	public void setLabelStart(String labelStart) {
		this.labelStart = labelStart;
	}

	public String getLabelEnd() {
		return labelEnd;
	}

	public void setLabelEnd(String labelEnd) {
		this.labelEnd = labelEnd;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getLabelSuffix() {
		return labelSuffix;
	}

	public void setLabelSuffix(String labelSuffix) {
		this.labelSuffix = labelSuffix;
	}

	public String getLabelPrefix() {
		return labelPrefix;
	}

	public void setLabelPrefix(String labelPrefix) {
		this.labelPrefix = labelPrefix;
	}

	public String getLabelNumStart() {
		return labelNumStart;
	}

	public void setLabelNumStart(String labelNumStart) {
		this.labelNumStart = labelNumStart;
	}

	public String getLabelNumEnd() {
		return labelNumEnd;
	}

	public void setLabelNumEnd(String labelNumEnd) {
		this.labelNumEnd = labelNumEnd;
	}
}
