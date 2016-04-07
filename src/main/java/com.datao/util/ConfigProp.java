package com.datao.util;

import com.datao.exception.DataAccessException;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by 海涛 on 2016/4/7.
 * 解析配置文件 config.properties
 */
public class ConfigProp {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigProp.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataAccessException(e, "读取配置文件出现异常");
        }
    }

    /**
     * 得到配置文件中key相对应的属性
     *
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
}
