package com.zhiyin.mysql.acid;

import com.alibaba.fastjson.JSON;
import com.zhiyin.mysql.acid.domain.User;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

	@Autowired
	@Qualifier("userServiceCustomTrans")
	private IUserService userService;

	@Test
	public void insert() throws Exception {
		User user = new User();
		user.setName("pang");
		user.setAge(18);
		userService.insert(user);
	}

	@Test
	public void selectAll(){
		List<User> all = userService.selectAll();
		for (User user : all) {
			System.out.println(JSON.toJSONString(user));
		}
	}
	@Test
	public void insertWith() throws Exception {
		User user = new User();
		user.setName("pang");
		user.setAge(18);
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override public void run() {
				for (Isolation isolation : Isolation.values()) {
					if(isolation == Isolation.DEFAULT){
						continue;
					}
					if(isolation == Isolation.SERIALIZABLE){
						continue;
					}
					List<User> list = userService.selectAll(isolation.value());
					System.out.print(isolation.name() + ":" + list.size() + "   ");
				}

//				Isolation isolation = Isolation.READ_UNCOMMITTED;
//					List<User> list = userService.selectAll(isolation.value());
//					System.out.print(isolation.name() + ":" + list.size() + "   ");

				System.out.println();

			}
		},2,2, TimeUnit.SECONDS);

		Executors.newFixedThreadPool(1).submit(new Runnable() {
			@Override public void run() {
				userService.insertLong(user);
			}
		});


		TimeUnit.SECONDS.sleep(20);
	}


}