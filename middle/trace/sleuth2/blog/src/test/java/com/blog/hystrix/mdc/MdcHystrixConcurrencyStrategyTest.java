package com.blog.hystrix.mdc;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.HystrixPlugins;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * 测试Hystrix MDC传参
 * Created by hg on 2016/12/22.
 */
@Slf4j
public class MdcHystrixConcurrencyStrategyTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(30);

    @Test
    public void doBiz(){
        MDC.put("traceId",UUID.randomUUID().toString());
        String serverUrl = "";
        log.info("call remote server.");
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("mdc"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("biz"));
        HystrixCommand<String> command = new HystrixCommand<String>(setter) {
            @Override
            protected String run() throws Exception {
                log.info("command run.");
                return callRemote(serverUrl);
            }
        };
        command.execute();
    }

    private String callRemote(String url) {
        log.info(url);
        return "hello";
    }

    @Test
    public void doBiz2(){
        Transaction transacton = Cat.newTransaction("biz", "http");
        String serverUrl = "";
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("cat"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("biz"));
        HystrixCommand<String> command = new HystrixCommand<String>(setter) {
            @Override
            protected String run() throws Exception {
                Cat.logEvent("biz","callremote");
                return callRemote(serverUrl);
            }
        };
        command.execute();
        transacton.setSuccessStatus();
        transacton.complete();
    }

    @Test
    public void notRegisterConcurrencyStrategy() {
        MDC.put("traceId", UUID.randomUUID().toString());
        MdcHystrixCommand command = new MdcHystrixCommand("mdc");
        String traceId = command.execute();
        assertThat(traceId).isNotEqualTo(MDC.get("traceId"));
    }

    @Test
    public void registerConcurrencyStrategy() {
        MDC.put("traceId", UUID.randomUUID().toString());
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MdcHystrixConcurrencyStrategy());
        MdcHystrixCommand command = new MdcHystrixCommand("mdc");
        String traceId = command.execute();
        assertThat(traceId).isEqualTo(MDC.get("traceId"));
    }

    @Test
    public void batchTest() {

        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MdcHystrixConcurrencyStrategy());

        for (int i = 0; i < 20; i++) {
            MdcTraceTask task = new MdcTraceTask("" + i);
            executorService.submit(task);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
