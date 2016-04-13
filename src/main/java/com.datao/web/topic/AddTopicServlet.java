package com.datao.web.topic;

import com.datao.entity.Node;
import com.datao.entity.Topic;
import com.datao.entity.User;
import com.datao.service.topic.TopicService;
import com.datao.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/11.
 * 新建帖子
 */
@WebServlet("/topic/addtopic.do")
public class AddTopicServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Node> nodes = new TopicService().findNodes();

        req.setAttribute("nodes", nodes);
        forward(req, resp, "topic/addtopic.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String nodeid = request.getParameter("node");

        HttpSession session = request.getSession();

        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(nodeid)) {
            if (session.getAttribute("user") != null) {
                Integer topicId = new TopicService().addTopic(title, text, nodeid, (User) session.getAttribute("user"));

                response.sendRedirect("/topic/showtopic.do?code=" + topicId);
            } else {
                response.sendError(400, "参数错误");
            }
        } else {
            response.sendError(400, "参数错误");
        }
    }
}
