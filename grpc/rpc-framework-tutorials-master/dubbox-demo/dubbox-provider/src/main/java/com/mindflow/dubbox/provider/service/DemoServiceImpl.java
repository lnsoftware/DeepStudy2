package com.mindflow.dubbox.provider.service;

import com.mindflow.api.DemoService;
import com.mindflow.api.model.User;
import org.springframework.stereotype.Service;

/**
 * @author Ricky Fung
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return String.format("Hello %s", name);
    }

    @Override
    public User findUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("ricky-"+id);
        user.setAge(28);
        return user;
    }
}
