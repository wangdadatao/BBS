package com.datao.web.user;

import com.datao.entity.User;
import com.datao.service.user.UserService;
import com.datao.web.BaseServlet;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 海涛 on 2016/4/7.
 * 登录功能
 */
@WebServlet("/login.do")
public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            forward(req, resp, "user/login.jsp?code" + code);
        } else {
            forward(req, resp, "user/login.jsp?");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, Object> result = Maps.newHashMap();
        UserService userService = new UserService();
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            User user = userService.login(username, password, getIp(request));
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                result.put("state", "success");
            } else {
                result.put("state", "error");
                result.put("message", "用户名或密码错误");
            }
        } else {
            result.put("state", "error");
            result.put("message", "参数错误!");
        }

        sendJson(response, result);


    }
}
