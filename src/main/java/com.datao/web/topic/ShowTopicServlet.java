package com.datao.web.topic;

import com.datao.entity.Favorites;
import com.datao.entity.Topic;
import com.datao.entity.User;
import com.datao.service.topic.TopicService;
import com.datao.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/11.
 * 展示帖子内容
 */
@WebServlet("/topic/showtopic.do")
public class ShowTopicServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        TopicService topicService = new TopicService();

        if (StringUtils.isNumeric(code)) {
            try {
                Topic topic = topicService.findById(code);
                topicService.addView(topic);

                //判断该用户是否收藏多该主题
                User user = getSessionUser(req);
                if (user != null) {
                    Favorites favorites = topicService.findFav(user, topic);
                    if (favorites != null) {
                        req.setAttribute("favstatus", "true");
                    } else {
                        req.setAttribute("favstatus", "false");
                    }
                }

                req.setAttribute("topic", topic);
                forward(req, resp, "topic/showtopic.jsp");
            } catch (Exception e) {
                resp.sendError(404, e.getMessage());
            }
        } else {
            resp.sendError(500, "参数错误!");
        }
    }
}
