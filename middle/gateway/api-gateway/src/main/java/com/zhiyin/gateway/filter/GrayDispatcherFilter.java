package com.zhiyin.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 根据条件调用灰度服务
 * <p/>
 * https://github.com/spring-cloud/spring-cloud-netflix/issues/1169
 */
@Slf4j
@Component
public class GrayDispatcherFilter extends ZuulFilter {

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String servletPath = request.getServletPath();

        String requestUrl = request.getRequestURI();

        log.info("RequestURI:{}", requestUrl);

        log.info("ServletPath:{}", servletPath);

        String testApp = "/user-api";
        if (servletPath.startsWith(testApp)) {
            if ("admin".equals(request.getParameter("name"))) {
                String grayApp = requestUrl.replace(testApp, testApp + "-gray");
                RequestContext.getCurrentContext().getRequest().setAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE, grayApp);
            }
        }
//        ctx.getRequest().get
//        if(contextPath.startsWith("service-b")){
//            System.out.println("");
//            log.info("start with service-b.");
//
//            ctx.set("serivceId","service-b");
//
//            ctx.setRouteHost(null);
//
//        }


//        Object accessToken = request.getParameter("accessToken");
//        if (accessToken == null) {
//            log.warn("access token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        log.info("access token ok");
        return null;
    }

}
