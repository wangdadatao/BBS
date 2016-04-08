package com.datao.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/7.
 * 建立抽象的Filter 不实现其中的doFilter方法
 */
public abstract class AbstractFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
