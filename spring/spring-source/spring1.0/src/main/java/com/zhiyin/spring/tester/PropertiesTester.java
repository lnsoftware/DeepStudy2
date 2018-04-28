package com.zhiyin.spring.tester;

import com.alibaba.fastjson.JSON;
import com.zhiyin.spring.service.User;
import junit.framework.TestCase;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;

/**
 * Created by wangqinghui on 2018/4/26.
 */
public class PropertiesTester extends TestCase{

    // 获取配置信息
    public void testWithPropertiesFile() throws IOException {
        PropertiesFactoryBean pfb = new PropertiesFactoryBean();
        pfb.setLocation(new ClassPathResource("test.properties"));
        pfb.afterPropertiesSet();
        Properties props = (Properties) pfb.getObject();
        assertEquals("root", props.getProperty("rootName"));
    }

    // bean属性使用配置文件配置
    public void testPropertiesBean() throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] { "propertiesConfig.xml" });
        User user = (User) ctx.getBean("user", User.class);
        System.out.println(JSON.toJSONString(user));
        assertEquals("root",user.getName());
    }
}
