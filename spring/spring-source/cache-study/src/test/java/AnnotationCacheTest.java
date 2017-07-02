import com.zhiyin.spring.cache.AccountService;
import com.zhiyin.spring.cache.OtherService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationCacheTest {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.zhiyin.spring.cache");

        AccountService accountService = context.getBean(AccountService.class);
        // 第一次查询
        System.out.print("first query...");
        accountService.getAccountByName("somebody");
        // 第二次查询
        System.out.print("second query...");
        accountService.getAccountByName("somebody");
        System.out.println();

        OtherService otherService = context.getBean(OtherService.class);
        otherService.getAccountByName("hh");
    }
}