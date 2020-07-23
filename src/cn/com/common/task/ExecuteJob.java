package cn.com.common.task;

import cn.com.common.agent.BaseDomain;
import cn.com.common.model.Order;
import cn.com.common.model.OrderHistoryStatus;
import cn.com.common.model.TblReportTask;
import cn.com.common.utils.DateUtils;
import cn.com.commonUser.service.OrderUserService;
import cn.com.companyUser.service.OrderQueryCompanyService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Horace.zhang on 2019/7/4.
 */
@Component
public class ExecuteJob {
    private static final Logger _logger = LoggerFactory.getLogger(ExecuteJob.class);

    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private OrderQueryCompanyService queryCompanyService;

    @Autowired
    private ExecutorReport executorReport;

    /**
     * 7天后 系统自动已确认收货
     */
    @Scheduled(cron = "0 30 0 * * *")
//    @Scheduled(cron = "0 40 13 * * *")
//    @Scheduled(cron = "0/5 * * * * ? ")
    public void executeConfirmReceipt(){
        try {
            List<Order> orderArray = new ArrayList<>();
            Order value = new Order();
            value.setOrderStatus(BaseDomain.ORDER_STATUS_THREE); //发运中状态
            value.setComplateType(BaseDomain.COMPLATE_TYPE_ZERO); //未确认完成订单
            List<Order> orders = orderUserService.getOrderFinish(value);
            if(orders==null){
                return;
            }
            for(Order order : orders){
                //快递信息最新创建日期 dateCreated
                if(Objects.equals(order.getComplateType(), BaseDomain.COMPLATE_TYPE_ZERO) && DateUtils.daysBetween(order.getDateCreated(),new Date())>7){
                    order.setComplateType(BaseDomain.COMPLATE_TYPE_TWO);
                    orderArray.add(order);
                }
            }
            _logger.info("开始定时任务 系统自动确认7天前待完成订单------共有[{}]条数据。",orderArray.size());
            //批量更新
            if(orderArray.size()!=0){
                for(Order order : orderArray){
                    order.setOrderStatus(BaseDomain.ORDER_STATUS_FOUR);
                    orderUserService.putOrder(order);
                    //新增流转记录
                    OrderHistoryStatus historyStatus = new OrderHistoryStatus();
                    historyStatus.setHistoryStatus(BaseDomain.ORDER_STATUS_FOUR);
                    historyStatus.setOrderId(order.getId());
                    queryCompanyService.postOrderHistoryStatus(historyStatus,null);
                }
                _logger.info("系统自动确认7天前待完成订单,定时任务结束。");
            }

        } catch (Exception e) {
            _logger.error("due to running job:[{}] jobType[{}] to error [{}]","executeConfirmReceipt",JobType.CONFIRMRECEIPT,e.getStackTrace());
            e.printStackTrace();
        }
    }

    /**
     * 每周一生成上周一至周日的待生产，输出报表数据
     */
    @Scheduled(cron = "0 30 1 ? * MON")
//    @Scheduled(cron = "0 44 15 * * *")
//    @Scheduled(cron = "0/30 * * * * ? ")
//    @Scheduled(cron = "0 0 0/1 * * ? ")
//    @Scheduled(cron = "0 30 1 * * *")
    public void executeReport(){
        try {

            executorReport.reportRun();

        } catch (Exception e) {
            _logger.error("due to running job:[{}] jobType[{}] to error [{}]","executeConfirmReceipt",JobType.REPORTFILE,e.getStackTrace());
            e.printStackTrace();
        }
    }

    /**
     * 每天中午检索待运行报表任务
     */
    @Scheduled(cron = "0 10 12 * * *")
    public void executeTaskReport(){
        try {
            String currentMonday = DateUtils.currentMonday();
            TblReportTask tblReportTask = queryCompanyService.getTblReportTaskBetweenBulider(currentMonday);
            if(tblReportTask!=null && tblReportTask.getTaskStatus()==0){
                executorReport.reportRun(DateUtils.getDateYYYYMMDD(tblReportTask.getBuilderTime()));
            }else {
                _logger.error("==========未检索到待运行报表数据=============");
            }
        } catch (Exception e) {
            _logger.error("due to running executeTaskReport job:[{}] jobType[{}] to error [{}]","executeConfirmReceipt",JobType.REPORTFILE,e.getStackTrace());
            e.printStackTrace();
        }
    }

    //开机自启检测本周报表任务
    @PostConstruct
    public void taskReport() throws Exception{
        String currentMonday = DateUtils.currentMonday();
        TblReportTask tblReportTask = queryCompanyService.getTblReportTask(currentMonday);
        if(tblReportTask!=null && tblReportTask.getTaskStatus()==0){
            executorReport.reportRun(currentMonday);
        }else {
            _logger.error("==========未存在待运行报表数据=============");
        }
    }

}
