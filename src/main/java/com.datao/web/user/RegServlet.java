package com.datao.web.user;

import com.datao.entity.User;
import com.datao.exception.DataAccessException;
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
 * 注册功能
 */
@WebServlet("/reg.do")
public class RegServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getSessionUser(req);
        if (user != null) {
            resp.sendRedirect("/index.do");
        } else {
            forward(req, resp, "user/reg.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getSessionUser(req);
        if(user != null){
            resp.sendError(400);
        }

        HttpSession session = req.getSession();
        session.removeAttribute("errorTimes");
        //先判断验证码是否正确
        String oldCaptcha = (String) session.getAttribute("captcha");
        String captcha = req.getParameter("captcha");

        Map<String, Object> map = Maps.newHashMap();
        UserService userService = new UserService();

        if (StringUtils.isNotEmpty(oldCaptcha) && oldCaptcha.equals(captcha)) {
            //验证码正确 继续接受值进行判断
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");

            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(email)) {
                try {
                    //进一步判断用户名和邮箱是否被占用
                    userService.addUser(username, password, email);
                    map.put("state", "success");
                } catch (DataAccessException e) {
                    map.put("state", "error");
                    map.put("errorMessage", e.getMessage());
                }
            } else {
                map.put("state", "error");
                map.put("errorMessage", "输入参数不合法!");
            }
        } else {
            map.put("state", "error");
            map.put("errorMessage", "请输入验证码!");
        }

        sendJson(resp, map);
    }
}
