package cn.com.common.model.imports;

/**
 * Created by Horace.zhang on 2019/8/6.
 */
public class ConstantMsg {
    public static final String FLOWIDISNULL = "流水号不能为空";
    public static final String PRODUCTIONFLOWIDISNULL = "生产流水号不能为空";
    public static final String FLOWIDBYORDERISNULL = "订单不存在此流水号";
    public static final String PRODUCTIONFLOWIDBYORDERISNULL = "订单不存在此流水号";
    public static final String EXPRESSNOISNULL = "快递单号不能为空";
    public static final String ORDERPRODUCTIONERROR = "导入的订单请关闭生产待审核状态";
    public static final String ORDERERROR = "导入的订单状态不允许为退回或作废";
    public static final String ARRIVALDATEERROR = "到款日期格式错误";
    public static final String ARRIVALAMOUNTERROR = "到款金额格式错误";
    public static final String ISTOTALARRIVALERRO = "是否全部到款允许填写:是/否";
    public static final String ARRIVALACCOUNTERRO = "到款账号允许填写:辰锐/三所";

    public static final String EXPRESSTYPEERROR = "快递发运物品允许填写:发票/证书/证书和发票";
    public static final String DELIVERYDATEERROR = "快递发运日期格式错误";

    public static final String BILLINGDATEERROR = "开票日期格式错误";
}
