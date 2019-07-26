package com.fpt.filter;

import com.fpt.controller.AtubeApi;
import com.fpt.entity.Article;
import com.fpt.entity.Atube;
import com.fpt.entity.CrawlerSource;
import com.fpt.taskqueue.AddQueue;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.*;
import java.io.IOException;

public class ObjectifyRegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ObjectifyService.register(Atube.class);
        ObjectifyService.register(Article.class);
        ObjectifyService.register(CrawlerSource.class);
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
