package cn.com.common.dao;

import cn.com.common.model.OrderProduct;
import cn.com.common.model.TblOrderUnit;
import cn.com.common.model.TblProductionProduct;
import cn.com.common.model.search.TblProductionProductSearcher;

import java.util.List;
import java.util.Map;

/**
 * 证书产品 查询通用
 * Created by Horace.zhang on 2019/8/1.
 */
public interface ProductMapper {

    /**
     *  产品开始编号，产品结束编号。查看订单产品是否存在。
     *  订单状态为已接收以后，不等于退回，不等于作废。
     * @param map  labelStart,  labelEnd
     * @return
     */
    List<TblProductionProductSearcher> getOrderProductBetween(Map map);


    //已售证书编号 是否存在数据
    List<TblProductionProductSearcher> getTblProductionProductByLabelStartLabelEnd(Map map);
}
