package cn.com.common.model.export;

/**
 * 订单管理表
 * Created by Horace.zhang on 2019/7/24.
 */
public class OrderManageExcel {

    private String orderId;

    //证书类别
    private String productName;
    //证书型号
    private String productType;
    //订购日期
    private String orderDate;
    //省份
    private String provinceName;
    //地市
    private String cityName;
    //订购单位
    private String orderUnitName;
    //开票单位
    private String invoiceUnitName;
    //流水号
    private String flowId;
    //数量
    private String productTotalCount;
    //单价
    private String productPrice;
    //金额
    private String productTotalAmount;
    //产品省份字母
    private String prefixName;
    //供货方
    private String supplier;
    //起始号
    private String labelStart;
    //末尾号
    private String labelEnd;
    //产品字母
    private String labelSuffix;
    //发货日期
    private String deliveryDate;
    //发货数
    private String expressTotalCount;
    //发货金额
    private String expressTotalAmount;
    //类型
    private String orderType;
    //到款账户
    private String arrivalAccount;
    //发票数量
    //private String invoiceNum;
    //开票日期
    private String billingDate;
    //到款日期
    private String arrivalDate;

    //大区
    private String regionName;
    //销售经理
    private String saleManager;
    //收件人
    private String receiveName;
    //收件固定电话
    private String receivePhone;
    //收件手机号
    private String receiveMobile;
    //收件地址
    private String receiveUnitAddress;
    //开票信息
    private String socialCreditCode;
    //发票快递单
    private String invoiceExpressNo;
    //证书快递单
    private String productExpressNo;
    //证书和发票快递单
    private String productInvoiceExpressNo;
    //备忘录
    private String memo;
}
