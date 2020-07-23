package cn.com.common.service.impl;

import cn.com.common.cache.CaCheManager;
import cn.com.common.dao.CommonMapper;
import cn.com.common.model.TblAreaInfo;
import cn.com.common.model.TblAreaLabelPrefix;
import cn.com.common.model.TblAreaLabelSuffix;
import cn.com.common.model.TblProductList;
import cn.com.common.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/6/10.
 */
@Service
public class CommServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;


    @Override
    @Cacheable(value = "cache.getAllProvince", key = "'level'+#level")
    public List<TblAreaInfo> getTblAreaInfoByLevel(String level) throws Exception{
        Map map = new HashMap<>();
        map.put("levelType",level);
        List<TblAreaInfo> data = commonMapper.getTblAreaInfo(map);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }


    @Override
    @Cacheable(value = "cache.getAllProvince", key = "'id'+#id")
    public TblAreaInfo getTblAreaInfoById(String id) throws Exception {
        Map map = new HashMap<>();
        map.put("id",id);
        List<TblAreaInfo> data = commonMapper.getTblAreaInfo(map);
        if(data.size()==0){
            return null;
        }else {
            return data.get(0);
        }
    }

    @Override
    @Cacheable(value = "cache.getAllProvince", key = "'parent'+#parent")
    public List<TblAreaInfo> getTblAreaInfoByParentId(String parent) throws Exception {
       return commonMapper.getTblAreaInfoByParentId(parent);
    }

    @Override
    public List<TblProductList> getTblProductList() throws Exception {
        return commonMapper.getTblProductList();
    }

    @Override
    public TblAreaLabelPrefix getTblProductPreffixByAreaId(String areaId) throws Exception {
        return commonMapper.getTblProductPreffixByAreaId(areaId);
    }

    @Override
    public List<TblAreaLabelSuffix> getTblAreaLabelSuffixByProductListIdAndLabelPreffix(String productListId, String labelPreffixId) throws Exception {
        Map map = new HashMap<>();
        map.put("productListId",productListId);
        map.put("labelPreffixId",labelPreffixId);
        List<TblAreaLabelSuffix> data = commonMapper.getTblTblAreaLabelSuffix(map);
        if(data.size()==0){
            return null;
        }else {
            return data;
        }
    }
}
