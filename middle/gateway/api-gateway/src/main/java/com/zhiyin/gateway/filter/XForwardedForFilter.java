package com.zhiyin.gateway.filter;

//Turns out the X-Forwarded-For header was taken from the original request feeding into Zuul to populate HttpServletRequest#getRemoteAddr(). This would then have to be passed on to the proxied backend services through RequestContext#getZuulRequestHeaders().put("X-Forwarded-For", remoteAddr). The following ZuulFilter accomplishes this, even if it isn't appending it's own value to the X-Forwarded-For filter just yet.

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class XForwardedForFilter extends ZuulFilter {
    private static final String X_FORWARDED_FOR = "X-Forwarded-For";

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        // Rely on HttpServletRequest to retrieve the correct remote address from upstream X-Forwarded-For header
        HttpServletRequest request = ctx.getRequest();
        String remoteAddr = request.getRemoteAddr() +
                request.getRemotePort();
        // Pass remote address downstream by setting X-Forwarded for header again on Zuul request
        log.info("Settings X-Forwarded-For to: {}", remoteAddr);
//    ctx.getZuulRequestHeaders().put(X_FORWARDED_FOR, remoteAddr);

        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }
}