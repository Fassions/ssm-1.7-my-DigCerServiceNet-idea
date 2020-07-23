package cn.com.ukey.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Horace.zhang on 2019/8/5.
 */
public class UKeyUtils {
    public static void getUKeyInfo(UKeyInfo info){
        if(StringUtils.isBlank(info.getProductListId())){
            return;
        }

        if("1".equals(info.getProductListId())){   // 1	公安数字证书
            info.setDateBase("bdtechproductPG");
            info.setColumnLabelName("出厂编号");
        }else if("2".equals(info.getProductListId())){  //2	警辅数字证书
            info.setDateBase("bdtechproductPF");
            info.setColumnLabelName("出厂编号");
        }else if("4".equals(info.getProductListId())){  //4	公安指纹数字证书
            info.setDateBase("bdtechproductZG");
            info.setColumnLabelName("证书标号");
        }else if("5".equals(info.getProductListId())){ //5	警辅指纹数字证书
            info.setDateBase("bdtechproductZF");
            info.setColumnLabelName("证书标号");
        }else if("6".equals(info.getProductListId())){  //6	专网数字证书
            info.setDateBase("bdtechproductVPN");
            info.setColumnLabelName("出厂编号");
        }
    }
}
