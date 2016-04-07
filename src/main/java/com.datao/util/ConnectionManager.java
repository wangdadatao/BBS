package com.datao.util;

import com.datao.exception.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class ConnectionManager {
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName(ConfigProp.get("jdbc.Driver"));
        dataSource.setUrl(ConfigProp.get("jdbc.url"));
        dataSource.setUsername(ConfigProp.get("jdbc.username"));
        dataSource.setPassword(ConfigProp.get("jdbc.password"));

        dataSource.setInitialSize(5);
        dataSource.setMaxIdle(20);
        dataSource.setMinIdle(5);
        dataSource.setMaxWaitMillis(5000);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}