package com.zhiyin.mysql.acid;

import com.zhiyin.mysql.acid.domain.User;
import com.zhiyin.mysql.acid.domain.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class MapperTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	@Rollback
	public void findByName() throws Exception {


		userMapper.insert(new User("pang",18));

		User u = userMapper.findByName("pang");
		Assert.assertEquals(18, u.getAge().intValue());
	}


}