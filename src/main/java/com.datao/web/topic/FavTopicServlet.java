package com.datao.web.topic;

import com.datao.entity.Topic;
import com.datao.exception.DataAccessException;
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
 * Created by 海涛 on 2016/4/13.
 * 收藏主题
 */
@WebServlet("/topic/favtopic.do")
public class FavTopicServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        String favStatus = req.getParameter("favstatus");

        Map<String, Object> result = Maps.newHashMap();
        TopicService topicService = new TopicService();

        if (StringUtils.isNumeric(topicid) && StringUtils.isNotEmpty(favStatus)) {
            Topic topic = topicService.findById(topicid);
            if (topic != null) {
                try {
                    topicService.addFav(getSessionUser(req), topic,favStatus);
                    result.put("status", "success");
                } catch (DataAccessException e) {
                    result.put("status", "error");
                    result.put("errorMessage", e.getMessage());
                }
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
