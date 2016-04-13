package com.datao.DAO;

import com.datao.entity.ForgetPassword;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by 海涛 on 2016/4/8.
 */
public class ForgetPasswordDao {
    //添加找回密码记录
    public void save(ForgetPassword forgetPassword) {
        String sql = "insert into forgetpwd(uid,token,createtime) values(?,?,?)";
        DBhelper.updater(sql, forgetPassword.getUid(), forgetPassword.getToken(), forgetPassword.getCreatetime());
    }

    //根据token查找对象
    public ForgetPassword findByToken(String token) {
        String sql = "select * from forgetpwd where token = ?";
        return DBhelper.query(sql, new BeanHandler<>(ForgetPassword.class), token);
    }

    //移除用户的找回密码时产生的token
    public void removeUid(Integer uid) {
        String sql = "delete from forgetpwd where uid =? ";
        DBhelper.updater(sql, uid);
    }
}
