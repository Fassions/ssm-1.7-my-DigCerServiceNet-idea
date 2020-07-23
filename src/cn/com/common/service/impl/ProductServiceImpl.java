package cn.com.common.service.impl;

import cn.com.common.agent.BaseDomain;
import cn.com.common.dao.ProductMapper;
import cn.com.common.model.OrderProduct;
import cn.com.common.model.TblAreaInfo;
import cn.com.common.model.TblProductionProduct;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.search.TblProductionProductSearcher;
import cn.com.common.service.ProductService;
import cn.com.commonUser.service.OrderUserService;
import cn.com.ukey.service.UKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Horace.zhang on 2019/8/1.
 */
@Service
public class ProductServiceImpl implements ProductService{

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductMapper productMapper;
    @Autowired
    UKeyService uKeyService;
    @Autowired
    OrderUserService orderUserService;



    @Override
    public Boolean verifyOrderProductByOrderId(String orderId) throws Exception {
        List<OrderProduct> products = orderUserService.getOrderProductByOrderId(orderId);
        for(OrderProduct product : products){
            //添加产品 需要验证自身orderId,其他验证无需添加OrderId 验证本订单
            product.setOrderId(null);
            Boolean verify = verifyOrderProductBetween(product);
            if(verify){
                return true;
            }
         }
        return false;
    }

    /**
     * 添加产品 需要验证自身orderId,其他验证无需添加OrderId
     * @param product
     * @return
     * @throws Exception
     */
    @Override
    public Boolean verifyOrderProductBetween(OrderProduct product) throws Exception {
        if("0".equals(product.getProductListId())){ //配件无需验证
            return false;
        }
        //证书服务网库验证
        Map map = new HashMap<>();
        map.put("labelStart",product.getLabelStart());
        map.put("labelEnd",product.getLabelEnd());
        map.put("labelNumStart",product.getLabelNumStart());
        map.put("labelNumEnd",product.getLabelNumEnd());
        map.put("orderId",product.getOrderId());
        map.put("productListId",product.getProductListId());
        map.put("labelPrefix",product.getLabelPrefix());

        List<TblProductionProductSearcher> products = verifyOrderProductBetween(map);
        if(products.size()!=0){
            return true;
        }
        return false;
    }


    /**
     * 添加产品 需要验证自身orderId,其他验证无需添加OrderId
     * @param map
     * map.put labelStart
     * map.put labelEnd
     * map.put productListId  证书标号Id[生产库需要]
     * map.put labelPrefix  证书标号前缀[生产库需要]
     * map.put orderId  [无则无需填写]
     * @return
     * @throws Exception
     */
    public List<TblProductionProductSearcher> verifyOrderProductBetween(Map map) throws Exception {
        List<TblProductionProductSearcher> products = new ArrayList<>();
        //验证本应用库
        List<TblProductionProductSearcher> data = productMapper.getOrderProductBetween(map);
        products.addAll(data);
        //验证已售证书标号
        List<TblProductionProductSearcher> data1= productMapper.getTblProductionProductByLabelStartLabelEnd(map);
        products.addAll(data1);
        //验证生产部数据库
        try {
            List<TblProductionProductSearcher> data2= uKeyService.verifyUkeyInfoByLabelProduct(map);
            products.addAll(data2);
        }catch (Exception e){
            logger.error("验证生产数据库，异常"+e);
            e.printStackTrace();
        }
        //TODO:存在多需验证数据源 继续添加
        return products;

    }



    @Override
    public OrderProduct getOrderProductByUserIdLabelStartLabelEnd(String userId, String labelStart, String labelEnd) throws Exception {
        return null;
    }
}
