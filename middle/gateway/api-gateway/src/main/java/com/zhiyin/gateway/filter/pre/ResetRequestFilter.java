package com.zhiyin.gateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 可以对RequestBody操作
 */
@Slf4j
@Component
public class ResetRequestFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        return (!StringUtils.equals(HttpRequest.Verb.POST.toString(), request.getMethod()));
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        setAutoResetRequestEntity(ctx);

        return null;
    }

    protected void setAutoResetRequestEntity(RequestContext ctx) {
        HttpServletRequest request = ctx.getRequest();

        if (request.getContentLength() > 0) {
            try {
                String originStr = IOUtils.toString(request.getInputStream());
                log.info("request body:" + originStr);
                ctx.set("requestEntity", getInputStream(originStr));
            } catch (IOException e) {
                log.error("Could not get request input stream, skipping AutoResetInputStreamFilter", e);
            }
        }
    }

    public ServletInputStream getInputStream(String body) throws IOException {
        final ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(body.getBytes(Charset.forName("utf-8")));
        return new ServletWrappperInputstream(byteArrayInputStream);
    }

}

class ServletWrappperInputstream extends ServletInputStream {

    private final ByteArrayInputStream byteArrayInputStream;

    public ServletWrappperInputstream(final ByteArrayInputStream byteArrayInputStream) {
        this.byteArrayInputStream = byteArrayInputStream;
    }

    @Override
    public int read() throws IOException {
        return byteArrayInputStream.read();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {
    }

}