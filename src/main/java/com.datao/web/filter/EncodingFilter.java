package com.datao.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/7.
 * 设置字符编码过滤器
 */
public class EncodingFilter extends AbstractFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
    /*

    <!--字符集过滤器-->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>com.datao.web.filter.EncodingFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
   */
}
