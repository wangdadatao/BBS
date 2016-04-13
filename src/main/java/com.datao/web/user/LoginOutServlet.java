package com.datao.web.user;

import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/8.
 * 安全退出
 */
@WebServlet("/loginout.do")
public class LoginOutServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        String code = null;
        String getCode = req.getParameter("code");

        //判断是安全退出还是修改密码后重新登录
        if (getCode != null) {
            code = getCode;
        }else{
            code = "8001";
        }

        resp.sendRedirect("/login.do?code=" + code);
    }
}
