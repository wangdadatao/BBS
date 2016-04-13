package com.datao.service.user;

import com.datao.DAO.ForgetPasswordDao;
import com.datao.DAO.UserDao;
import com.datao.entity.ForgetPassword;
import com.datao.entity.User;
import com.datao.exception.DataAccessException;
import com.datao.util.ConfigProp;
import com.datao.util.EmailUtil;
import com.qiniu.util.Auth;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

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

    //根据用户姓名查找用户
    public User findByUserName(String username) {
        return new UserDao().findByName(username);
    }

    /**
     * 根据用户输入的用户名,查找到相应的用户,生成token对象,发送邮件
     *
     * @param user
     */
    public void senEmail(User user) {
        String uuid = UUID.randomUUID().toString();
        String email = user.getEmail();
        String title = "VR论坛-找回密码邮件";
        String url = "http://localhost/forgetcallback.do?token=" + uuid;
        String msg = user.getUsername() + ":<br>\n" +
                "点击该<a href='" + url + "'>链接</a>进行设置新密码，该链接30分钟内有效。<br>\n" +
                url + "<br>如果不是本人操作请无视!";

        System.out.println("发送的主题:" + title);
        System.out.println("发送的内容:" + msg);
        System.out.println("接收的邮箱为:" + email);

        ForgetPassword forgetPassword = new ForgetPassword();

        forgetPassword.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        forgetPassword.setToken(uuid);
        forgetPassword.setUid(user.getId());
        new ForgetPasswordDao().save(forgetPassword);

        EmailUtil.sendHtmlEmail(title, msg, email);

    }

    /**
     * @param token
     * @return 返回编码
     */
    public Integer finToken(String token) {
        ForgetPassword forgetPassword = new ForgetPasswordDao().findByToken(token);

        if (forgetPassword != null) {
            String creadTime = forgetPassword.getCreatetime();

            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            DateTime dateTime = formatter.parseDateTime(creadTime);

            dateTime = dateTime.plusMinutes(30);

            if (dateTime.isAfterNow()) {
                return forgetPassword.getUid();
            } else {
                throw new DataAccessException("链接已经失效");
            }
        } else {
            throw new DataAccessException("该链接无效");
        }

    }

    /**
     * 根据token查找ForgetPassword对象
     *
     * @param token
     * @return
     */
    public ForgetPassword findByToken(String token) {
        return new ForgetPasswordDao().findByToken(token);
    }


    /**
     * 设置新密码
     *
     * @param password
     * @param uid
     * @return
     */

    public void resetPassword(String password, Integer uid) {
        password = DigestUtils.md5Hex(password + ConfigProp.get("user.password.salt"));
        User user = new UserDao().findById(uid);
        user.setPassword(password);
        new UserDao().upUser(user);
        new ForgetPasswordDao().removeUid(uid);
    }

    //验证错误次数
    public int errNum(HttpSession session) {
        Integer errorNum = Integer.valueOf((String) session.getAttribute("errorTimes"));
        errorNum++;
        session.setAttribute("errorTimes", errorNum + "");
        return errorNum;
    }

    //保存用户的新邮箱
    public Boolean save(User user, String email) {
        if (user != null && email != null) {
            user.setEmail(email);
            new UserDao().upUser(user);
            return true;
        } else {
            return false;
        }
    }

    //用户设置新密码
    public String settingPassword(HttpServletRequest request, String nowPassword, String newPassword) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            nowPassword = DigestUtils.md5Hex(nowPassword + ConfigProp.get("user.password.salt"));
            if (user.getPassword().equals(nowPassword)) {
                newPassword = DigestUtils.md5Hex(newPassword + ConfigProp.get("user.password.salt"));
                user.setPassword(newPassword);
                new UserDao().upUser(user);
                return "success";
            }
        }
        return "error";
    }

    //产生七牛上传的token
    public String getToken() {
        //1准备好AK和SK
        final String AK = ConfigProp.get("qiniu.AK");
        final String SK = ConfigProp.get("qiniu.SK");

        //2指定上传的空间
        final String bucketName = "pictures";

        //3创建auth对象
        Auth auth = Auth.create(AK, SK);
        return auth.uploadToken(bucketName);

    }

    //保存用户头像
    public void savaHeeadImg(User user, String newHeadimg) {
        if(user != null){
            user.setHeadimg(newHeadimg);
            userDao.upUser(user);
        }else {
            throw new DataAccessException("头像设置失败"); //找不到user对象,头像设置失败
        }

    }
}
