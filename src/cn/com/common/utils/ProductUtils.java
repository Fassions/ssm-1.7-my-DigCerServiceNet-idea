package cn.com.common.utils;

import cn.com.common.agent.BaseDomain;
import cn.com.common.model.Order;
import cn.com.common.model.OrderProduct;
import cn.com.common.model.dto.OrderVo;
import cn.com.common.model.search.TblProductionProductSearcher;
import cn.com.common.service.ProductService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * 证书产品工具类
 * Created by Horace.zhang on 2019/7/25.
 */
public class ProductUtils {


    @Autowired
    ProductService  productService;


    /**
     *     验证证书产品是否连号
     * @param productList
     * @param product
     * @param entity
     * @return
     */
//    public static List<String>  getPutProductStartEnd(List<OrderProduct> productList, OrderProduct product){
////        for(OrderProduct pro : productList){
////            Integer num = pro.getLabelStart()+pro.getLabelEnd();
////        }
//        return null;
//    }

//    //验证证书产品是否已经被使用 true 存在 false 不存在
//    public static Boolean verifyOrderProduct(OrderVo vo) throws Exception {
//        if ("0".equals(vo.getProductListId())) {  //配件不需要验证
//            return null;
//        }
//        Boolean value = productService.verifyOrderProductBetween(vo.getLabelStart(), vo.getLabelEnd());
//        return value;
//    }

    //验证 当前用户是否有需要合并的证书
    //null 新添加
//    public Order verifyPutOrderProduct(OrderVo vo) throws Exception {
//
//     // 结束编号+1 等于orderProduct 当前用户 里面的起始编号
//                // 或
//                // 起始编号-1 等于orderProduct 当前用户 里面的结束编号
//        OrderVo orderVo = vo;
//        Integer numStart = IntegerUtil.parseInt(orderVo.getLabelNumStart())-1;
//        Integer numEnd = IntegerUtil.parseInt(orderVo.getLabelNumEnd()+1);
//        orderVo.setLabelNumStart(numStart.toString());
//        orderVo.setLabelNumEnd(numEnd.toString());
//        //同一用户，是否存在可连续产品
//        OrderProduct orderProduct = productService.getOrderProductByUserIdLabelStartLabelEnd(orderVo.getUserId(),orderVo.getLabelStart(),orderVo.getLabelEnd());
//        if(orderProduct==null){
//            return null;
//        }else {
//
//        }
//    }

