package com.datao.web.user;

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
 * Created by 海涛 on 2016/4/10.
 * 重新设置密码
 */
@WebServlet("/user/settingpassword.do")
public class SettingPasswordServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAjaxReq(request)) {
            Map<String, Object> result = Maps.newHashMap();
            UserService userService = new UserService();

            String nowPassword = request.getParameter("nowpassword");
            String newPassword = request.getParameter("newpassword");

            if (StringUtils.isNotEmpty(nowPassword) && StringUtils.isNotEmpty(newPassword)) {
                result.put("status", userService.settingPassword(request, nowPassword, newPassword));
            }
            sendJson(response, result);
        }
    }
}
