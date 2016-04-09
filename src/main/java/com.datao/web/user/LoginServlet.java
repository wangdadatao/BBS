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
        String getCode = req.getParameter("code");
        String errorNum = (String) req.getSession().getAttribute("errorTimes");

        String code = null;

        if (getCode == null) {
            if (errorNum != null) {
                code = "6001";
            }
        }else{
            code = getCode;
        }

        System.out.println("code为:" + code);
        System.out.println("errorNum为:" + errorNum);


        if (code != null) {
            forward(req, resp, "user/login.jsp?code=" + code);
        } else {
            forward(req, resp, "user/login.jsp?");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //登录获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = null;


        Map<String, Object> result = Maps.newHashMap();
        UserService userService = new UserService();
        HttpSession session = request.getSession();

        //判断应不应该输入验证码
        String errorNums = (String) session.getAttribute("errorTimes");
        if (errorNums == null) {
            session.setAttribute("errorTimes", "0");
        } else {
            Integer errorNum = Integer.valueOf(errorNums);
            if (errorNum >= 3) {
                captcha = request.getParameter("captcha");
                String oldCaptcah = (String) session.getAttribute("captcha");
                if (StringUtils.isNotEmpty(oldCaptcah) && oldCaptcah.equals(captcha)) {
                    //应该输入验证码并且验证码正确,则不进行操作
                } else {
                    result.put("state", "error");
                    result.put("errorMessage", "3"); //验证码错误!
                    sendJson(response, result);
                    return;
                }
            }
        }

        System.out.println("");

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            User user = userService.login(username, password, getIp(request));
            if (user != null) {

                session.setAttribute("user", user);

                result.put("state", "success");
            } else {
                userService.errNum(session);
                result.put("state", "error");
                result.put("errorMessage", "1"); //帐号或密码错误
            }
        } else {
            session.setAttribute("errorTimes", "3");
            result.put("state", "error");
            result.put("errorMessage", "2"); //参数错误
        }

        sendJson(response, result);

    }
}
