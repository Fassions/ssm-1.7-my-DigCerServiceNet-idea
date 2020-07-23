package cn.com.common.utils;

import cn.com.common.agent.BaseEntity;
import com.sun.xml.rpc.processor.util.StringUtils;

import java.util.Date;

/**
 * Created by Horace.zhang on 2019/6/18.
 */
public class PutDateSource {
    public static BaseEntity createDate(String id,String userId){
        BaseEntity base = new BaseEntity();
        if(org.apache.commons.lang3.StringUtils.isBlank(id)){
            base.setIdUUID();
        }else{
            base.setId(id);
        }
        base.setDateCreated(new Date());
        base.setUserCreated(userId);
        base.setDel(false);
        return base;
    }
    public static BaseEntity updateDate(String id,String userId){
        BaseEntity base = new BaseEntity();
        base.setId(id);
        base.setDateModified(new Date());
        base.setUserModified(userId);
        base.setDel(false);
        return base;
    }
}
