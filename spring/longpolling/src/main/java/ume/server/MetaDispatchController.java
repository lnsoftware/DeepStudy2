package ume.server;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import ume.ApolloThreadFactory;
import ume.TableChangeMeta;

@Slf4j
@RestController
public class MetaDispatchController {

    public MetaDispatchController(){
        Executors.newFixedThreadPool(1, ApolloThreadFactory
            .create("ReleaseMessageScanner", true))
        .submit(new Runnable() {
            @Override public void run() {
                taskRun();
            }
        });
    }

    @SuppressWarnings("rawtypes")
//    BlockingQueue<DeferredResult<String>> waittingDefers = Queues.newLinkedBlockingQueue(1000);

    BlockingQueue<RequestClient> requestClients = Queues.newLinkedBlockingQueue(1000);


    @RequestMapping(value = "/defer", method = RequestMethod.GET)
    public DeferredResult<String> getQuote(HttpServletRequest request) {
        log.info("rec req.");
        final DeferredResult<String> deferredResult = new DeferredResult<String>((long) (11 * 60 * 1000));


        RequestClient requestClient = new RequestClient();
        requestClient.setDeferredResult(deferredResult);
        requestClient.setHost( request.getRemoteAddr() + request.getRemotePort() );
//        System.out.println( JSON.toJSONString(requestClient) );
        requestClients.add( requestClient);
//        deferredResult.setResultHandler();

//        waittingDefers.add(deferredResult);

        deferredResult.onCompletion(new Runnable() {
            @Override public void run() {
                log.info("deferredResult onCompletion.");
                requestClients.remove( requestClient );
            }
        });

        deferredResult.onTimeout(new Runnable() {
            @Override public void run() {
                log.info("deferredResult onTimeout.");
                requestClients.remove( requestClient );
            }
        });

        return deferredResult;
    }

    public void getQuoteAndUpdateClients() {

        TableChangeMeta get = null;
        try {
            get = MessageQueue.queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(get ==null){
            return;
        }

        List<TableChangeMeta> list = Lists.newArrayList();
        list.add(get);

        Gson gson = new Gson();


        ArrayList< RequestClient > al = new ArrayList(10000);
        requestClients.drainTo(al);

        if(al == null || al.size()==0){
            return;
        }
        for (RequestClient  poll : al) {
            if(poll == null){
                continue;
            }
            poll.getDeferredResult().setResult( gson.toJson(list) );
        }

    }

    public void taskRun(){
        while (true){
            getQuoteAndUpdateClients();
        }

    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String client() {

        List<String> ret = Lists.newArrayList();
        Iterator<RequestClient> it = requestClients.iterator();
        while (it.hasNext()) {
            RequestClient tmp = it.next();
            boolean expire = tmp.getDeferredResult().isSetOrExpired();
            if(expire){
                continue;
            }
            ret.add( tmp.getHost() );
        }
        return JSON.toJSONString(ret);
    }


}
