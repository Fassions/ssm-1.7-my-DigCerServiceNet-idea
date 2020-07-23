package cn.com.common.service;

import cn.com.common.model.OrderProduct;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.search.TblProductionProductSearcher;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/8/1.
 */
public interface ProductService {

    //验证证书产品是否已经被使用 true 存在 false 不存在
    Boolean verifyOrderProductBetween(OrderProduct product) throws Exception;

    Boolean verifyOrderProductByOrderId(String orderId) throws Exception;

    List<TblProductionProductSearcher> verifyOrderProductBetween(Map map) throws Exception;

    /**
     * 暂不使用
     * 查询 当前用户是否有需要合并的证书
     * 结束编号+1 等于orderProduct 当前用户 里面的起始编号
     * 或
     * 起始编号-1 等于orderProduct 当前用户 里面的结束编号
     * @param userId
     * @param labelStart 起始编号
     * @param labelEnd 结束编号
     * @return
     * @throws Exception
     */
    //查询 当前用户是否有需要合并的证书
    //起始编号-1 等于orderProduct 当前用户 里面的结束编号
    //或
    //结束编号+1 等于orderProduct 当前用户 里面的起始编号
    OrderProduct getOrderProductByUserIdLabelStartLabelEnd(String userId,String labelStart,String labelEnd) throws Exception;

}
