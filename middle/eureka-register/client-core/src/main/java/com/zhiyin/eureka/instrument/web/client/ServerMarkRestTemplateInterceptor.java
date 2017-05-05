package com.zhiyin.eureka.instrument.web.client;

import com.zhiyin.eureka.instrument.web.client.support.RibbonFilterContextHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Map;

public class ServerMarkRestTemplateInterceptor
		implements ClientHttpRequestInterceptor {

    private Map<String, ServerInvokerProperty.ServerInvokerConfig>  serverMark;

	public ServerMarkRestTemplateInterceptor(Map<String, ServerInvokerProperty.ServerInvokerConfig> serverMark) {
        this.serverMark = serverMark;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
        if(!serverMark.containsKey(request.getURI().getHost())){
            return execution.execute(request,body);
        }
        // 启动时设置服务标签
        ServerInvokerProperty.ServerInvokerConfig config = serverMark.get(request.getURI().getHost());
        String mark = config.getServerMark();
        RibbonFilterContextHolder.getCurrentContext().add(ServerInvokerConst.SERVER_MARK_NAME,mark);
        RibbonFilterContextHolder.setMarkMap(config.getCandidateServerMarks());
        try {
            ClientHttpResponse ret = execution.execute(request, body);
            return ret;
        }catch (Exception e){
            throw e;
        }finally {
            RibbonFilterContextHolder.clearCurrentContext();
        }
	}

}
