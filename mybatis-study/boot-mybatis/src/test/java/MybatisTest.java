import com.zhiyin.mybatis.SpringBootMybatisWithRedisApplication;
import com.zhiyin.mybatis.dao.domain.Product;
import com.zhiyin.mybatis.dao.mapper.ProductMapper;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMybatisWithRedisApplication.class)
@ActiveProfiles(profiles = "test")
public class MybatisTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void test() {

        productMapper.select(1L);

//        assertThat(testProduct.getPrice()).isEqualTo(newPrice);
    }
}
