package cn.com.common.model.export;

import cn.com.common.utils.ExcelVOAtteribute;

/**
 * 开票信息快递单
 * Created by Horace.zhang on 2020/2/18.
 */
public class ExpressInvoiceExcel {
    @ExcelVOAtteribute(name="流水号",column = "A")
    private String flowId;
    //收件人
    @ExcelVOAtteribute(name="收件人",column = "B")
    private String receiveName;
    //目的地（空）
    @ExcelVOAtteribute(name="目的地",column = "C")
    private String bourn;
    //省份
    @ExcelVOAtteribute(name="省份",column = "D")
    private String provinceName;
    //地市
    @ExcelVOAtteribute(name="地市",column = "E")
    private String cityName;
    //区县
    @ExcelVOAtteribute(name="区县",column = "F")
    private String county;
    //收件地址
    @ExcelVOAtteribute(name="收件地址",column = "G")
    private String receiveUnitAddress;
    //收件单位名称
    @ExcelVOAtteribute(name="收件单位名称",column = "H")
    private String receiveUnitName;
    //收件固定电话
    @ExcelVOAtteribute(name="收件固定电话",column = "I")
    private String receivePhone;
    //收件手机号
    @ExcelVOAtteribute(name="收件手机号",column = "J")
    private String receiveMobile;
    //空
    @ExcelVOAtteribute(name="空",column = "K")
    private String empty;
    //空
    @ExcelVOAtteribute(name="空",column = "L")
    private String empty1;
    //品名
    @ExcelVOAtteribute(name="品名",column = "M")
    private String expressType;

    //发货单号
    @ExcelVOAtteribute(name="快递单号",column = "N")
    private String expressNo;

    //发货日期
    @ExcelVOAtteribute(name="发货日期",column = "O")
    private String deliveryDate;

    //备注
    @ExcelVOAtteribute(name="发货备注",column = "P")
    private String expressMessage;

    //导入失败原因
    @ExcelVOAtteribute(name="失败原因",column = "Q")
    private String error;


    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getBourn() {
        return bourn;
    }

    public void setBourn(String bourn) {
        this.bourn = bourn;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getReceiveUnitAddress() {
        return receiveUnitAddress;
    }

    public void setReceiveUnitAddress(String receiveUnitAddress) {
        this.receiveUnitAddress = receiveUnitAddress;
    }

    public String getReceiveUnitName() {
        return receiveUnitName;
    }

    public void setReceiveUnitName(String receiveUnitName) {
        this.receiveUnitName = receiveUnitName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getEmpty1() {
        return empty1;
    }

    public void setEmpty1(String empty1) {
        this.empty1 = empty1;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getExpressMessage() {
        return expressMessage;
    }

    public void setExpressMessage(String expressMessage) {
        this.expressMessage = expressMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
