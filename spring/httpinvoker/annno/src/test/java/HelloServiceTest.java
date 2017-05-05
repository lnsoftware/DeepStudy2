
import com.taofang.service.IHelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-beans.xml", "annno/src/main/resources/spring-mvc.xml"})
public class HelloServiceTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    private IHelloService service;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }

    @Test
    public void testSay() {
        //  service.say("zhanghailei");
        //System.out.println(applicationContext.getBean("/helloServiceExporter"));
//        ClassPathResource resource = new ClassPathResource("classpath:org/springframework/remoting/caucho/HessianServiceExporter.class");
//        System.out.println(resource);
//        try {
//            System.out.println(resource.getFile().getAbsolutePath());
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
}
