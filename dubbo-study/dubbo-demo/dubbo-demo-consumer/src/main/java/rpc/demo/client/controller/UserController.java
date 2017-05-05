package rpc.demo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import rpc.demo.api.UserService;

@Controller
public class UserController {

	@Autowired
//    @Reference
	private UserService userService;

	@RequestMapping("/")
	@ResponseBody
	public String hello(){
		return userService.hello("admin");
	}

    @RequestMapping("/timeout")
    @ResponseBody
    public String timeout(){
        return userService.timeout("timeout");
    }
	
}
