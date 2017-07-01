package basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by hg on 2017/6/29.
 */
public class MainTest {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(MainTest.class.getPackage().getName());

        context.getBean(DemoService.class);
//        FirstService firstService = context.getBean(FirstService.class);
//        System.out.println(firstService.hello("HG"));
    }
}
