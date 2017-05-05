package com.zhiyin.frame.bootx.thirdenv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

@Slf4j
public class ThirdEnvPostProcessor implements EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        MutablePropertySources propertySources = environment.getPropertySources();

        // 由于优先级比较高，所以app name获取不到
        String env = environment.getProperty("spring.application.name");
        log.info(env);
        Properties properties = new Properties();
        properties.put("third-provider", "hg");

        //addLast 结合下面的 getOrder() 保证顺序 读者也可以试试其他姿势的加载顺序
        propertySources.addLast(new PropertiesPropertySource("thirdEnv", properties));
    }

    @Override
    public int getOrder() {
        //  +1 保证application.propertie里的内容能覆盖掉本配置文件中默认的
        // 如果不想被覆盖 可以去掉 +1  或者 -1  试试
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }
}