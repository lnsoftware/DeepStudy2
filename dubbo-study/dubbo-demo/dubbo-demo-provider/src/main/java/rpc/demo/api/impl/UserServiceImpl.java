package rpc.demo.api.impl;

import com.alibaba.dubbo.config.annotation.Service;

import rpc.demo.api.UserService;

@org.springframework.stereotype.Service
@Service( timeout = 10000, protocol = { "dubbo" })
public class UserServiceImpl implements UserService {

	@Override
	public String hello(String name) {
		System.out.println("-----------demo simple ----");
		return "hello,"+name;
	}

    @Override
    public String timeout(String name) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------timeout simple ----sleep 5s.");
        return "hello,"+name;
    }

}
