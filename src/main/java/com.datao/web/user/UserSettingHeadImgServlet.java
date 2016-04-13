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
 * Created by 海涛 on 2016/4/13.
 * 设置头像
 */
@WebServlet("/user/settingheadimg.do")
public class UserSettingHeadImgServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newHeadimg = req.getParameter("newheadimg");

        Map<String, Object> result = Maps.newHashMap();

        if (StringUtils.isNotEmpty(newHeadimg)) {
            UserService userService = new UserService();
            try {
                userService.savaHeeadImg(getSessionUser(req), newHeadimg);
                result.put("status", "success");
            } catch (DataAccessException e) {
                result.put("status", "error");
                result.put("errorMessage", e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("errorMessaga", "头像设置失败!");
        }
        sendJson(resp, result);
    }
}
