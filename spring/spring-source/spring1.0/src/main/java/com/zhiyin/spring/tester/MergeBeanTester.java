package com.zhiyin.spring.tester;

import com.alibaba.fastjson.JSON;
import com.zhiyin.spring.service.Student;
import com.zhiyin.spring.service.User;
import junit.framework.TestCase;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by wangqinghui on 2018/4/26.
 */
public class MergeBeanTester extends TestCase{

    // bean属性使用配置文件配置
    public void testPropertiesBean() throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] { "mergeBeanConfig.xml" });
        Student user = (Student) ctx.getBean("stu", Student.class);
        System.out.println(JSON.toJSONString(user));
        assertEquals("hg",user.getName());
    }
}
