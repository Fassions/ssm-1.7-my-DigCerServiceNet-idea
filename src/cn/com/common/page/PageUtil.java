package cn.com.common.page;


import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Horace.zhang on 2019/7/1.
 */
public class PageUtil {

    public static final <T extends Serializable> Page<T> listToPage(List<T> list, Limit limit,int count){
        if(limit.getOffset()<0){
            limit.setOffset(0);
        }
        int limitCount = limit.getCount();
        if(limit.getOffset()+limit.getCount()>list.size()){
            limitCount = list.size();
        }else {
            limitCount = limit.getOffset()+limit.getCount();
        }
        if (!CollectionUtils.isEmpty(list)) {
            list = list.subList(limit.getOffset(), limitCount);
        }
        Page<T> page = new Page<>();
        if (CollectionUtils.isEmpty(list)){
            List<T>  adminList = new ArrayList<>();
            page.setData(adminList);
        }else {
            page.setData(list);
        }
        page.setLimit(limit);
        page.setTotal(count);
        return page;
    }
    public static final <T> Page<T> listToPages(List<T> list, Limit limit,int count){
        Page<T> page = new Page<>();
        if (CollectionUtils.isEmpty(list)){
            List<T>  adminList = new ArrayList<>();
            page.setData(adminList);
        }else {
            page.setData(list);
        }
        page.setLimit(limit);
        page.setTotal(count);
        return page;
    }

    /*
     * 截取列表
     */
    public static <T> List<T> getSubListPage(List<T> list, int skip,
                                             int pageSize) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int startIndex = skip;
        int endIndex = skip + pageSize;
        if (startIndex > endIndex || startIndex > list.size()) {
            return null;
        }
        if (endIndex > list.size()) {
            endIndex = list.size();
        }
        return list.subList(startIndex, endIndex);
    }
}
