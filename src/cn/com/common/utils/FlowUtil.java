package cn.com.common.utils;

import cn.com.common.agent.OrderTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

/**
 * Created by Horace.zhang on 2019/7/12.
 * 流水号工具类
 */
public class FlowUtil {

    public enum  ProductTypeEnum {
        DD(0,"DD"),
        YX(-1,"YX"),
        GA(1,"GA"),
        FJ(2,"FJ"),
        GAJM(3,"GAJM"),
        GZ(4,"GZ"),
        FZ(5,"FZ"),
        ZW(6,"ZW");
        public int code;
        public String name;
        ProductTypeEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }
        ProductTypeEnum() {
        }
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getName(Integer index){
            if(index==null){
                return null;
            }
            for(ProductTypeEnum o : ProductTypeEnum.values()){
                if(o.getCode()==index){
                    return o.getName();
                }
            }
            return null;
        }
    }
    //转换生产任务单号字母后缀
    public static String getProductLetter(String productListId,String productName){
        if("0".equals(productListId)){ //配件判断吊带、延长线
            if("吊带".equals(productName)){
                return "-" + ProductTypeEnum.getName(0);
            }else if("延长线".equals(productName)){
                return "-" + ProductTypeEnum.getName(-1);
            }
        }
        return "-" + ProductTypeEnum.getName(IntegerUtil.parseInt(productListId));
    }

    //创建流水号
    public static String createFlow(String maxFlowId){
        String yy = DateUtils.getYearYY();
        if(StringUtils.isBlank(maxFlowId)){
            return yy+"-001";
        }
        String prefixYear = maxFlowId.split("-")[0];
        String suffixFlow = maxFlowId.split("-")[1];
        if(!prefixYear.equals(yy)){     //年份不等于当前时间
            return yy+"-001";
        }
        int i = Integer.valueOf(suffixFlow)+1;
        StringBuffer sb = new StringBuffer();
        //累加后转出字符串
        String num = String.valueOf(i);
        //补缺位数
        for(int j = 0;j<suffixFlow.length()-num.length();j++){
            sb.append("0");
        }
        if(num.length()<=suffixFlow.length()){
            num=sb.toString()+num;
        }
        return yy+"-"+num;
    }

    /**
     *     流水号英文字母递加
     *     数据导出生产号 英文字母递增
     * @param flowId1 前一位
     * @param flowId2 当前 需要变更
     * @param flowId3 后一位
     * @return
     */
    public static String flowWhileChar(String flowId1,String flowId2,String flowId3){
        if(flowId1 ==null){
            flowId1 ="";
        }
        if(StringUtils.isBlank(flowId2)){
            return "";
        }
        if(flowId3==null){
            flowId3 = "";
        }
        String flowId1Num = flowId1.replaceAll("[a-zA-Z]","").trim();
        String flowId2Num = flowId2.replaceAll("[a-zA-Z]","").trim();
        String flowId3Num = flowId3.replaceAll("[a-zA-Z]","").trim();
        String flowId1Letter = flowId1.replaceAll("[^a-zA-Z]","").trim();
        if(StringUtils.isNotBlank(flowId3Num)){ //后一位不为空
            if(!flowId1Num.equals(flowId2Num) && !flowId2Num.equals(flowId3Num)){ //同个订单不存在多个产品
                return flowId2;
            }else if(!flowId1Num.equals(flowId2Num) && flowId2Num.equals(flowId3Num)){ //同个订单存在多个产品
                flowId2 = flowId2 + "A";
                return flowId2;
            }
        }else {     //后一位为空
            if(!flowId1Num.equals(flowId2Num)){ //同个订单不存在多个产品
                return flowId2;
            }
        }
        char b =  flowId1Letter.charAt(0);
        b = (char)(b+1);
        return flowId2+String.valueOf(b);
    }

    /**
     *     流水号数字递加
     *     数据导出生产号 英文字母递增
     * @param flowId1 前一位
     * @param flowId2 当前 需要变更
     * @param flowId3 后一位
     * @return
     */
    public static String flowProductionWhileInt(String flowId1,String flowId2,String flowId3){
        if(flowId1 ==null){
            flowId1 ="";
        }
        if(StringUtils.isBlank(flowId2)){
            return "";
        }
        if(flowId3==null){
            flowId3 = "";
        }

//        String flowId1Num = flowId1.substring(flowId1.lastIndexOf("[a-zA-Z]"+1)).trim();
        String flowId1Num = flowId1.replaceAll("_[0-9](.*)","").trim();
        String flowId2Num = flowId2.replaceAll("_[0-9](.*)","").trim();
        String flowId3Num = flowId3.replaceAll("_[0-9](.*)","").trim();
        String flowId1Letter = flowId1.substring(flowId1.lastIndexOf("_")+1).trim();
        if(StringUtils.isNotBlank(flowId3Num)){ //后一位不为空
            if(!flowId1Num.equals(flowId2Num) && !flowId2Num.equals(flowId3Num)){ //同个订单不存在多个产品
                return flowId2;
            }else if(!flowId1Num.equals(flowId2Num) && flowId2Num.equals(flowId3Num)){ //同个订单存在多个产品
                flowId2 = flowId2 + "_01";
                return flowId2;
            }
        }else {     //后一位为空
            if(!flowId1Num.equals(flowId2Num)){ //同个订单不存在多个产品
                return flowId2;
            }
        }
        String result = String.format("%02d",IntegerUtil.parseInt(flowId1Letter)+1);
        return flowId2+"_"+String.valueOf(result);
    }


    public static void main(String[] args) {
//        System.out.println(flowWhileChar("","19-002",""));
//        System.out.println(flowWhileChar("19-001A","19-001","19-003"));
//        String ss = createFlow("19-1000");
//        System.out.println(ss);

        System.out.println(flowProductionWhileInt("","19-002",""));
        System.out.println(flowProductionWhileInt("19-002-GA_01","19-001-GA","19-001-FJ"));
        String ss = createFlow("19-1000");
        System.out.println(ss);
    }
}
