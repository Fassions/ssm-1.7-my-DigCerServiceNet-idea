package cn.com.common.model.search;

import cn.com.common.agent.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Horace.zhang on 2019/7/30.
 */
public class TblOrderUnitSearcher implements Serializable {

    private static final long serialVersionUID = 6012131264857379891L;
    //每页显示总页数
    private int pageCount;
    //当前页数
    private int offset;

    private String userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 身份证
     */
    private String certificateCode;

    /**
     * 用户类型 0买家 1卖家
     */
    private Integer userType;

    /**
     * 订购单位编码
     */
    private String orderUnitCode;


    /**
     * 买家备注
     */
    private String userMessage;

    /**
     * 订购单位
     */
    private String orderUnitName;

    /**
     * 订购单位备注
     */
    private String remark;

    //用户表创建日期
    private Date userDateCreated;

    //用户表修改日期
    private Date userDateModified;

    //用户表创建人
    private String userCreated;

    //用户表修改人
    private String userModified;

    //单位表创建日期
    private Date unitDateCreated;

    //单位表修改日期
    private Date unitDateModified;

    //单位表创建人id
    private String unitUserCreated;

    //单位表创建人
    private String unitUserCreatedName;

    //单位表修改人
    private String unitUserModified;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getOrderUnitCode() {
        return orderUnitCode;
    }

    public void setOrderUnitCode(String orderUnitCode) {
        this.orderUnitCode = orderUnitCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getOrderUnitName() {
        return orderUnitName;
    }

    public void setOrderUnitName(String orderUnitName) {
        this.orderUnitName = orderUnitName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUserDateCreated() {
        return userDateCreated;
    }

    public void setUserDateCreated(Date userDateCreated) {
        this.userDateCreated = userDateCreated;
    }

    public Date getUserDateModified() {
        return userDateModified;
    }

    public void setUserDateModified(Date userDateModified) {
        this.userDateModified = userDateModified;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserModified() {
        return userModified;
    }

    public void setUserModified(String userModified) {
        this.userModified = userModified;
    }

    public Date getUnitDateCreated() {
        return unitDateCreated;
    }

    public void setUnitDateCreated(Date unitDateCreated) {
        this.unitDateCreated = unitDateCreated;
    }

    public Date getUnitDateModified() {
        return unitDateModified;
    }

    public void setUnitDateModified(Date unitDateModified) {
        this.unitDateModified = unitDateModified;
    }

    public String getUnitUserCreated() {
        return unitUserCreated;
    }

    public void setUnitUserCreated(String unitUserCreated) {
        this.unitUserCreated = unitUserCreated;
    }

    public String getUnitUserCreatedName() {
        return unitUserCreatedName;
    }

    public void setUnitUserCreatedName(String unitUserCreatedName) {
        this.unitUserCreatedName = unitUserCreatedName;
    }

    public String getUnitUserModified() {
        return unitUserModified;
    }

    public void setUnitUserModified(String unitUserModified) {
        this.unitUserModified = unitUserModified;
    }
}
