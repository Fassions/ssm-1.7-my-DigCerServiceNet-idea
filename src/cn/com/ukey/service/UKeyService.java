package cn.com.ukey.service;

import cn.com.common.model.OrderProduct;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.search.TblProductionProductSearcher;
import cn.com.ukey.model.UKeyInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/8/5.
 */
public interface UKeyService {

    /**
     * 验证产品编号 是否已经被使用
     * @param map
     * @return
     */
    List<TblProductionProductSearcher> verifyUkeyInfoByLabelProduct(Map map);
}
