package cn.com.common.model.search;

import cn.com.common.agent.BaseDomain;
import cn.com.common.agent.BaseEntity;

import java.io.Serializable;

/**
 * Created by Horace.zhang on 2019/7/3.
 */
public class OrderMessageSearcher extends BaseEntity implements Serializable{
    private static final long serialVersionUID = -6239561831187145396L;
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
     * 订购单位
     */
    private String orderUnitName;

    //orderMessage
    private String orderId;

    /**
     * 用户类型：0买家 1卖家
     */
    private Integer userType;

    /**
     * 留言内容
     */
    private String message;

    //是否需要提醒：false否 true是
    private Boolean isWarn;

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

    public String getOrderUnitName() {
        return orderUnitName;
    }

    public void setOrderUnitName(String orderUnitName) {
        this.orderUnitName = orderUnitName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getWarn() {
        return isWarn;
    }

    public void setWarn(Boolean warn) {
        isWarn = warn;
    }
}
