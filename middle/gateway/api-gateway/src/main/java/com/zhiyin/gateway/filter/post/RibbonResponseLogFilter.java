package com.zhiyin.gateway.filter.post;

import com.netflix.client.IResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * 如果没有使用ribbon或者ribbon调用失败,不会有这个值。
 * org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommand
 * http://stackoverflow.com/questions/36981525/zuul-proxy-with-ribbon-listofservers-and-logging-requirement
 */
@Slf4j
@Component
public class RibbonResponseLogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 9;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        try {
//            ribbonResponse在AbstractRibbonCommand.run()中设置
            RequestContext ctx = RequestContext.getCurrentContext();
//             contains not work
//            if( !ctx.contains("ribbonResponse")){
//                return null;
//            }

            Object ribbonResponse = ctx.get("ribbonResponse");
            if ( ribbonResponse == null) {
                return null;
            }
            URI ribbonResponseURI = ((IResponse) ribbonResponse).getRequestedURI();

            Object serviceId = ctx.get("serviceId");

            log.info("ribbon call {}, uri:{}",serviceId, ribbonResponseURI.toString());
        } catch (Exception e) {
            log.error("ribbon respoonse error.", e);
        }
        return null;
    }
}