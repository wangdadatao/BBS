package com.datao.web.topic;

import com.datao.entity.Topic;
import com.datao.entity.User;
import com.datao.service.topic.TopicService;
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
 * Created by 海涛 on 2016/4/12.
 * 增加评论
 */
@WebServlet("/topic/addcomment.do")
public class AddCommentServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getSessionUser(req);
        String commentText = req.getParameter("commenttext");
        String topicId = req.getParameter("topicid");

        Map<String, Object> result = Maps.newHashMap();
        TopicService topicService = new TopicService();

        if (StringUtils.isNotEmpty(commentText) && StringUtils.isNumeric(topicId) && user != null) {
            Topic topic = topicService.findById(topicId);
            if (topic != null) {
                topicService.saveComment(user, commentText, topic);

                result.put("status", "success");
            } else {
                result.put("status", "error");
                result.put("errorMessage", "参数错误");
            }

        } else {
            result.put("status", "error");
            result.put("errorMessage", "参数错误!");
        }

        sendJson(resp, result);
    }
}
