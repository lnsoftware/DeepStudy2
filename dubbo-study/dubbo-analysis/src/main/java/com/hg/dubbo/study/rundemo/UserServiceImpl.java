package com.hg.dubbo.study.rundemo;

import com.alibaba.dubbo.config.annotation.Service;

@org.springframework.stereotype.Service
@Service( timeout = 10000, protocol = { "dubbo" })
public class UserServiceImpl implements UserService {

	@Override
	public String hello(String name) {
		System.out.println("-----------demo simple ----");
		return "hello,"+name;
	}

}
