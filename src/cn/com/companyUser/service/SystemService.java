package cn.com.companyUser.service;

import cn.com.common.model.TblHome;
import cn.com.common.model.TblOrderUnit;
import cn.com.common.model.TblProductionProduct;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.search.OrderQuerySearcher;
import cn.com.common.model.search.TblOrderUnitSearcher;
import cn.com.common.model.search.TblProductionProductSearcher;
import cn.com.common.page.Limit;
import cn.com.common.page.Page;

import java.util.List;

/**
 * Created by Horace.zhang on 2019/7/30.
 */
public interface SystemService {

    TblOrderUnit getTblOrderUnitByOrderUnitName(String orderUnitName) throws Exception;

    void postTblOrderUnit(TblOrderUnit orderUnit,String userId) throws Exception;

    void postTblProductionProduct(TblProductionProduct productionProduct,String userId);

    void postTblHome(TblHome tblHome,String userId);

    List<TblHome> getAllTblHome();

    List<TblHome> getTblHomeByFileLevel(String fileLevel);

    TblHome getTblHomeById(String TblHomeId);

    void deleteTblHome(String tblHomeId);

    void updateTblHomeByLevelAndId(TblHome tblHome,String userId);

    Page<TblOrderUnitSearcher> listUserUnitWithPage(TblOrderUnitSearcher orderUnitSearcher, GaUser gaUser, Limit limit) throws Exception;

    Page<TblProductionProductSearcher> listproductionProductWithPage(TblProductionProductSearcher productionProductSearcher, GaUser gaUser, Limit limit) throws Exception;
}
