import com.zhiyin.mysql.lock.Application;
import com.zhiyin.mysql.lock.domain.OptimisticUser;
import com.zhiyin.mysql.lock.domain.OptimisticUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class MapperTests {

	@Autowired
	private OptimisticUserMapper optimisticUserMapper;

	@Test
	public void findByName() throws Exception {

		OptimisticUser user = optimisticUserMapper.selectById(1L);

		user.setValue("v1");
		Integer ret = optimisticUserMapper.updateOptimistic(user);
		System.out.println("修改商品信息1"+(ret==1?"成功":"失败"));

		 user.setValue("v2");
		ret = optimisticUserMapper.updateOptimistic(user);
		System.out.println("修改商品信息1"+(ret==1?"成功":"失败"));


	}


}