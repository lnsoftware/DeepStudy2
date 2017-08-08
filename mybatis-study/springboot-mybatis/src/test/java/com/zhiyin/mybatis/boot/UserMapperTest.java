package com.zhiyin.mybatis.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.zhiyin.mybatis.boot.entity.User;
import com.zhiyin.mybatis.boot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Autowired
	UserMapper userMapper;

	@Test
	public void mapperIsNotNullTest() {
		assertThat(userMapper).isNotNull();
	}

	@Test
	public void findByStateTest() {

		User user = new User();
		user.setName("Webx");
		user.setState("London");
		user.setCountry("UK");

		userMapper.insertUser(user);
		user = new User();
		user.setName("Webo");
		user.setState("Delhi");
		user.setCountry("India");

		userMapper.insertUser(user);

		user = userMapper.findByState("Delhi");
		System.out.println("user: " + user);
		 assertThat(user.getName()).isEqualTo("Webo");
		 assertThat(user.getState()).isEqualTo("Delhi");
		 assertThat(user.getCountry()).isEqualTo("India");
	}

	@Test
	public void findByStatesTest() {

		User user = new User();
		user.setName("Webx");
		user.setState("London");
		user.setCountry("UK");

		userMapper.insertUser(user);
		user = new User();
		user.setName("Webo");
		user.setState("NewYork");
		user.setCountry("UK");

		userMapper.insertUser(user);

		String str = "London,NewYork";
		String[] qry = str.split(",");
		List<User> users = userMapper.findByStates(qry);
		System.out.println("users__0: " + users.get(0));
		System.out.println("users__1: " + users.get(1));
		assertThat(users.contains(user.getName()));
	}

	@Test
	public void addUserTest() {

		User user = new User();
		user.setName("Webx");
		user.setState("London");
		user.setCountry("UK");

		userMapper.insertUser(user);

		User userx = userMapper.findByState("London");
		assertThat(userx.getName()).isEqualTo("Webx");
		assertThat(userx.getState()).isEqualTo("London");
		assertThat(userx.getCountry()).isEqualTo("UK");
	}
}
