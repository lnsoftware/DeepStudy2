package com.zhiyin.mybatis.boot;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhiyin.mybatis.boot.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testAddUserService() throws Exception {
		final String BASE_URL = "http://localhost:" + randomServerPort;
		String expected_name = "Juno";
		User user = new User();
		user.setName(expected_name);
		user.setState("Belfast");
		user.setCountry("UK");

		Object urlVariables = null;
		ResponseEntity<User> entity = restTemplate.postForEntity(BASE_URL + "/service/adduser", user, User.class,
				urlVariables);

		System.out.println(entity.getStatusCode());
		System.out.println(entity.getBody().getName());
		
		String actual_name = entity.getBody().getName();
		
		assertEquals(expected_name, actual_name);
	}
	
/*	@Test
	public void testGetAllUserService() throws Exception {
		final String BASE_URL = "http://localhost:" + randomServerPort;
		String expected_name = "Juno";
		User user = new User();
		user.setName(expected_name);
		user.setState("Belfast");
		user.setCountry("UK");

		Object urlVariables = null;
		ResponseEntity<List> entity = (ResponseEntity<List>) restTemplate.postForEntity(BASE_URL + "/service/alluser", user, List.class,
				urlVariables);

		System.out.println(entity.getStatusCode());
		List o = entity.getBody();
		String u = entity.getBody().get(0).toString();
		jsonPath(expression, matcher);
		//System.out.println(u.getName());		
		//String actual_name = u.getName();
		
		//assertEquals(expected_name, actual_name);
	}*/
}
