package cn.com.common.utils;

/**
 * 文件上传--优先级处理类
 * Created by Horace.zhang on 2019/10/28.
 */
public class TblHomeFileLevelUtil {
    public static String getHomeFileLevel(String homeFileLevel){
        //重新排序需插入级别前一位
        Double value = Double.valueOf(homeFileLevel.trim())-1.0;
        return value.toString();
    }
}
