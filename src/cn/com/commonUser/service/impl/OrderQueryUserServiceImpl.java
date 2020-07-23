package cn.com.commonUser.service.impl;

import cn.com.common.model.Order;
import cn.com.common.model.OrderMessage;
import cn.com.common.model.UserInfo;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.search.OrderMessageSearcher;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.common.page.PageUtil;
import cn.com.commonUser.dao.OrderUserMapper;
import cn.com.commonUser.service.OrderQueryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Horace.zhang on 2019/7/1.
 */
@Service("orderQueryUserService")
public class OrderQueryUserServiceImpl implements OrderQueryUserService {

    @Autowired
    private OrderUserMapper orderUserMapper;

    @Override
    public Page<OrderQuerySearcher> listOrderWithPage(OrderQuerySearcher orderQuerySearcher, GaUser gaUser, Limit limit) {
        //选择当前用户 添加userId
        if("0".equals(orderQuerySearcher.getOrderUser())){
            orderQuerySearcher.setUserId(gaUser.getUserId());
        }else if("1".equals(orderQuerySearcher.getOrderUser())){
            orderQuerySearcher.setOrderUnitName(gaUser.getOrderUnitName());
        }
        List<OrderQuerySearcher> querySearchers = orderUserMapper.listWithOrderQuery(orderQuerySearcher);
        Page<OrderQuerySearcher> page = PageUtil.listToPage(querySearchers,limit,querySearchers.size());
        //TODO:检索多行
        return page;
    }

    @Override
    public Page<OrderMessageSearcher> listOrderMessageWithPage(OrderMessageSearcher orderMessage, Limit limit) {
        List<OrderMessageSearcher> querySearchers = orderUserMapper.listWithOrderMessage(orderMessage);
        Page<OrderMessageSearcher> page = PageUtil.listToPage(querySearchers,limit,querySearchers.size());
        //TODO:检索多行
        return page;
    }
}
