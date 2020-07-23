package cn.com.common.agent;

/**
 * 基础属性对象
 * 
 * @author
 */
public class BaseDomain {


	/**
	 * 未提交
	 */
	public static final Integer SUBMIT_STATUS_NONE = new Integer(0);
	/**
	 * 提交
	 */
	public static final Integer SUBMIT_STATUS_DONE = new Integer(1);



	//订单类型：0订购，1代购，2项目内，3赠送，4提前发货，5合同已包含，6.先货后款
	public static final Integer ORDER_TYPE_ZERO = new Integer(0);
	public static final Integer ORDER_TYPE_ONE = new Integer(1);
	public static final Integer ORDER_TYPE_TWO = new Integer(2);
	public static final Integer ORDER_TYPE_THREE = new Integer(3);
	public static final Integer ORDER_TYPE_FOUR = new Integer(4);
	public static final Integer ORDER_TYPE_FIVE = new Integer(5);
	public static final Integer ORDER_TYPE_SIX = new Integer(6);

	//订单状态：订单状态：(0.等待受理、1.已受理待汇款、7.待生产（在生产未下单之前）2.生产中—待生产、2.1.生产中—生产中、2.2.生产中—生产完待发货、3.发运中、4.完成、
	// 5.退回、6.作废、 )
	public static final Double ORDER_STATUS_ZERO = new Double(0);
	public static final Double ORDER_STATUS_ONE = new Double(1);
	public static final Double ORDER_STATUS_TWO = new Double(2);
	public static final Double ORDER_STATUS_TWO_ONE = new Double(2.1);
	public static final Double ORDER_STATUS_TWO_TWO = new Double(2.2);
	public static final Double ORDER_STATUS_THREE = new Double(3);
	public static final Double ORDER_STATUS_FOUR = new Double(4);
	public static final Double ORDER_STATUS_FIVE = new Double(5);
	public static final Double ORDER_STATUS_SIX = new Double(6);
	public static final Double ORDER_STATUS_SEVEN = new Double(7);


	//操作流转记录 10.用户确定下单，11.管理员订单编辑保存订购单信息 12.管理员编辑保存到款信息 13.管理员编辑保存快递信息
	//	 14.开启生产中订单退回重审操作
	// 15.关闭生产中订单退回重审操作【完成重审】
	public static final Double HISTROY_STATUS_TEN = new Double(10);
	public static final Double HISTROY_STATUS_ELEVEN = new Double(11);
	public static final Double HISTROY_STATUS_TWELVE = new Double(12);
	public static final Double HISTROY_STATUS_THIRTEEN = new Double(13);
	public static final Double HISTROY_STATUS_FOURTEEN = new Double(14);
	public static final Double HISTROY_STATUS_FIFTEEN = new Double(15);


	/**
	 * order是否开启子订单:0未开启，1开启
	 */
	public static final Integer CHILD_TYPE_NONE = new Integer(0);
	/**
	 * 提交
	 */
	public static final Integer CHILD_TYPE_DONE = new Integer(1);

	//是否设为选定地址 0否 1是
	public static final Integer IS_DEFAULT_NONE = new Integer(0);

	//是否设为选定地址 0否 1是
	public static final Integer IS_DEFAULT_DONE = new Integer(1);

	//发票类型：0.增值税专用发票,1.增值税普通发票
	public static final Integer INVOICE_TYPE_ZERO = new Integer(0);

	//发票类型：0.增值税专用发票,1.增值税普通发票
	public static final Integer INVOICE_TYPE_ONE = new Integer(1);


	//订单是否开启子订单 0 未开启 1开启
	public static final Integer IS_CHILD_TYPE_NONE = new Integer(0);

	//订单是否开启子订单 0 未开启 1开启
	public static final Integer IS_CHILD_TYPE_DONE = new Integer(0);

	//买家是否确认收货：0未确认，1用户确认2.系统确认
	public static final Integer COMPLATE_TYPE_ZERO = new Integer(0);
	public static final Integer COMPLATE_TYPE_ONE = new Integer(1);
	public static final Integer COMPLATE_TYPE_TWO = new Integer(2);


	//订单发票状态 ：0未开票，1已开票 ，2部分票据已开
	public static final Integer ORDER_INVOICE_STATUS_ZERO = new Integer(0);
	public static final Integer ORDER_INVOICE_STATUS_ONE = new Integer(1);
	public static final Integer ORDER_INVOICE_STATUS_TWO = new Integer(2);


	//留言消息提醒 是否需要提醒：否
	public static final Boolean IS_WARN_FALSE = new Boolean(false);
	//留言消息提醒 是否需要提醒：是
	public static final Boolean IS_WARN_TRUE = new Boolean(true);

	//用户类型 0买家 1卖家
	public static final Integer USER_TYPE_ZERO = new Integer(0);
	public static final Integer USER_TYPE_ONE = new Integer(1);

	//快递发运物品：1.发票，2.证书，3.证书（一起邮寄）
	public static final Integer EXPRESS_TYPE_ONE = new Integer(1);
	public static final Integer EXPRESS_TYPE_TWO = new Integer(2);
	public static final Integer EXPRESS_TYPE_THREE = new Integer(3);


	//订单是否为先开发票：0后开票，1先开票 ，2.不开票
	public static final Integer NEED_BEFORE_INVOICE_ZERO = new Integer(0);
	public static final Integer NEED_BEFORE_INVOICE_ONE = new Integer(1);
	public static final Integer NEED_BEFORE_INVOICE_TWO = new Integer(2);

	//0 生产任务单
	public static final String PRODUCTIONEXCEL = new String("0");
	// 1 财务开票单
	public static final String INVOICEEXCEL = new String("1");
	//  2产品快递单
	public static final String EXPRESSEXCEL = new String("2");
	//  3 订购单管理表
	public static final String ORDERMANAGEEXCEL = new String("3");
	//  4 发票快递单
	public static final String INVOICEEXPRESSEXCEL = new String("4");


	//报表文件生成状态(0.生成中 1.完成)
	public static final Integer FILESTATUS_ZERO = new Integer(0);
	public static final Integer FILESTATUS_ONE = new Integer(1);

	// 报表文件生成类型 0.生产任务单1.签收单2.产品快递发运单
	public static final Integer FILETYPE_ZERO = new Integer(0);
	public static final Integer FILETYPE_ONE = new Integer(1);
	public static final Integer FILETYPE_TWO = new Integer(2);

	// order 订单 是否开启 退回重审状态 0未开启 1开启
	public static final Integer PRODUCTIONERRORSTATUS_ZERO = new Integer(0);
	public static final Integer PRODUCTIONERRORSTATUS_ONE = new Integer(1);

	//产品类型id0	配件1公安数字证书2	警辅数字证书3	公安、警辅数字证书解锁卡4	公安指纹数字证书5	警辅指纹数字证书6	专网数字证书
	public static final Integer PRODUCTLISTID_ZERO = new Integer(0);
	public static final Integer PRODUCTLISTID_ONE = new Integer(1);
	public static final Integer PRODUCTLISTID_TWO = new Integer(2);
	public static final Integer PRODUCTLISTID_THREE = new Integer(3);
	public static final Integer PRODUCTLISTID_FOUR = new Integer(4);
	public static final Integer PRODUCTLISTID_FIVE = new Integer(5);
	public static final Integer PRODUCTLISTID_SIX = new Integer(6);
}