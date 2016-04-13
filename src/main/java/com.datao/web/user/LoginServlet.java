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
        User user = getSessionUser(req);
        if (user != null) {
            resp.sendRedirect("/index.do");
        } else {
            forward(req, resp, "user/login.jsp");
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

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            User user = userService.login(username, password, getIp(request));
            if (user != null) {
                //登录成功,建立User对象,删除errorTimes对象
                session.setAttribute("user", user);
                session.removeAttribute("errorTimes");

                result.put("state", "success");
            } else {
                //判断什么时候显示验证码
                int errorNum = userService.errNum(session);
                result.put("errorNum", errorNum);

                System.out.println("错误次数为:" + errorNum);

                result.put("state", "error");
                result.put("errorMessage", "1"); //帐号或密码错误
            }
        } else {
            session.setAttribute("errorTimes", "3");

            //判断什么时候显示验证码
            int errorNum = userService.errNum(session);
            result.put("errorNum", errorNum);

            System.out.println("错误次数为:" + errorNum);

            result.put("state", "error");
            result.put("errorMessage", "2"); //参数错误
        }

        sendJson(response, result);

    }
}
