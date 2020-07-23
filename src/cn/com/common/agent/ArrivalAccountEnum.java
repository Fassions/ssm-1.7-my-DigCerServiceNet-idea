package cn.com.common.agent;

/**
 * 到款账号 enum
 * Created by Horace.zhang on 2019/7/22.
 */
public enum  ArrivalAccountEnum {
    PRODUCTTYPEZERO(0,"公司"),
    PRODUCTTYPEONE(1,"三所");

    public int code;
    public String name;


    ArrivalAccountEnum(int code, String name) {
        this.code = code;
        this.name = name;
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
        for(ArrivalAccountEnum o : ArrivalAccountEnum.values()){
            if(o.getCode()==index){
                return o.getName();
            }
        }
        return null;
    }
}
