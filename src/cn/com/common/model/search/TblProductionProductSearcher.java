package cn.com.common.model.search;

import cn.com.common.agent.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Horace.zhang on 2019/8/2.
 */
public class TblProductionProductSearcher extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6015886160163226632L;
    //每页显示总页数
    private int pageCount;
    //当前页数
    private int offset;
    /**
     * 数字证书KEY标号后缀表Id
     */
    private String productListId;

    /**
     * 订单流水号
     */
    private String flowId;

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
     * 产品前缀--对应省份
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
     * 产品数字开始编号
     */
    private String labelNumStart;

    /**
     * 产品数字结束编号
     */
    private String labelNumEnd;

    /**
     * 生产库产品编号
     */
    private String labelProduct;

    /**
     * 生产日期
     */
    private String productionDate;

    private String userName;

    //添加日期起始
    private String addDateStart;
    //添加日期结束
    private String addDateEnd;

    //公安、警辅数字证书解锁卡 地区编码
    private String productRegionLabelInfix;

    private Integer productNumber;

    private Double productPrice;

    private Double productAmount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getProductListId() {
        return productListId;
    }

    public void setProductListId(String productListId) {
        this.productListId = productListId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLabelProduct() {
        return labelProduct;
    }

    public void setLabelProduct(String labelProduct) {
        this.labelProduct = labelProduct;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getAddDateStart() {
        return addDateStart;
    }

    public void setAddDateStart(String addDateStart) {
        this.addDateStart = addDateStart;
    }

    public String getAddDateEnd() {
        return addDateEnd;
    }

    public void setAddDateEnd(String addDateEnd) {
        this.addDateEnd = addDateEnd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
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

    public String getProductRegionLabelInfix() {
        return productRegionLabelInfix;
    }

    public void setProductRegionLabelInfix(String productRegionLabelInfix) {
        this.productRegionLabelInfix = productRegionLabelInfix;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }
}