    public static void getlabelStartEndNumberAmount(Object entity) throws Exception{
//        System.out.println("kk{},{}"+entity.getClass().getName()+"{}"+entity);
        String value = "";	//合并比对值
        Class cls = entity.getClass();
        Field productListIdf = cls.getDeclaredField("productListId");
        productListIdf.setAccessible(true);
        String productListId = productListIdf.get(entity)+"";
        Field supplierf = cls.getDeclaredField("supplier");
        supplierf.setAccessible(true);
        String supplier = supplierf.get(entity)!=null?supplierf.get(entity)+"":"";
        Field labelPrefixf = cls.getDeclaredField("labelPrefix");
        labelPrefixf.setAccessible(true);
        String labelPrefix = labelPrefixf.get(entity)+"";
        Field labelSuffixf = cls.getDeclaredField("labelSuffix");
        labelSuffixf.setAccessible(true);
        String labelSuffix = labelSuffixf.get(entity)+"";
        Field labelNumStartf = cls.getDeclaredField("labelNumStart");
        labelNumStartf.setAccessible(true);
        String labelNumStart = labelNumStartf.get(entity)+"";
        Field labelNumEndf = cls.getDeclaredField("labelNumEnd");
        labelNumEndf.setAccessible(true);
        String labelNumEnd = labelNumEndf.get(entity)+"";
        Field productRegionLabelInfixf = cls.getDeclaredField("productRegionLabelInfix");
        productRegionLabelInfixf.setAccessible(true);
        String productRegionLabelInfix = productRegionLabelInfixf.get(entity)+"";
        Field labelStartf = cls.getDeclaredField("labelStart");
        labelStartf.setAccessible(true);
        String labelStart = labelStartf.get(entity)+"";
        Field labelEndf = cls.getDeclaredField("labelEnd");
        labelEndf.setAccessible(true);
        String labelEnd = labelEndf.get(entity)+"";
        Field productNumberf = cls.getDeclaredField("productNumber");
        productNumberf.setAccessible(true);
        Integer ProductNumber = (Integer) productNumberf.get(entity);

        Field productPricef = cls.getDeclaredField("productPrice");
        productPricef.setAccessible(true);
        Double productPrice = (Double) productPricef.get(entity);

        Field productAmountf = cls.getDeclaredField("productAmount");
        productAmountf.setAccessible(true);
        Double productAmount = (Double) productAmountf.get(entity);

        //产品数量，编号开始-编号结束
        if(!Objects.equals(BaseDomain.PRODUCTLISTID_ZERO.toString(),productListId+"")){
            productNumberf.set(entity,Integer.parseInt(labelNumEnd)-Integer.parseInt(labelNumStart)+1);
            if(Objects.equals(BaseDomain.PRODUCTLISTID_THREE.toString(),productListId+"")){
                labelStartf.set(entity,labelPrefix+supplier+productRegionLabelInfix+labelSuffix+labelNumStart);
                labelEndf.set(entity,labelPrefix+supplier+productRegionLabelInfix+labelSuffix+labelNumEnd);
            }else if("6".equals(productListId)){
                labelStartf.set(entity,labelSuffix+labelPrefix+labelNumStart);
                labelEndf.set(entity,labelSuffix+labelPrefix+labelNumEnd);
            }else {
                labelStartf.set(entity,labelPrefix+supplier+labelNumStart+labelSuffix);
                labelEndf.set(entity,labelPrefix+supplier+labelNumEnd+labelSuffix);
            }
            if(Objects.equals(BaseDomain.PRODUCTLISTID_THREE.toString(),productListId+"")){ //TODO: 解锁卡拼接前缀 解锁卡 地区合并前缀
                labelPrefixf.set(entity,labelPrefix+productRegionLabelInfix);
            }
        }
        Double amount = null;
        if(productPrice!=null && (Integer) productNumberf.get(entity)!=null){
            //TODO:数值过长会被转换科学计数
            amount = Double.valueOf(productPrice)*Double.valueOf((Integer) productNumberf.get(entity));
        }
        if(amount!=null){
            //产品金额
            productAmountf.set(entity,amount);
        }

    }

    //已弃用，使用getlabelStartEndNumberAmount
    public static void text(OrderVo vo){
        //产品数量，编号开始-编号结束
        if(!Objects.equals(BaseDomain.PRODUCTLISTID_ZERO.toString(),vo.getProductListId()+"")){
            vo.setProductNumber(Integer.parseInt(vo.getLabelNumEnd())-Integer.parseInt(vo.getLabelNumStart())+1);
            if(Objects.equals(BaseDomain.PRODUCTLISTID_THREE.toString(),vo.getProductListId()+"")){
                vo.setLabelStart(vo.getLabelPrefix()+vo.getSupplier()+vo.getProductRegionLabelInfix()+vo.getLabelSuffix()+vo.getLabelNumStart());
                vo.setLabelEnd(vo.getLabelPrefix()+vo.getSupplier()+vo.getProductRegionLabelInfix()+vo.getLabelSuffix()+vo.getLabelNumEnd());

            }else if(Objects.equals(BaseDomain.PRODUCTLISTID_SIX.toString(),vo.getProductListId()+"")){
                vo.setLabelStart(vo.getLabelSuffix()+vo.getLabelPrefix()+vo.getLabelNumStart());
                vo.setLabelEnd(vo.getLabelSuffix()+vo.getLabelPrefix()+vo.getLabelNumEnd());
            }else {
                vo.setLabelStart(vo.getLabelPrefix()+vo.getSupplier()+vo.getLabelNumStart()+vo.getLabelSuffix());
                vo.setLabelEnd(vo.getLabelPrefix()+vo.getSupplier()+vo.getLabelNumEnd()+vo.getLabelSuffix());
            }
            if(Objects.equals(BaseDomain.PRODUCTLISTID_THREE.toString(),vo.getProductListId()+"")) {
                //TODO: 解锁卡拼接前缀 解锁卡 地区合并前缀
                vo.setLabelPrefix(vo.getLabelPrefix() + vo.getProductRegionLabelInfix());
            }
        }
        Double amount = null;
        if(vo.getProductPrice()!=null && vo.getProductNumber()!=null){
            //TODO:数值过长会被转换科学计数
            amount = vo.getProductPrice()*vo.getProductNumber();
        }
        //产品金额
        vo.setProductAmount(amount);
    }
}
