package com.zhiyin.rpc.shi.demo.filter;

import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.spi.MessageTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hg on 2016/7/21.
 */
@WebFilter
public class CatLogFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Transaction transaction = Cat.newTransaction("CatLogFilter", "doFilter");
        Cat.logEvent("filter", "catlog", Event.SUCCESS, "excute");

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            if (response instanceof HttpServletResponse) {

                logRemoteServer(httpReq);
//                CatContextManager.setContext( httpReq.getHeader(UmeCatContext.HttpCatIds));
//                CatContextManager.setContext(httpReq.getHeader(CatContext.HttpRootId),httpReq.getHeader(CatContext.HttpParentId),httpReq.getHeader(CatContext.HttpChildId));
            }
        }

        chain.doFilter(request,response);

        transaction.setStatus(Transaction.SUCCESS);
        transaction.complete();

    }


    private void logRemoteServer(HttpServletRequest httpReq) {
        MessageTree tree = Cat.getManager().getThreadLocalMessageTree();

        Map<String,String> map = JSON.parseObject(  httpReq.getHeader(UmeCatContext.HttpCatIds) , Map.class);

        if(map == null){
            return;
        }

        String messageId =   map.get(Cat.Context.CHILD);
        String rootId =   map.get(Cat.Context.ROOT);
        String parentId = map.get(Cat.Context.PARENT);

        String info = "remote cat info:" +rootId+" " + parentId +" " +messageId +" " + JSON.toJSONString(map);
        log.info( info );
        System.out.println( info );

        if (messageId != null) {
            tree.setMessageId(messageId);
        }
        if (parentId != null) {
            tree.setParentMessageId(parentId);
        }
        if (rootId != null) {
            tree.setRootMessageId(rootId);
        }


    }

        @Override
    public void destroy() {

    }
}
