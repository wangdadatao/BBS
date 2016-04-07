package com.datao.util;


import com.datao.exception.DataAccessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.SQLException;

public class DBhelper {

    //查询数据
    public static <T> T query(String sql, ResultSetHandler<T> handler, Object... objects) {
        QueryRunner qr = new QueryRunner(ConnectionManager.getDataSource());
        try {
            return qr.query(sql, handler, objects);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e,"DBhelper 异常");
         }

    }

    //增删改查
    public static void updater(String sql, Object... objects) {
        QueryRunner qr = new QueryRunner(ConnectionManager.getDataSource());
        try {
            qr.update( sql, objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
