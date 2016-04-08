package com.datao.DAO;

import com.datao.entity.User;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by 海涛 on 2016/4/7.
 * UserDao
 */
public class UserDao {

    //添加User
    public void addUser(User user) {
        String sql = "insert into user(username,password,email,headimg,regtime,state) values(?,?,?,?,?,?)";
        DBhelper.updater(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getHeadimg(), user.getRegtime(), user.getState());
    }

    //根据username查询User
    public User findByName(String username) {
        String sql = "select * from user where username=?";
        return DBhelper.query(sql, new BeanHandler<User>(User.class), username);
    }

    //根据emali查询User
    public User findByEmail(String email){
        String sql = "select * from user where email=?";
        return DBhelper.query(sql,new BeanHandler<User>(User.class),email);
    }

    //修改用户资料
    public void upUser(User user) {
        String sql = "update user set password=? ,email=?, headimg=?, lastlogip=?, logtime=?, state=? where id=?";
        DBhelper.updater(sql,user.getPassword(),user.getEmail(),user.getHeadimg(),user.getLastlogip(),user.getLogtime(),user.getState(),user.getId());
    }
}
