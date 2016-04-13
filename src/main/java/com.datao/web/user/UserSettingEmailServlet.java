package com.datao.web.user;

import com.datao.entity.User;
import com.datao.service.user.UserService;
import com.datao.web.BaseServlet;
import com.google.common.collect.Maps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 海涛 on 2016/4/9.
 * 设置邮箱
 */
@WebServlet("/user/settingemail.do")
public class UserSettingEmailServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = (User) req.getSession().getAttribute("user");
        Map<String, Object> result = Maps.newHashMap();

        result.put("status", (new UserService().save(user, email)) ? "success" : "error");
        sendJson(resp, result);

    }
}
