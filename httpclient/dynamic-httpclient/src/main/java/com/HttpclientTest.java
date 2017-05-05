package com;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hg on 2016/12/16.
 */
public class HttpclientTest {


    public static void main(String[] args) throws IOException {

        HttpClientParams httpClientParams = new HttpClientParams();
        httpClientParams.setConnectionManagerTimeout(1000);

        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(1000);
        params.setSoTimeout(10000000);
        params.setDefaultMaxConnectionsPerHost(80);
        params.setMaxTotalConnections(100);

        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.setParams(params);

        final HttpClient client = new HttpClient(httpClientParams, manager);

        final HttpClientInfoGetter getter = new HttpClientInfoGetter(client);

        final String url = "http://localhost:8080/hello_long";
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 50; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    while (true) {
                        try {

                            Thread.sleep(RandomUtils.nextInt(400, 500));

                            GetMethod method = new GetMethod(url);
                            client.executeMethod(method);
                            String response = new String(method.getResponseBodyAsString().getBytes("utf-8"));
                            System.out.println(response);
                            System.out.println("end");
                            method.releaseConnection();
                        } catch (Exception e) {
                            System.out.println("error");
                        }
                    }
                }
            });
        }

        ExecutorService monitorThread = Executors.newFixedThreadPool(1);
        monitorThread.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(JSON.toJSONString(getter.get()));
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.out.println("main end");
    }

}