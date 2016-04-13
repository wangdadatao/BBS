package com.datao.web;

import com.datao.entity.Node;
import com.datao.entity.Topic;
import com.datao.service.topic.TopicService;
import com.datao.util.Page;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/7.
 */
@WebServlet("/index.do")
public class IndexServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String pageNo = req.getParameter("p");

        TopicService topicService = new TopicService();
        List<Node> nodes = topicService.findNodes();
        Page page = topicService.showIndexTopic(code, pageNo);

        req.setAttribute("nodes", nodes);
        req.setAttribute("page", page);
        forward(req, resp, "index.jsp");
    }
}
