package cn.com.companyUser.service.impl;

import cn.com.common.agent.BaseDomain;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.*;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.dto.TemPlateExcelVo;
import cn.com.common.model.imports.ImportPaidExcel;
import cn.com.common.model.search.OrderHistoryStatusSearcher;
import cn.com.common.model.search.OrderMemoSearcher;
import cn.com.common.model.search.OrderMessageSearcher;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.common.page.PageUtil;
import cn.com.common.service.ProductService;
import cn.com.common.utils.DateUtils;
import cn.com.common.utils.FlowUtil;
import cn.com.common.utils.PutDateSource;
import cn.com.common.utils.ResultMessage;
import cn.com.commonUser.service.OrderUserService;
import cn.com.commonUser.service.UserService;
import cn.com.companyUser.dao.OrderCompanyMapper;
import cn.com.companyUser.service.ExcelTemplateService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import cn.com.companyUser.service.SystemService;
import cn.com.ukey.model.UKeyInfo;
import cn.com.ukey.service.UKeyService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Horace.zhang on 2019/7/9.
 */
@Service
public class OrderQueryCompanyServiceImpl implements OrderQueryCompanyService {

    private static final Logger _logger = LoggerFactory.getLogger(OrderQueryCompanyServiceImpl.class);

    @Autowired
    private OrderCompanyMapper orderCompanyMapper;

    @Autowired
    private SystemService systemService;

    @Autowired
    private OrderUserService orderUserService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ExcelTemplateService excelTemplateService;





    @Override
    public Page<OrderQuerySearcher> listOrderCompanyWithPage(OrderQuerySearcher orderQuerySearcher, GaUser gaUser, Limit limit) throws Exception {
        if(StringUtils.isNotBlank(orderQuerySearcher.getOrderTypeArrays())&&!"null".equals(orderQuerySearcher.getOrderTypeArrays())){
            orderQuerySearcher.setOrderTypeArray(JSONObject.parseArray(orderQuerySearcher.getOrderTypeArrays()));
        }
        if(StringUtils.isNotBlank(orderQuerySearcher.getOrderStatusArrays())&&!"null".equals(orderQuerySearcher.getOrderStatusArrays())){
            orderQuerySearcher.setOrderStatusArray(JSONObject.parseArray(orderQuerySearcher.getOrderStatusArrays()));
        }

        List<OrderQuerySearcher> querySearchers = orderCompanyMapper.companyListWithOrderQuery(orderQuerySearcher);
        Page<OrderQuerySearcher> page = PageUtil.listToPage(querySearchers,limit,querySearchers.size());
        //TODO:检索多行
        return page;
    }

    @Override
    public Page<OrderHistoryStatusSearcher> listOrderHistoryStatusWithPage(OrderHistoryStatusSearcher historyStatusSearcher, Limit limit) {
            List<OrderHistoryStatusSearcher> querySearchers = orderCompanyMapper.listOrderHistoryStatusWithPage(historyStatusSearcher);
            Page<OrderHistoryStatusSearcher> page = PageUtil.listToPage(querySearchers,limit,querySearchers.size());
            //TODO:检索多行
            return page;
    }

    @Override
    public Page<OrderMemoSearcher> listOrderMemoWithPage(OrderMemoSearcher orderMemoSearcher, Limit limit) {
        List<OrderMemoSearcher> querySearchers = orderCompanyMapper.listOrderMemoWithPage(orderMemoSearcher);
        Page<OrderMemoSearcher> page = PageUtil.listToPage(querySearchers,limit,querySearchers.size());
        //TODO:检索多行
        return page;
    }

    @Override
    public List<Order> getOrderByOrderIdList(String[] orderId) throws Exception {
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<Order> data = orderCompanyMapper.getOrderByOrderIdList(map);
        if(data.size()!=0){
            return data;
        }else {
            return null;
        }
    }

    @Override
    public List<OrderPaid> getOrderPaidByOrderIdList(String orderId) throws Exception {
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderPaid> data = orderCompanyMapper.getOrderPaidByOrderIdList(map);
        if(data.size()!=0){
            return data;
        }else {
            return null;
        }
    }

