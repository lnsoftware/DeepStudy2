package com.zhiyin.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter2 extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AccessFilter2.class);
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

        String requestUrl = this.urlPathHelper.getPathWithinApplication(ctx.getRequest());
        String contextPath = urlPathHelper.getContextPath(request);

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        log.info(contextPath);
        if (requestUrl.startsWith("appapi")) {
            log.info("start with");

            ctx.set("serivceId", "");
            ctx.setRouteHost(null);

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
