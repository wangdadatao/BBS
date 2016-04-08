package com.datao.web.user;

import com.datao.exception.DataAccessException;
import com.datao.service.user.UserService;
import com.datao.web.BaseServlet;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        forward(req, resp, "user/reg.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到注册时的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");


        Map<String, Object> map = Maps.newHashMap();

        UserService userService = new UserService();

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(email)) {
            try {
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

        sendJson(resp, map);


    }
}
