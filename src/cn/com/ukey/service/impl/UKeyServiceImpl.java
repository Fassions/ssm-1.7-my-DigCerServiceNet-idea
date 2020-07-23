package cn.com.ukey.service.impl;

import cn.com.common.model.Order;
import cn.com.common.model.OrderProduct;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.search.TblProductionProductSearcher;
import cn.com.common.utils.PutDateSource;
import cn.com.filter.URLFilter;
import cn.com.ukey.dao.UKeyInfoMapper;
import cn.com.ukey.model.UKeyInfo;
import cn.com.ukey.model.UKeyUtils;
import cn.com.ukey.service.UKeyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/8/5.
 */
@Service
public class UKeyServiceImpl implements UKeyService {

    @Autowired
    private UKeyInfoMapper keyInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(UKeyServiceImpl.class);


    @Override
    public List<TblProductionProductSearcher> verifyUkeyInfoByLabelProduct(Map map) {
        UKeyInfo uKeyInfo = new UKeyInfo();
        uKeyInfo.setLabelStart(map.get("labelStart").toString());
        uKeyInfo.setLabelEnd(map.get("labelEnd").toString());
        uKeyInfo.setProductListId(map.get("productListId").toString());
        uKeyInfo.setLabelPrefix(map.get("labelPrefix").toString());
        //BeanUtilsEx.copyProperties(vo,uKeyInfo);
        UKeyUtils.getUKeyInfo(uKeyInfo);
        if(StringUtils.isAnyBlank(uKeyInfo.getDateBase(),uKeyInfo.getLabelPrefix(),uKeyInfo.getColumnLabelName())){
            logger.error(uKeyInfo.getLabelStart(),uKeyInfo.getLabelEnd()+"---所验证的生产数据库，缺失必要条件。");
            return null;
        }
        List<TblProductionProductSearcher> data = keyInfoMapper.getUkeyInfoByLabelStartEnd(uKeyInfo);
        return data;
    }
}
