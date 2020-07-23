package cn.com.common.model.search;

import cn.com.common.agent.BaseEntity;

import java.io.Serializable;

/**
 * Created by Horace.zhang on 2019/7/3.
 */
public class OrderHistoryStatusSearcher extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 6015993860026805799L;
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



    /**
     * 用户类型：0买家 1卖家
     */
    private Integer userType;

    //orderHistoryStatus
    private String orderId;

    /**
     * 父订单id
     */
    private String orderParentId;

    /**
     * 订单流转状态：(0.等待受理、0.1.订单被打回待确认、1.已受理待汇款、2.生产中”—未下单、2.1.生产中—生产中、3.发运中、4.完成、5.退回、6.废止, 7.用户确定下单，8.管理员订单编辑保存
     */
    private Double historyStatus;

    /**
     * 退回理由
     */
    private String returnReason;


    /**
     * 订单作废原因
     */
    private String orderInvalid;

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

    public String getOrderParentId() {
        return orderParentId;
    }

    public void setOrderParentId(String orderParentId) {
        this.orderParentId = orderParentId;
    }

    public Double getHistoryStatus() {
        return historyStatus;
    }

    public void setHistoryStatus(Double historyStatus) {
        this.historyStatus = historyStatus;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getOrderInvalid() {
        return orderInvalid;
    }

    public void setOrderInvalid(String orderInvalid) {
        this.orderInvalid = orderInvalid;
    }
}
