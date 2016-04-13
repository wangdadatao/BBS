package com.datao.DAO;

import com.datao.entity.Node;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by 海涛 on 2016/4/11.
 * nodeDAO
 */
public class NodeDao {

    //查询所有的节点
    public List<Node> findAll() {
        String sql = "select * from node";
        return DBhelper.query(sql, new BeanListHandler<>(Node.class));
    }

}
