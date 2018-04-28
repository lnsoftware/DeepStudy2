//package com.zhiyin.spring.tester;
//
//import org.springframework.beans.factory.config.PropertiesFactoryBean;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.IOException;
//import java.util.Properties;
//
//import static junit.framework.Assert.assertEquals;
//
///**
// * Created by wangqinghui on 2018/4/28.
// */
//public class ThreadPoolTester {
//
//    public void testWithPropertiesFile() throws IOException {
//        ThreadPool pfb = new PropertiesFactoryBean();
//        pfb.setLocation(new ClassPathResource("test.properties"));
//        pfb.afterPropertiesSet();
//        Properties props = (Properties) pfb.getObject();
//        assertEquals("root", props.getProperty("rootName"));
//    }
//}
