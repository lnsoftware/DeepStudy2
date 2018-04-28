package com.mindflow.api;

import com.mindflow.api.model.User;

public interface DemoService {
 
	String sayHello(String name);
 
    User findUserById(Long id);
}