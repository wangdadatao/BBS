package com.datao.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/7.
 */
public class BaseServlet extends HttpServlet {
    public void forward(HttpServletRequest request, HttpServletResponse response,String url) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/"+url).forward(request,response);
    }
}
