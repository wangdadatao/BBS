package com.datao.web.topic;

import com.datao.entity.Comment;
import com.datao.entity.Topic;
import com.datao.service.topic.TopicService;
import com.datao.web.BaseServlet;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by 海涛 on 2016/4/12.
 * 回复展示
 */
@WebServlet("/topic/searchcomments.do")
public class CommentsServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicId = req.getParameter("topicid");
        System.out.println("Topic:" + topicId);
        TopicService topicService = new TopicService();

        Map<String, Object> result = Maps.newHashMap();

        if (StringUtils.isNumeric(topicId)) {
            Topic topic = topicService.findById(topicId);
            if (topic == null) {
                result.put("status", "error");
                result.put("errorMessage", "参数错误!````");
            } else {
                List<Comment> comments = topicService.findComment(topicId);
                result.put("status", "success");
                result.put("data", comments);
            }
        } else {
            result.put("status", "error");
            result.put("errorMessage", "参数错误!!!!!");
        }

        sendJson(resp, result);

    }
}
