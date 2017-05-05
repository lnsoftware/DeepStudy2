package com.zhiyin.rpc.shi.demo.filter;

import com.zhiyin.rpc.httpinvoker.demo.service.CatContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hg on 2016/7/21.
 */
@WebFilter
public class LogFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            if (response instanceof HttpServletResponse) {

                CatContextManager.setContext(httpReq.getHeader(CatContext.HttpRootId),httpReq.getHeader(CatContext.HttpParentId),httpReq.getHeader(CatContext.HttpChildId));
            }
        }

        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
