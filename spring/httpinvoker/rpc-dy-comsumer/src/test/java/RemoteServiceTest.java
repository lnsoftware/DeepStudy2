import com.zhiyin.rpc.httpinvoker.demo.service.IThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hg on 2016/7/21.
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring-httpinvoker.xml")
//@SpringApplicationConfiguration(classes = {OurChatApplication.class})
public class RemoteServiceTest {

    @Autowired
    private IThreadLocalService tlService;

    @Test
    public void test(){

        tlService.testPassParm();
    }

}
