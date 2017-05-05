package com.zhiyin.gateway.filter.post;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import com.zhiyin.gateway.filter.http.ServletWrapperOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Locale;

@Slf4j
@Component
public class ResetResponseFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
            String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));

            Object serviceId = ctx.get("serviceId");
            if( "local".equals(serviceId) ){
                responseData += "return was modify respoonse";
            }
            log.info("response body:{}",responseData);
            ctx.setResponseDataStream( new ByteArrayInputStream(responseData.getBytes(Charset.forName("UTF-8")) ));
        } catch (IOException e) {
            log.warn("Error reading body",e);
        }

        return null;
    }



}
