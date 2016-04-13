package com.datao.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/13.
 * url过滤器
 */
public class UrlFilter extends AbstractFilter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();

        if(path.contains("/user/")){
            if(request.getSession().getAttribute("user") != null){
                filterChain.doFilter(request,response);
            }else{
                response.sendRedirect("/login.do?code=4001");
            }
        }else{
            filterChain.doFilter(request,response);
        }


    }
}