    public void paramTemPlateExcelProductionId(List<OrderProduct> orderProducts) throws Exception{
        //TODO:组装生产任务单号
        for(int i =0;i<orderProducts.size();i++){
            OrderProduct tem = orderProducts.get(i);
            String currentFlowId = tem.getFlowId() + FlowUtil.getProductLetter(tem.getProductListId(),tem.getProductName());
            //生产任务单号组装
            if(i==0 && orderProducts.size()==1){
                tem.setProductionFlowId(FlowUtil.flowProductionWhileInt(null,currentFlowId,null));
            }else if(i==0 && orderProducts.size()!=1){
                String lastFlowId = orderProducts.get(i+1).getFlowId() + FlowUtil.getProductLetter(orderProducts.get(i+1).getProductListId(),orderProducts.get(i+1).getProductName());
                tem.setProductionFlowId(FlowUtil.flowProductionWhileInt(null,currentFlowId,lastFlowId));
            }else if(i==orderProducts.size()-1){
                String beforeFlowId = orderProducts.get(i-1).getProductionFlowId();
                tem.setProductionFlowId(FlowUtil.flowProductionWhileInt(beforeFlowId,currentFlowId,null));
            }else {
                String beforeFlowId = orderProducts.get(i-1).getProductionFlowId();
                String lastFlowId = orderProducts.get(i+1).getFlowId() + FlowUtil.getProductLetter(orderProducts.get(i+1).getProductListId(),orderProducts.get(i+1).getProductName());
                tem.setProductionFlowId(FlowUtil.flowProductionWhileInt(beforeFlowId,currentFlowId,lastFlowId));
            }
        }
    }



    @Override
    public List<OrderMemo> getOrderMemoByOrderId(String orderId) throws Exception {
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderMemo> data = orderCompanyMapper.getOrderMemo(map);
        if(data.size()!=0){
            return data;
        }else {
            return null;
        }
    }

    @Override
    public OrderProductionMemo getOrderProductionMemoByOrderId(String orderId) throws Exception {
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        return orderCompanyMapper.getOrderProductionMemo(map);
    }

