package com.datao.web.user.validate;

import com.datao.DAO.UserDao;
import com.datao.entity.User;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/7.
 * 验证用户名是否已被使用
 */
@WebServlet("/valiusername.do")
public class ValiUserName extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        String result = null;
        if (username != null) {
            username = new String(username.getBytes("ISO8859-1"), "UTF-8");
            System.out.println("初步验证username:" + username);

            User user = new UserDao().findByName(username);
            if (user == null) {
                result = "true";
            } else {
                result = "false";
            }
        }

        sendText(resp, result);

    }
}
