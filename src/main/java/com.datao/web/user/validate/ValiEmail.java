package com.datao.web.user.validate;

import com.datao.DAO.UserDao;
import com.datao.entity.User;
import com.datao.service.user.UserService;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/7.
 * 验证邮箱是否已被使用
 */
@WebServlet("/valiemail.do")
public class ValiEmail extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String result = null;
        if (email != null) {
            email = new String(email.getBytes("ISO8859-1"), "UTF-8");
            System.out.println("初步验证email:" + email);

            User user = new UserDao().findByEmail(email);
            if (user == null) {
                result = "true";
            } else {
                result = settingEmail(req, email) ? "true" : "false";
            }
        }

        sendText(resp, result);
    }

}
