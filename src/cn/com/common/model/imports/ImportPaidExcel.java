package cn.com.common.model.imports;

import cn.com.common.utils.ExcelVOAtteribute;

/**
 * 到款信息导入
 * Created by Horace.zhang on 2019/8/6.
 */
public class ImportPaidExcel {
    @ExcelVOAtteribute(name = "流水号",column = "A")
    private String flowId;
    @ExcelVOAtteribute(name = "到款日期",column = "B")
    private String arrivalDate;
    @ExcelVOAtteribute(name = "到款账号",column = "C")
    private String arrivalAccount;
    @ExcelVOAtteribute(name = "到款金额",column = "D")
    private String arrivalAmount;
    @ExcelVOAtteribute(name = "汇款单位/汇款人",column = "E")
    private String remitter;
    @ExcelVOAtteribute(name = "是否全部到款",column = "F")
    private String isTotalArrival;
    @ExcelVOAtteribute(name = "备注",column = "G")
    private String arrivalMessage;
    @ExcelVOAtteribute(name = "失败原因",column = "H")
    private String error;



    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalAccount() {
        return arrivalAccount;
    }

    public void setArrivalAccount(String arrivalAccount) {
        this.arrivalAccount = arrivalAccount;
    }

    public String getArrivalAmount() {
        return arrivalAmount;
    }

    public void setArrivalAmount(String arrivalAmount) {
        this.arrivalAmount = arrivalAmount;
    }

    public String getRemitter() {
        return remitter;
    }

    public void setRemitter(String remitter) {
        this.remitter = remitter;
    }

    public String getIsTotalArrival() {
        return isTotalArrival;
    }

    public void setIsTotalArrival(String isTotalArrival) {
        this.isTotalArrival = isTotalArrival;
    }

    public String getArrivalMessage() {
        return arrivalMessage;
    }

    public void setArrivalMessage(String arrivalMessage) {
        this.arrivalMessage = arrivalMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
