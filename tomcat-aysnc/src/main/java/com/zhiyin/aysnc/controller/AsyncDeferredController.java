package com.zhiyin.aysnc.controller;

import com.zhiyin.aysnc.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncDeferredController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TaskService taskService;

    @Autowired
    public AsyncDeferredController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/defer/{time}", method = RequestMethod.GET)
    public DeferredResult<String> executeSlowTask(final @PathVariable("time") int time) {
        logger.info("Request received");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(() ->
                taskService.execute(time)
        ).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
        logger.info("Servlet thread released");

        return deferredResult;
    }


    @RequestMapping(value = "/callable", method = RequestMethod.GET, produces = "text/html")
    public Callable<String> executeSlowTask2() {
        logger.info("Request received");
        Callable<String> callable = taskService::execute;

//        Callable<String> callable2 = new Callable<String>() {
//            public String call() throws Exception {
//               return  taskService.execute();
//            }
//        };

        logger.info("Servlet thread released");

        return callable;
    }


    @RequestMapping("/webAsyncTask")
    public WebAsyncTask<String> callableWithCustomTimeoutHandling() {
        System.out.println("Thread - name -" + Thread.currentThread().getName());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                // Thread.sleep(5000);
                Thread.sleep(3000);
                System.out.println("Thread - name -" + Thread.currentThread().getName());
                return "Callable result";
            }
        };


        WebAsyncTask asyncTask = new WebAsyncTask(2000, callable);
        asyncTask.onTimeout(
                new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("执行超时 thread id is ：" + Thread.currentThread().getId());
                        return "WebAsyncTask";
                    }
                }
        );

        System.out.println("Thread - name -" + Thread.currentThread().getName());
        // 这里设置Callable的超时时间为3s，超时则抛出超时异常，通过自定义的拦截器来处理该超时异常
        // 对于Callable子线程的超时的处理，在springContext.xml中配置(这里：采取的处理是，抛出自定义的超时异常)
        // 即：如果在3s内，Callable子线程未完成，则抛出自定义的超时异常
        return asyncTask;
    }

}