    @Override
    public void postOrderProductionMemo(OrderProductionMemo memo,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(memo.getId(),userId),memo);
        orderCompanyMapper.postOrderProductionMemo(memo);
    }

    @Override
    public void putOrderProductionMemo(OrderProductionMemo memo,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(memo.getId(),userId),memo);
        orderCompanyMapper.putOrderProductionMemo(memo);
    }

    @Override
    public String putDetailsOrderStatus(Order order,String userId,Double putOrderStatus) throws Exception {

        //变更为接受订单状态 新增单位， 变更用户单位 ，新增流水号，流转记录
        if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_ZERO) && Objects.equals(BaseDomain.ORDER_STATUS_ONE, putOrderStatus)){

            //验证 产品编号是否已被使用
            if(productService.verifyOrderProductByOrderId(order.getId())){
                return "订购的产品编号已被使用，请重新选购。";
            }

            //新增订单流水号
            if(StringUtils.isBlank(order.getFlowId())){
                String maxFlowId = orderCompanyMapper.getOrderMaxFlowId();
                String flowId = FlowUtil.createFlow(maxFlowId);
                order.setFlowId(flowId);
            }

            //单位
            TblOrderUnit orderUnit = systemService.getTblOrderUnitByOrderUnitName(order.getOrderUnitName());
            if(orderUnit==null){
                orderUnit = new TblOrderUnit();
                orderUnit.setOrderUnitName(order.getOrderUnitName());
                postTblOrderUnit(orderUnit,userId);
                orderUnit = systemService.getTblOrderUnitByOrderUnitName(order.getOrderUnitName());
            }
            //变更用户单位
            UserInfo userInfo = userService.getUserInfoByUserId(order.getUserId());
            if(userInfo!=null){
                userInfo.setOrderUnitName(orderUnit.getId());
                userService.putUserInfo(userInfo);
            }else{
                _logger.error("ERROR:订单变更为已接收待付款，userInfo 为 NULL");
            }
            order.setOrderStatus(putOrderStatus);
            orderUserService.putOrder(order);
        }else if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_ONE) && Objects.equals(BaseDomain.ORDER_STATUS_SEVEN, putOrderStatus)){
            order.setOrderStatus(putOrderStatus);
            orderUserService.putOrder(order);
            //组装生产任务单号
            postProductionFlowId(order.getId());
            //添加报表生成任务
            TblReportTask reportTask = getTblReportTask(DateUtils.firstMonday());
            if(reportTask==null){ //不存在做新增
                reportTask = new TblReportTask();
                reportTask.setBuilderTime(DateUtils.parseStringByPatternYYYYMMDD(DateUtils.firstMonday()));
                reportTask.setTaskStatus(0);
                postTblReportTask(reportTask,userId);
            }
        }else if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_SEVEN) && Objects.equals(BaseDomain.ORDER_STATUS_TWO, putOrderStatus)){
            order.setOrderStatus(putOrderStatus);
            orderUserService.putOrder(order);
        }else if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_TWO) && Objects.equals(BaseDomain.ORDER_STATUS_TWO_ONE, putOrderStatus)){
            order.setOrderStatus(putOrderStatus);
            orderUserService.putOrder(order);
        }else if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_TWO_ONE) && Objects.equals(BaseDomain.ORDER_STATUS_TWO_TWO, putOrderStatus)){
            order.setOrderStatus(putOrderStatus);
            orderUserService.putOrder(order);
        }else if(((Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_TWO))
                ||(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_TWO_ONE))
                ||(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_TWO_TWO)))
                && Objects.equals(BaseDomain.ORDER_STATUS_THREE, putOrderStatus)){  //进入发运中状态
            //TODO:判断订单当前是否为生产中 退回重审订单状态 无法状态流转
            if(Objects.equals(order.getProductionErrorStatus(),BaseDomain.PRODUCTIONERRORSTATUS_ONE)){
                return "请点击完成重审操作。";
            }
            order.setComplateType(BaseDomain.COMPLATE_TYPE_ZERO);  //发运中 需要用户确认收货。
            order.setOrderStatus(putOrderStatus);
            orderUserService.putOrder(order);
        }else if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_FIVE)){   //退回
            order.setOrderStatus(putOrderStatus);
            orderUserService.putOrder(order);
        }else if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_SIX)){    //作废
            order.setOrderStatus(putOrderStatus);
            orderUserService.putOrder(order);
        }else if(Objects.equals(BaseDomain.HISTROY_STATUS_TEN, putOrderStatus)){ //用户下单

        }else if(Objects.equals(BaseDomain.HISTROY_STATUS_ELEVEN, putOrderStatus)){ //卖家操作--编辑保存订单

        }else{
            return "需变更的状态不符合要求。";
        }

        //新增流转记录
        OrderHistoryStatus historyStatus = new OrderHistoryStatus();
        historyStatus.setHistoryStatus(putOrderStatus);
        historyStatus.setOrderId(order.getId());
        historyStatus.setReturnReason(order.getReturnReason()); //退回理由
        historyStatus.setOrderInvalid(order.getOrderInvalid()); //作废理由
        postOrderHistoryStatus(historyStatus,userId);
        return null;
    }

    @Override
    public void putOrderStatus(List<Order> orders,String userId, Double orderStatus) throws Exception {
        for(Order order : orders){
            putDetailsOrderStatus(order,userId,orderStatus);
        }
//        List<Order> putOrder = new ArrayList<>();
////        for(Order order : orders){
////            if(Objects.equals(Double.parseDouble(orderStatus),BaseDomain.ORDER_STATUS_TWO)){ //批量进入生产中（0.等待受理、1.已受理待汇款）可修改
////                if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_ZERO)
////                        || Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_ONE)){
////                    order.setOrderStatus(BaseDomain.ORDER_STATUS_TWO);
////                    putOrder.add(order);
////                }
////            }else if(Objects.equals(Double.parseDouble(orderStatus),BaseDomain.ORDER_STATUS_TWO_ONE)){ //批量下生产单（2.生产中—待生产）可修改
////                if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_TWO)){
////                    order.setOrderStatus(BaseDomain.ORDER_STATUS_TWO_ONE);
////                    putOrder.add(order);
////                }
////            }else if(Objects.equals(Double.parseDouble(orderStatus),BaseDomain.ORDER_STATUS_TWO_TWO)){ //批量完成生产（2.1.生产中—生产中）可修改
////                if(Objects.equals(order.getOrderStatus(), BaseDomain.ORDER_STATUS_TWO_ONE)){
////                    order.setOrderStatus(BaseDomain.ORDER_STATUS_TWO_TWO);
////                    putOrder.add(order);
////                }
////            }
////        }
////
////        //更新订单状态
////        if(putOrder.size()!=0){
////            for(Order order:putOrder){
////                orderUserService.putOrder(order);
////            }
////        }

    }

    @Override
    public void postProductionFlowId(String orderId) throws Exception {
        //组装生产任务单号
        List<OrderProduct> tem = orderUserService.getOrderProduct(orderId);
        if(tem!=null){
            paramTemPlateExcelProductionId(tem);
            for(OrderProduct orderProduct: tem){
                orderUserService.putOrderProduct(orderProduct);
            }
        }
    }

    @Override
    public TblReportTask getTblReportTask(String builderTime) throws Exception {
        Map map = new HashMap<>();
        map.put("builderTime",builderTime);
        return orderCompanyMapper.getTblReportTask(map);
    }

    @Override
    public TblReportTask getTblReportTaskBetweenBulider(String builderTime) throws Exception {
        Map map = new HashMap<>();
        map.put("builderTime",builderTime);
        return orderCompanyMapper.getTblReportTaskBetween(map);
    }

    @Override
    public void postTblReportTask(TblReportTask tblReportTask,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(tblReportTask.getId(),userId),tblReportTask);
        orderCompanyMapper.postTblReportTask(tblReportTask);
    }

    @Override
    public void putTblReportTask(TblReportTask tblReportTask,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(tblReportTask.getId(),userId),tblReportTask);
        orderCompanyMapper.putTblReportTask(tblReportTask);
    }

    @Override
    public void postTblOrderUnit(TblOrderUnit tblOrderUnit,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(tblOrderUnit.getId(),userId),tblOrderUnit);
        orderCompanyMapper.postTblOrderUnit(tblOrderUnit);
    }

    @Override
    public void postOrderHistoryStatus(OrderHistoryStatus historyStatus, String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(historyStatus.getId(),userId),historyStatus);
        orderCompanyMapper.postOrderHistoryStatus(historyStatus);
    }

    @Override
    public void postOrderMemo(OrderMemo memo, String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(memo.getId(),userId),memo);
        orderCompanyMapper.postOrderMemo(memo);
    }

    @Override
    public void postOrderPaid(List<OrderPaid> orderPaid, String userId) throws Exception{
        for(OrderPaid paid:orderPaid){
            BeanUtilsEx.copyProperties(PutDateSource.createDate(paid.getId(),userId),paid);
            orderCompanyMapper.postOrderPaid(paid);
        }
    }
    @Override
    public void postOrderPaid(OrderPaid orderPaid, String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(orderPaid.getId(),userId),orderPaid);
        orderCompanyMapper.postOrderPaid(orderPaid);
    }
    @Override
    public void editOrderPaid(OrderPaid orderPaid, String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(orderPaid.getId(),userId),orderPaid);
        orderCompanyMapper.putOrderPaid(orderPaid);
        //新增流转记录
        OrderHistoryStatus historyStatus = new OrderHistoryStatus();
        historyStatus.setHistoryStatus(BaseDomain.HISTROY_STATUS_TWELVE);
        historyStatus.setOrderId(orderPaid.getOrderId());
        postOrderHistoryStatus(historyStatus,userId);
    }

    @Override
    public void editOrderExpress(OrderExpress orderExpress, String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(orderExpress.getId(),userId),orderExpress);
        orderCompanyMapper.putOrderExpress(orderExpress);
        //新增流转记录
        OrderHistoryStatus historyStatus = new OrderHistoryStatus();
        historyStatus.setHistoryStatus(BaseDomain.HISTROY_STATUS_THIRTEEN);
        historyStatus.setOrderId(orderExpress.getOrderId());
        postOrderHistoryStatus(historyStatus,userId);
    }

    @Override
    public void postOrderExpress(List<OrderExpress> orderExpress, String userId) throws Exception {
        for(OrderExpress express:orderExpress){
            BeanUtilsEx.copyProperties(PutDateSource.createDate(express.getId(),userId),express);
            orderCompanyMapper.postOrderExpress(express);
        }
    }

    @Override
    public void putOrderExpress(List<OrderExpress> orderExpress, String userId) throws Exception {
        for(OrderExpress express:orderExpress){
            BeanUtilsEx.copyProperties(PutDateSource.updateDate(express.getId(),userId),express);
            orderCompanyMapper.putOrderExpress(express);
        }

    }
    @Override
    public void putOrderInvoiceExpress(List<OrderInvoiceExpress> orderInvoiceExpress,String userId) throws Exception{
        for(OrderInvoiceExpress express:orderInvoiceExpress){
            BeanUtilsEx.copyProperties(PutDateSource.updateDate(express.getId(),userId),express);
            orderCompanyMapper.putOrderInvoiceExpress(express);
        }

    }

    @Override
    public void postOrderExpress(OrderExpress orderExpress, String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(orderExpress.getId(),userId),orderExpress);
        orderCompanyMapper.postOrderExpress(orderExpress);
    }

    @Override
    public void postOrderInvoiceExpress(List<OrderInvoiceExpress> orderExpress, String userId) throws Exception {
        for(OrderInvoiceExpress express:orderExpress){
            BeanUtilsEx.copyProperties(PutDateSource.createDate(express.getId(),userId),express);
            orderCompanyMapper.postOrderInvoiceExpress(express);
        }
    }

    @Override
    public void postOrderElectronicInvoice(OrderElectronicInvoice invoice, String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(invoice.getId(),userId),invoice);
        orderCompanyMapper.postOrderElectronicInvoice(invoice);
    }

    @Override
    public void putOrderElectronicInvoice(OrderElectronicInvoice invoice, String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(invoice.getId(),userId),invoice);
        orderCompanyMapper.putOrderElectronicInvoice(invoice);
    }

    @Override
    public OrderElectronicInvoice getOrderElectronicInvoice(String orderId) throws Exception {
        Map map = new HashMap<>();
        map.put("orderId",orderId);
        return orderCompanyMapper.getOrderElectronicInvoice(map);
    }
}
