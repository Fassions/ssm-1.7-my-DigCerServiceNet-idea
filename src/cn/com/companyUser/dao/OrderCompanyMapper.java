package cn.com.companyUser.dao;

import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.*;
import cn.com.common.model.search.OrderHistoryStatusSearcher;
import cn.com.common.model.search.OrderMemoSearcher;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.utils.PutDateSource;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/9.
 */
public interface OrderCompanyMapper {
     List<OrderQuerySearcher> companyListWithOrderQuery(OrderQuerySearcher orderQuerySearcher);

     List<OrderHistoryStatusSearcher> listOrderHistoryStatusWithPage(OrderHistoryStatusSearcher orderHistoryStatusSearcher);

     List<OrderMemoSearcher> listOrderMemoWithPage(OrderMemoSearcher orderMemoSearcher);

     List<OrderMemo> getOrderMemo(Map map);

     List<Order> getOrderByOrderIdList(Map map);

     List<OrderPaid> getOrderPaidByOrderIdList(Map map);






     //取最大流水号
     String getOrderMaxFlowId();

     OrderProductionMemo getOrderProductionMemo(Map map);

     void postTblOrderUnit(TblOrderUnit orderUnit);

     void postOrderHistoryStatus(OrderHistoryStatus historyStatus);

     void postOrderMemo(OrderMemo memo);

     void postOrderProductionMemo(OrderProductionMemo memo);

     void postOrderPaid(OrderPaid orderPaid);

     void postOrderExpress(OrderExpress orderExpress);

     void postOrderInvoiceExpress(OrderInvoiceExpress orderExpress);

     void putOrderPaid(OrderPaid orderPaid);

     void putOrderProductionMemo(OrderProductionMemo memo);

     void putOrderExpress(OrderExpress orderExpress);

     void putOrderInvoiceExpress(OrderInvoiceExpress orderExpress);

     void putOrderElectronicInvoice(OrderElectronicInvoice invoice);

     void postOrderElectronicInvoice(OrderElectronicInvoice invoice);

     OrderElectronicInvoice getOrderElectronicInvoice(Map map);

     TblReportTask getTblReportTask(Map map);

     TblReportTask getTblReportTaskBetween(Map map);

     void putTblReportTask(TblReportTask task);

     void postTblReportTask(TblReportTask reportTask);
}
