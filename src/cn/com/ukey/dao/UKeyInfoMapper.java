package cn.com.ukey.dao;

import cn.com.common.model.search.TblProductionProductSearcher;
import cn.com.ukey.model.UKeyInfo;

import java.util.List;

/**
 * Created by Horace.zhang on 2019/8/5.
 */
public interface UKeyInfoMapper {
    List<TblProductionProductSearcher> getUkeyInfoByLabelStartEnd(UKeyInfo uKeyInfo);
}
