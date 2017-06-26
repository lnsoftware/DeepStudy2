package com.zhiyin.aysnc.controller;

import com.google.common.collect.Queues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class SimpleController {

    AtomicInteger count = new AtomicInteger();

    @SuppressWarnings("rawtypes")
    BlockingQueue<DeferredResult<String>> queue = Queues.newLinkedBlockingQueue(1000);

    @RequestMapping(value = "/defer", method = RequestMethod.GET)
    public DeferredResult<String> getQuote() {
        log.info("rec req.");
        final DeferredResult<String> deferredResult = new DeferredResult<String>();
        queue.add(deferredResult);
        return deferredResult;
    }

    public void getQuoteAndUpdateClients() {
        try {
            DeferredResult<String> poll = queue.poll(100, TimeUnit.MILLISECONDS);
            if (poll == null) {
                return;
            }
            String ret = "num:" + count.incrementAndGet();
            log.info("set result:" + ret);
            poll.setResult(ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Scheduled(fixedDelay = 2000)
    public void AutoDone() {
        getQuoteAndUpdateClients();
    }
}
