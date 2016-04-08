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
 * Created by 海涛 on 2016/4/8.
 * 忘记密码
 */
@WebServlet("/forgetpwd.do")
public class ForgetPwdServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp, "user/forgetpwd.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String newCaptcha = request.getParameter("captcha");

        Map<String, Object> result = Maps.newHashMap();

        String oldCaptcha = (String) request.getSession().getAttribute("captcha");

        if (oldCaptcha.equals(newCaptcha)) {
            if (username != null) {
                User user = new UserService().findByUserName(username);
                if (user != null) {
                    try {
                        new UserService().senEmail(user);
                        result.put("status", "success");

                    } catch (Exception e) {
                        result.put("status", "error");
                        result.put("errorMessage", "0");
                    }
                } else {
                    result.put("status", "error");
                    result.put("errorMessage", "1");    //输入用户名不存在
                }
            } else {
                result.put("status", "error");
                result.put("errorMessage", "3");    //参数错误
            }
        } else {
            result.put("status", "error");
            result.put("errorMessage", "2"); //验证码错误
        }

        sendJson(response, result);


    }
}
