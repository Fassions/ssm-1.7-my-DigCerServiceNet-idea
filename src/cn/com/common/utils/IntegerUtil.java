package cn.com.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Horace.zhang on 2019/5/31.
 */
public class IntegerUtil {
    public static boolean equals(Integer num,Integer num2){
        if(num==null && num2 ==null){
            return false;
        }else if(num ==null && num2 !=null){
            return false;
        }else if(num != num2){
            return false;
        }else if(num == num2){
            return true;
        }
        return true;
    }
    public static Integer parseInt(String s){
        if(StringUtils.isBlank(s)){
            return null;
        }else {
            return Integer.parseInt(s);
        }
    }
}
