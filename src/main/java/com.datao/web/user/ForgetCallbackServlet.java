package com.datao.web.user;

import com.datao.entity.ForgetPassword;
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
 * Created by 海涛 on 2016/4/8.
 * 用户点击邮箱中的链接修改密码
 */
@WebServlet("/forgetcallback.do")
public class ForgetCallbackServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        if (StringUtils.isNotEmpty(token)) {
            try {
                new UserService().finToken(token);
                ForgetPassword forgetPassword = new UserService().findByToken(token);

                HttpSession session = request.getSession();
                session.setAttribute("forgetPassword", forgetPassword);
                forward(request, response, "user/forgetcallbackpassword.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());

                forward(request, response, "user/forgeterrorpassword.jsp");

            }


        } else {
            response.sendError(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");

        ForgetPassword frogetpassword = (ForgetPassword) req.getSession().getAttribute("forgetPassword");

        Map<String, Object> result = Maps.newHashMap();

        if (StringUtils.isNotEmpty(password)) {
            if (frogetpassword != null) {
                new UserService().resetPassword(password, frogetpassword.getUid());


                result.put("status", "success");


            } else {
                result.put("status", "error");
                result.put("errorMessage", "设置密码失败!");
            }
        } else {
            result.put("status", "error");
            result.put("errorMessage", "参数错误!");
        }
        sendJson(resp, result);
    }
}
