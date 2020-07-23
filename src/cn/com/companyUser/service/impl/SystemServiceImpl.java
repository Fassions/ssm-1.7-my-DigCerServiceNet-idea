package cn.com.companyUser.service.impl;

import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.OrderUploadFiles;
import cn.com.common.model.TblHome;
import cn.com.common.model.TblOrderUnit;
import cn.com.common.model.TblProductionProduct;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.export.ProductionExcel;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.model.search.TblOrderUnitSearcher;
import cn.com.common.model.search.TblProductionProductSearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;
import cn.com.common.page.PageUtil;
import cn.com.common.service.ProductService;
import cn.com.common.utils.ProductUtils;
import cn.com.common.utils.PutDateSource;
import cn.com.common.utils.ZipUtils;
import cn.com.companyUser.dao.SystemMapper;
import cn.com.companyUser.service.SystemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/30.
 */
@Service
public class SystemServiceImpl implements SystemService{

    @Autowired
    SystemMapper systemMapper;

    @Autowired
    ProductService productService;

    @Override
    public TblOrderUnit getTblOrderUnitByOrderUnitName(String orderUnitName) throws Exception {
        Map map = new HashMap<>();
        map.put("orderUnitName",orderUnitName);
        return systemMapper.getTblOrderUnit(map);
    }

    @Override
    public void postTblOrderUnit(TblOrderUnit orderUnit,String userId) throws Exception {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(orderUnit.getId(),userId),orderUnit);
        systemMapper.postTblOrderUnit(orderUnit);
    }

    @Override
    public void postTblProductionProduct(TblProductionProduct productionProduct,String userId) {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(productionProduct.getId(),userId),productionProduct);
        systemMapper.postTblProductionProduct(productionProduct);
    }
    @Override
    public void postTblHome(TblHome tblHome,String userId) {
        BeanUtilsEx.copyProperties(PutDateSource.createDate(tblHome.getId(),userId),tblHome);
//        新增文件信息
        systemMapper.postTblHome(tblHome);
//        重置文件排列顺序别
        systemMapper.resetTblHomeFileLevel();
    }

    @Override
    public List<TblHome> getAllTblHome() {
        return  systemMapper.getTblHome(null);
    }

    @Override
    public List<TblHome> getTblHomeByFileLevel(String fileLevel) {
        Map map = new HashMap<>();
        map.put("fileLevel",fileLevel);
        List<TblHome> param = systemMapper.getTblHome(map);
        if(param.size()!=0){
            return param;
        }else {
            return null;
        }
    }
    @Override
    public TblHome getTblHomeById(String id) {
        Map map = new HashMap<>();
        map.put("id",id);
        List<TblHome> param = systemMapper.getTblHome(map);
        if(param.size()!=0){
            return param.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void deleteTblHome(String tblHomeId) {
        TblHome tblHome = getTblHomeById(tblHomeId);
        if(tblHome!=null){
            ZipUtils.deleteDir(tblHome.getFileName()+tblHome.getFileName());
        }
        systemMapper.deleteTblHome(tblHomeId);
    }

    @Override
    public void updateTblHomeByLevelAndId(TblHome tblHome,String userId) {
//        tblHome.setTbl
        BeanUtilsEx.copyProperties(PutDateSource.updateDate(tblHome.getId(),userId),tblHome);
//        更改
        systemMapper.updateTblHome(tblHome);
//        重置文件排列顺序别
        systemMapper.resetTblHomeFileLevel();
    }

    @Override
    public Page<TblOrderUnitSearcher> listUserUnitWithPage(TblOrderUnitSearcher orderUnitSearcher, GaUser gaUser, Limit limit) throws Exception {
        List<TblOrderUnitSearcher> querySearchers = systemMapper.listTblOrderUnitWithPage(orderUnitSearcher);
        Page<TblOrderUnitSearcher> page = PageUtil.listToPage(querySearchers,limit,querySearchers.size());
        //TODO:检索多行
        return page;
    }

    @Override
    public Page<TblProductionProductSearcher> listproductionProductWithPage(TblProductionProductSearcher productionProductSearcher, GaUser gaUser, Limit limit) throws Exception {


        //已售证书表查询
        //List<TblProductionProductSearcher> querySearchers = systemMapper.listproductionProductWithPage(productionProductSearcher);
        //组装全字段
        ProductUtils.getlabelStartEndNumberAmount(productionProductSearcher);  //拼接需要字段值

        Map map = new HashMap<>();
        map.put("labelStart",productionProductSearcher.getLabelStart());
        map.put("labelEnd",productionProductSearcher.getLabelEnd());
        map.put("labelNumStart",productionProductSearcher.getLabelNumStart());
        map.put("labelNumEnd",productionProductSearcher.getLabelNumEnd());
        map.put("productListId",productionProductSearcher.getProductListId());
        map.put("labelPrefix",productionProductSearcher.getLabelPrefix());
        //数据校验查询
        List<TblProductionProductSearcher> querySearchers = productService.verifyOrderProductBetween(map);
        Page<TblProductionProductSearcher> page = PageUtil.listToPage(querySearchers,limit,querySearchers.size());
        return page;
    }
}
