package com.heyy.study.springbootstudycentral.filter;


import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Classname RequestBodyCacheFilter
 * @Description TODO
 * @Date 2019/5/13 19:52
 * @Created by Breeze
 */
public class RequestBodyCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new ContentCachingRequestWrapper((HttpServletRequest)servletRequest),servletResponse );
    }

    @Override
    public void destroy() {

    }
}
