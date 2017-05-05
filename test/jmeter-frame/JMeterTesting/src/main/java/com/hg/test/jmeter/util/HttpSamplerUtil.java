package com.hg.test.jmeter.util;

import org.apache.jmeter.protocol.http.sampler.HTTPSampler;

/**
 * Created by hg on 2016/4/25.
 */
public class HttpSamplerUtil {


    public static HTTPSampler getBaidu() {

        return get("baidu.com", 80, "/");

    }

    /**
     * Get获取信息
     *
     * @param domain google.com
     * @param port   80
     * @param path   /
     * @return
     */
    public static HTTPSampler get(String domain, int port, String path) {

        HTTPSampler sampler = new HTTPSampler();

//        sampler.setName("test");
//        sampler.setProperty(TestElement.TEST_CLASS, HTTPSampler.class.getName());
//        sampler.setProperty(TestElement.GUI_CLASS, JavaTestSamplerGui.class.getName());
//        sampler.setEnabled(true);


        sampler.setDomain(domain);
        sampler.setPort(port);
        sampler.setPath(path);
        sampler.setMethod("GET");
        return sampler;

    }

    /**
     * Get获取信息
     *
     * @param domain google.com
     * @param port   80
     * @param path   /
     * @return
     */
    public HTTPSampler post(String domain, int port, String path, String body) {

        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setDomain(domain);
        httpSampler.setPort(port);
        httpSampler.setPath(path);
        httpSampler.setMethod("POST");
//        httpSampler.
        return httpSampler;

    }


}
