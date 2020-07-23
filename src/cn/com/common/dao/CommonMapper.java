package cn.com.common.dao;

import cn.com.common.model.TblAreaInfo;
import cn.com.common.model.TblAreaLabelPrefix;
import cn.com.common.model.TblAreaLabelSuffix;
import cn.com.common.model.TblProductList;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/6/10.
 */
public interface CommonMapper {

    //根据省市等级查看信息
    List<TblAreaInfo> getTblAreaInfo(Map map);

    List<TblAreaInfo> getTblAreaInfoByParentId(String parent);

    List<TblProductList> getTblProductList();

    TblAreaLabelPrefix getTblProductPreffixByAreaId(String areaId);

    List<TblAreaLabelSuffix> getTblTblAreaLabelSuffix(Map map);
}
