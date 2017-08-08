package com.zhiyin.mybatis.boot;

import java.util.List;

import com.zhiyin.mybatis.boot.entity.User;
import com.zhiyin.mybatis.boot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/service/*")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@PostMapping(value="/adduser", consumes="application/json", produces="application/json")
	@ResponseBody public User addUser(@RequestBody User user){
		userMapper.insertUser(user);
		User newUser = userMapper.findByState(user.getState());
		LOG.info("new User: " + newUser);
		return newUser;
	}
	
	@PostMapping(value="/alluser", consumes="application/json", produces="application/json")
	@ResponseBody public List<User> allUser(@RequestBody User user){		
		userMapper.insertUser(user);
		List<User> newUser = userMapper.getAllUsers();
		LOG.info("new User: " + newUser.get(0));
		return newUser;
	}

	@PostMapping(value="/getusers", consumes="application/json")
	@ResponseBody public List<User> getUsers(@RequestBody User user){
 
		addUser();
		System.out.println("Total User count: "+ userMapper.getUserCount());
 		//List<String> states = Arrays.asList(user.getState().split("\\s*,\\s*"));
		String[] states = user.getState().split(",", -1);
 		List<User> usrs = userMapper.findByStates(states);
		System.out.println("User: "+ usrs);
		
		return usrs;		
	}
	
	private void addUser(){
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
	}
}
