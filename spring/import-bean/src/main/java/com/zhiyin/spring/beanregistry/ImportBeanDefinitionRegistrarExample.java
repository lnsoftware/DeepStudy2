package com.zhiyin.spring.beanregistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;

public class ImportBeanDefinitionRegistrarExample {

    public static void main (String[] args) {
        ApplicationContext context =
                  new AnnotationConfigApplicationContext(
                            MyConfig.class);
        ClientBean bean = context.getBean(ClientBean.class);
        bean.doSomething();
    }
}

@Configuration
@Import(MyBeanRegistrar.class)
class MyConfig {

    @Bean
    ClientBean clientBean () {
        return new ClientBean();
    }

}

class MyBeanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions (AnnotationMetadata importingClassMetadata,
                                         BeanDefinitionRegistry registry) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(AppBean.class);
        gbd.getPropertyValues().addPropertyValue("str", "value set from registrar");
        registry.registerBeanDefinition("appBean", gbd);
    }
}

class ClientBean {
    @Autowired
    private AppBean appBean;

    public void doSomething () {
        appBean.process();
    }
}

class AppBean {
    private String str;

    public void setStr (String str) {
        this.str = str;
    }

    public void process () {
        System.out.println(str);
    }
}