package cn.com.common.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;

/**
 * Created by Horace.zhang on 2019/12/16.
 */

public class CaCheManager{

    private static final String path = "/WEB-INF/spring/ehcache.xml";
    private URL url;
    private CacheManager cacheManager;
    private static CaCheManager caCheManager;

    private CaCheManager (String path){
        url=getClass().getResource(path);
        cacheManager = CacheManager.create(url);
    }

    public static CaCheManager getInstance(){
        if(caCheManager==null){
            caCheManager = new CaCheManager(path);
        }
        return caCheManager;
    }

    public void put(String cacheName,String key,Object value){
        Cache cache = cacheManager.getCache(cacheName);
        Element element = new Element(key,value);
        cache.put(element);
    }

    public  Object get(String cacheName,String key){
        Cache cache = cacheManager.getCache(cacheName);
        if(cache!=null){
            Element element  = cache.get(key);
            return element == null ? null : element.getObjectValue();
        }
        return null;
    }

    public  Cache get(String cacheName){
        return cacheManager.getCache(cacheName);
    }

    public  void remove(String cacheName,String key){
        Cache cache = cacheManager.getCache(cacheName);
        cache.remove(key);
    }
    public void removeAll(String cacheName){
        Cache cache = cacheManager.getCache(cacheName);
        cache.removeAll();
    }

    public static void main(String[] args) {
        CaCheManager caCheManager = CaCheManager.getInstance();
        String test = (String) caCheManager.get("ehcache.getOrderInvoiceNum","id");
        System.out.println(test);
        if(StringUtils.isBlank(test)){
            caCheManager.put("ehcache.getOrderInvoiceNum","id","测试value值");
            System.out.println(test);
        }
        test = (String) caCheManager.get("ehcache.getOrderInvoiceNum","id");
        System.out.println(test);
        caCheManager.remove("ehcache.getOrderInvoiceNum","id");
        test = (String) caCheManager.get("ehcache.getOrderInvoiceNum","id");
        System.out.println(test);
    }


}
