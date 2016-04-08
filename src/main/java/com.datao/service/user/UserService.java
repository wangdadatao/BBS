package com.datao.service.user;

import com.datao.DAO.UserDao;
import com.datao.entity.User;
import com.datao.exception.DataAccessException;
import com.datao.util.ConfigProp;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

/**
 * Created by 海涛 on 2016/4/7.
 * User的一些相关服务
 */
public class UserService {
    private UserDao userDao = new UserDao();

    //添加用户
    public void addUser(String username, String password, String email) {

        //再次判断帐号和邮箱是否已被占用
        if (userDao.findByName(username) != null) {
            throw new DataAccessException("用户名已被占用");
        }
        if (userDao.findByEmail(email) != null) {
            throw new DataAccessException("emali已被注册!");
        }

        User user = new User();
        user.setUsername(username);
        //给密码加密
        user.setPassword((DigestUtils.md5Hex(password + ConfigProp.get("user.password.salt"))));
        System.out.println("加密后密码为:" + user.getPassword());

        user.setEmail(email);
        user.setHeadimg("ac2cbdc0-dc01-48e4-8232-05673f555622.jpg");//设置默认头像
        user.setState(User.STATE_NORMAL);   //设置默认状态
        user.setRegtime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

        userDao.addUser(user);
    }

    //登录查找用户
    public User login(String username, String password, String ip) {
        User user = new UserDao().findByName(username);
        if (user != null) {
            String bigPassWord = DigestUtils.md5Hex(password + ConfigProp.get("user.password.salt"));

            if (user.getPassword().equals(bigPassWord)) {
                user.setLastlogip(ip);
                user.setLogtime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

                new UserDao().upUser(user);
                return user;
            }
        }
        return null;

    }


}
