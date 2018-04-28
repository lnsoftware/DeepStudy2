package com.zhiyin.spring.tester;

import com.zhiyin.spring.lifecycle.LifecycleBean;
import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangqinghui on 2018/4/26.
 */
public class BeanLifecycleTester extends TestCase{



    public void testLifecycle() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] { "lifecycle.xml" });
        LifecycleBean lifecycle = (LifecycleBean) ctx.getBean("lifecycle", LifecycleBean.class);
        System.out.println(lifecycle.businessMethod());

        ctx.close();
    }

}
