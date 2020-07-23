package cn.com.common.service;

import cn.com.common.model.*;

import java.util.List;

/**
 * Created by Horace.zhang on 2019/6/10.
 */
public interface CommonService {

    List<TblAreaInfo> getTblAreaInfoByLevel(String Level) throws Exception;

    TblAreaInfo getTblAreaInfoById(String id) throws Exception;

    List<TblAreaInfo> getTblAreaInfoByParentId(String parent) throws Exception;

    List<TblProductList> getTblProductList() throws Exception;

    TblAreaLabelPrefix getTblProductPreffixByAreaId(String areaId) throws Exception;

    List<TblAreaLabelSuffix> getTblAreaLabelSuffixByProductListIdAndLabelPreffix(String productListId,String labelPreffixId) throws Exception;
}
