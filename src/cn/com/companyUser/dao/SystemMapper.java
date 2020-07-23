package cn.com.companyUser.dao;

import cn.com.common.model.TblHome;
import cn.com.common.model.TblOrderUnit;
import cn.com.common.model.TblProductionProduct;
import cn.com.common.model.search.OrderHistoryStatusSearcher;
import cn.com.common.model.search.TblOrderUnitSearcher;
import cn.com.common.model.search.TblProductionProductSearcher;

import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/30.
 */
public interface SystemMapper {

    List<TblOrderUnitSearcher> listTblOrderUnitWithPage(TblOrderUnitSearcher tblOrderUnitSearcher);


    List<TblProductionProductSearcher> listproductionProductWithPage(TblProductionProductSearcher productionProductSearcher);

    List<TblHome> getTblHome(Map map);

    void postTblOrderUnit(TblOrderUnit tblOrderUnit);

    void postTblProductionProduct(TblProductionProduct productionProduct);

    void postTblHome(TblHome tblHome);

    TblOrderUnit getTblOrderUnit(Map map);

    void resetTblHomeFileLevel();

    void deleteTblHome(String tblHomeId);

    void updateTblHome(TblHome tblHome);
}
