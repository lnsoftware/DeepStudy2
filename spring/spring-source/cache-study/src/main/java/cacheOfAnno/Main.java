package cacheOfAnno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext; 
 
public class Main { 
  public static void main(String[] args) { 
    ApplicationContext context = new ClassPathXmlApplicationContext( 
       "classpath:spring-cache-anno.xml");// 加载 spring 配置文件
    
    AccountService s = (AccountService) context.getBean("accountServiceBean"); 
    // 第一次查询，应该走数据库
    System.out.print("first query..."); 
    s.getAccountByName("somebody"); 
    // 第二次查询，应该不查数据库，直接返回缓存的值
    System.out.print("second query..."); 
    s.getAccountByName("somebody"); 
    System.out.println();

    OtherService other = context.getBean("otherService", OtherService.class);
    other.getAccountByName("ss");
  } 
}