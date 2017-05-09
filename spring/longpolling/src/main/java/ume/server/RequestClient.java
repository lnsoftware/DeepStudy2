package ume.server;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by hg on 2017/5/18.
 */
public class RequestClient {

    public String host;
    public DeferredResult<String> deferredResult ;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public DeferredResult<String> getDeferredResult() {
        return deferredResult;
    }

    public void setDeferredResult(DeferredResult<String> deferredResult) {
        this.deferredResult = deferredResult;
    }
}
