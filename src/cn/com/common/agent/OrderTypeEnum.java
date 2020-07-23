package cn.com.common.agent;

/**
 * 订单状态 enum
 * Created by Horace.zhang on 2019/7/22.
 */
public enum  OrderTypeEnum {
    PRODUCTTYPEZERO(0,"订购"),
    PRODUCTTYPEONE(1,"代购"),
    PRODUCTTYPETWO(2,"项目内"),
    PRODUCTTYPETHREE(3,"赠送"),
    PRODUCTTYPEFOUR(4,"提前发货"),
    PRODUCTTYPEFIVE(5,"合同已包含");

    public int code;
    public String name;

    OrderTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    OrderTypeEnum() {
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
        for(OrderTypeEnum o : OrderTypeEnum.values()){
            if(o.getCode()==index){
                return o.getName();
            }
        }
        return null;
    }


}
