package cn.com.companyUser.service;

import cn.com.common.model.*;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.imports.ImportPaidExcel;
import cn.com.common.model.search.OrderHistoryStatusSearcher;
import cn.com.common.model.search.OrderMemoSearcher;
import cn.com.common.model.search.OrderMessageSearcher;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/9.
 */
public interface OrderQueryCompanyService {

    Page<OrderQuerySearcher> listOrderCompanyWithPage(OrderQuerySearcher orderQuerySearcher, GaUser gaUser, Limit limit) throws Exception;

    Page<OrderHistoryStatusSearcher> listOrderHistoryStatusWithPage(OrderHistoryStatusSearcher historyStatusSearcher, Limit limit);

    Page<OrderMemoSearcher> listOrderMemoWithPage(OrderMemoSearcher OrderMemoSearcher, Limit limit);

    List<Order> getOrderByOrderIdList(String[] orderId) throws Exception;

    List<OrderPaid> getOrderPaidByOrderIdList(String orderId) throws Exception;


    //组装生产任务单号
    void paramTemPlateExcelProductionId(List<OrderProduct> orderProducts) throws Exception;


    List<OrderMemo> getOrderMemoByOrderId(String orderId) throws Exception;

    OrderProductionMemo getOrderProductionMemoByOrderId(String orderId) throws Exception;

    void postOrderProductionMemo (OrderProductionMemo memo,String userId) throws Exception;

    void putOrderProductionMemo(OrderProductionMemo memo,String userId) throws Exception;

    //更改订单状态
    String putDetailsOrderStatus(Order order,String userId,Double putOrderStatus) throws Exception;

    void putOrderStatus(List<Order> orders,String userId,Double orderStatus) throws Exception;

    void postProductionFlowId(String orderId) throws Exception;

    TblReportTask getTblReportTask(String builderTime) throws Exception;

    //取日期之前最近的数据
    TblReportTask getTblReportTaskBetweenBulider(String buliderTime) throws Exception;

    void postTblReportTask(TblReportTask tblReportTask,String userId) throws Exception;

    void postTblOrderUnit(TblOrderUnit tblOrderUnit,String userId) throws Exception;

    void postOrderHistoryStatus(OrderHistoryStatus historyStatus,String userId) throws Exception;

    void postOrderMemo(OrderMemo memo,String userId) throws Exception;

    //批量导入到款信息
    void postOrderPaid(List<OrderPaid> orderPaid,String userId) throws Exception;

    void postOrderPaid(OrderPaid orderPaid,String userId) throws Exception;

    void editOrderPaid(OrderPaid orderPaid,String userId) throws Exception;

    void editOrderExpress(OrderExpress orderExpress,String userId) throws Exception;

    void postOrderExpress(List<OrderExpress> orderExpress,String userId) throws Exception;

    void putOrderExpress(List<OrderExpress> orderExpress,String userId) throws Exception;

    void putOrderInvoiceExpress(List<OrderInvoiceExpress> orderExpress,String userId) throws Exception;

    void postOrderExpress(OrderExpress orderExpress,String userId) throws Exception;

    void postOrderInvoiceExpress(List<OrderInvoiceExpress> orderExpress, String userId) throws Exception;

    void postOrderElectronicInvoice(OrderElectronicInvoice invoice,String userId) throws Exception;

    void putOrderElectronicInvoice(OrderElectronicInvoice invoice,String userId) throws Exception;

    void putTblReportTask(TblReportTask tblReportTask,String userId) throws Exception;

    OrderElectronicInvoice getOrderElectronicInvoice(String orderId) throws Exception;
}
