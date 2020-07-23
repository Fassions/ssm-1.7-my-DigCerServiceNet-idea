package cn.com.ukey.model;

import java.util.Date;

/**
 * Created by Horace.zhang on 2019/8/5.
 */
public class UKeyInfo {
    //所需数据库
    private String dateBase;
    /**
     * 产品标号前缀（证书省份开头）所需数据表
     */
    private String labelPrefix;
    //所需查询产品编号字段名
    private String columnLabelName;
    //产品类型Id
    private String productListId;
    /**
     * 证书起始编号(查询条件)
     */
    private String labelStart;

    /**
     * 证书结束编号(查询条件)
     */
    private String labelEnd;




    private String id;
    //产品类型名称
    private String productName;
    //产品编号
    private String labelProduct;
    //产品出厂序列号
    private String proCer;
    //生产厂商
    private String manufacturer;
    //批次编号
    private String batchLabel;
    //生产时间
    private Date productionTime;
    //生产类型
    private String productionType;

    public String getDateBase() {
        return dateBase;
    }

    public void setDateBase(String dateBase) {
        this.dateBase = dateBase;
    }

    public String getLabelPrefix() {
        return labelPrefix;
    }

    public void setLabelPrefix(String labelPrefix) {
        this.labelPrefix = labelPrefix;
    }

    public String getColumnLabelName() {
        return columnLabelName;
    }

    public void setColumnLabelName(String columnLabelName) {
        this.columnLabelName = columnLabelName;
    }

    public String getProductListId() {
        return productListId;
    }

    public void setProductListId(String productListId) {
        this.productListId = productListId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProCer() {
        return proCer;
    }

    public void setProCer(String proCer) {
        this.proCer = proCer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBatchLabel() {
        return batchLabel;
    }

    public void setBatchLabel(String batchLabel) {
        this.batchLabel = batchLabel;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Date productionTime) {
        this.productionTime = productionTime;
    }

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
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
}
