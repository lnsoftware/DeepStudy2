package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangqinghui on 2017/10/27.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
//创建代理对象
        PhoneBiz pBiz = (PhoneBiz)ac.getBean("phoneBiz");
//购买100部手机
        pBiz.buyPhone(100);
//销售88部手机
        pBiz.salePhone(88);
    }
}
