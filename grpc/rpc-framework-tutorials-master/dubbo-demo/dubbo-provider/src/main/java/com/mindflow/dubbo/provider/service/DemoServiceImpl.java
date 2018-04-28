package com.mindflow.dubbo.provider.service;

import com.mindflow.api.DemoService;
import com.mindflow.api.model.User;
import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoServiceImpl implements DemoService {
 
	@Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

	@Override
	public User findUserById(Long id) {
		
		User user = new User();
		user.setId(id);
		user.setName("Ricky");
		user.setAge(26);
		
		return user;
	}
 
}