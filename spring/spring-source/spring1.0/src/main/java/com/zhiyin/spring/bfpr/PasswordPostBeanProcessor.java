package com.zhiyin.spring.bfpr;

import com.zhiyin.spring.service.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 它会影响处理Bean的加载时机，
 * https://blog.csdn.net/micro_hz/article/details/64920237
 */
public class PasswordPostBeanProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 修改user，user会提前加载。
        User user = (User) beanFactory.getBean("user",User.class);
        // 模拟加密算法
        // 真实密码为原来的密码数字对应的+1
        user.setPassword(decode(user.getPassword()));
    }

    private String decode(String password) {
        StringBuffer sb = new StringBuffer();
        for (char c : password.toCharArray()) {
            sb.append((int) c - 47);
        }
        return sb.toString();
    }

}