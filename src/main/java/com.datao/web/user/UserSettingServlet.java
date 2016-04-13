package com.datao.web.user;

import com.datao.service.user.UserService;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/9.
 * user设置
 */
@WebServlet("/user/usersetting.do")
public class UserSettingServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("token",new UserService().getToken());
        forward(req, resp, "user/usersetting.jsp");
    }
}
