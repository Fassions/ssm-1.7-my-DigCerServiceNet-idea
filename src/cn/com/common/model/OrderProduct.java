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

@XmlRootElement ( name ="orderProduct" )
public class OrderProduct extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  5720289318711959137L;


	//流水号代码模板
	private String flowId;

	/**
	 * 生产流水号
	 */
	private String productionFlowId;


	/**
	 * 流水生产号（相同证书类型+型号+前缀编号相同 同一批一个字段流水号，不同去查有没有其他子订单，累加数值）
	 */
	private Double flowNumber;

	private String orderId;

	/**
	 * 父订单id
	 */
	private String orderParentId;

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
	 * 产品型号
	 */
	private String productType;

	/**
	 * 产品标号后缀
	 */
	private String labelSuffix;

	/**
	 * 产品标号前缀（证书省份开头）
	 */
	private String labelPrefix;

	/**
	 * 证书起始编号
	 */
	private String labelStart;

	/**
	 * 证书结束编号
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
	 * 数量
	 */
	private Integer productNumber;

	/**
	 * 单价
	 */
	private Double productPrice;

	/**
	 * 产品总金额
	 */
	private Double productAmount;





	public Double getFlowNumber() {
		return this.flowNumber;
	}

	public void setFlowNumber(Double flowNumber) {
		this.flowNumber = flowNumber;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderParentId() {
		return this.orderParentId;
	}

	public void setOrderParentId(String orderParentId) {
		this.orderParentId = orderParentId;
	}

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

	public String getLabelPrefix() {
		return this.labelPrefix;
	}

	public void setLabelPrefix(String labelPrefix) {
		this.labelPrefix = labelPrefix;
	}

	public String getLabelStart() {
		return this.labelStart;
	}

	public void setLabelStart(String labelStart) {
		this.labelStart = labelStart;
	}

	public String getLabelEnd() {
		return this.labelEnd;
	}

	public void setLabelEnd(String labelEnd) {
		this.labelEnd = labelEnd;
	}

	public Integer getProductNumber() {
		return this.productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Double getProductAmount() {
		return this.productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

	public String getProductListId() {
		return productListId;
	}

	public void setProductListId(String productListId) {
		this.productListId = productListId;
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

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getProductionFlowId() {
		return productionFlowId;
	}

	public void setProductionFlowId(String productionFlowId) {
		this.productionFlowId = productionFlowId;
	}
}
