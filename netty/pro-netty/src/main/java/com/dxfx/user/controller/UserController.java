package com.dxfx.user.controller;

import org.springframework.stereotype.Controller;

import com.dxfx.netty.annotation.Action;
import com.dxfx.user.model.User;

@Controller
public class UserController {
	
	
	@Action("saveUser")
	public Object save(User user){
		System.out.println(user.getName());
		return user.getName();
	}

}
