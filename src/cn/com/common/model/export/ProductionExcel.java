package cn.com.common.model.export;

import cn.com.common.agent.BaseEntity;
import cn.com.common.utils.ExcelVOAtteribute;

import java.io.Serializable;

/**
 * 生产任务单 Excel
 * Created by Horace.zhang on 2019/7/17.
 */
@SuppressWarnings("serial")
public class ProductionExcel {

    @ExcelVOAtteribute(name="生产任务单号",column = "A")
    private String productionFlowId;

    @ExcelVOAtteribute(name="证书类别",column = "B")
    private String productName;

    @ExcelVOAtteribute(name="证书型号",column = "C")
    private String productType;

    @ExcelVOAtteribute(name="省份",column = "D")
    private String provinceName;

    @ExcelVOAtteribute(name="地市",column = "E")
    private String cityName;

    @ExcelVOAtteribute(name="订购单位",column = "F")
    private String orderUnitName;

    @ExcelVOAtteribute(name="求和项：数量",column = "G")
    private String productTotalCount;

    @ExcelVOAtteribute(name="证书编号",column = "H")
    private String labelCode;

    @ExcelVOAtteribute(name="到款日期",column = "I")
    private String arrivalDate;

    @ExcelVOAtteribute(name="收件人",column = "J")
    private String receiveName;

    @ExcelVOAtteribute(name="收件固定电话",column = "K")
    private String receivePhone;

    @ExcelVOAtteribute(name="收件手机号",column = "L")
    private String receiveMobile;

    @ExcelVOAtteribute(name="收件地址",column = "M")
    private String receiveUnitAddress;

    @ExcelVOAtteribute(name="发票数量",column = "N")
    private String invoiceNum;

    @ExcelVOAtteribute(name="生产注意事项",column = "O")
    private String productionMemo;

    @ExcelVOAtteribute(name="审单日期",column = "P")
    private String auditorDate;

    @ExcelVOAtteribute(name="审单人",column = "Q")
    private String auditor;


}
