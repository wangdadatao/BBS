package com.datao.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * Created by 海涛 on 2016/4/14.
 * 缓存类
 */

public class EhCacheUtil {
    private static CacheManager cacheManager = new CacheManager();

    //设置缓存中的值
    public static void set(String templeteName, String key, Object value) {
        Ehcache ehcache = cacheManager.getEhcache(templeteName);
        Element element = new Element(key, value);
        ehcache.put(element);
    }

    //得到缓存中的值
    public static Object get(String templeteName, String key) {
        Ehcache ehcache = cacheManager.getEhcache(templeteName);
        Element element = ehcache.get(key);
        if (element != null) {
            return element.getObjectValue();
        }else{
            return null;
        }
    }
}
