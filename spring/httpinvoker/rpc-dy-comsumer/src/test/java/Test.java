
import com.zhiyin.rpc.httpinvoker.demo.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring-httpinvoker.xml");
        DemoService remotDemoService = (DemoService) context.getBean("remoteDemoService");
        System.out.println(remotDemoService.getName());
    }
}
