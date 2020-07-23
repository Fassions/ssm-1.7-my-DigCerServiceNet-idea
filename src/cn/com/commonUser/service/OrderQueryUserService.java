package cn.com.commonUser.service;

import cn.com.common.model.OrderMessage;
import cn.com.common.model.OrderProduct;
import cn.com.common.model.UserInfo;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.search.OrderMessageSearcher;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;

/**
 * Created by Horace.zhang on 2019/7/1.
 */
public interface OrderQueryUserService {
    Page<OrderQuerySearcher> listOrderWithPage(OrderQuerySearcher orderQuerySearcher, GaUser gaUser, Limit limit);
    Page<OrderMessageSearcher> listOrderMessageWithPage(OrderMessageSearcher orderMessage, Limit limit);

}
