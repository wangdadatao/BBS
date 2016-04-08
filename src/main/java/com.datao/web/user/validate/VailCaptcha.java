package com.datao.web.user.validate;

import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/8.
 * 验证输入的验证码是否正确
 */
@WebServlet("/valicaptcha.do")
public class VailCaptcha extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String newCaptcha = req.getParameter("captcha");

        String oldCaptcha = (String) req.getSession().getAttribute("captcha");

        String result = null;

        System.out.println("输入的验证码为:" + newCaptcha + " 产生的验证码为:" + oldCaptcha);
        if (oldCaptcha.equals(newCaptcha)) {
            result = "true";
        } else {
            result = "false";
        }
        sendText(resp, result);
    }
}
