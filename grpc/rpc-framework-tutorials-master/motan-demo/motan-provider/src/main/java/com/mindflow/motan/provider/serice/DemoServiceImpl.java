package com.mindflow.motan.provider.serice;

import com.mindflow.api.DemoService;
import com.mindflow.api.model.User;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "hello, "+name;
    }

    @Override
    public User findUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("ricky_"+id);
        return user;
    }
}
