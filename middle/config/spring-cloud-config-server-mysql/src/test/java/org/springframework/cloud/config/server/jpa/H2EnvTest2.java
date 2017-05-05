package org.springframework.cloud.config.server.jpa;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.EnableDbConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoConfigServerApplication.class})
@WebAppConfiguration
@ActiveProfiles("h2")
public class H2EnvTest2 {

    @Autowired
    EnvironmentRepository environmentRepository;

    @Autowired
    private ConfigInfoRepository configInfoRepository;

    static ConfigInfo configInfo() {
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setApplication("demo-app");
        configInfo.setProfile("dev");
        configInfo.setKey("demo-key");
        configInfo.setValue("val");
        return configInfo;
    }

    @Before
    public void ini() {
        configInfoRepository.save(configInfo());
    }

    @Test
    public void repositoryTest() {
        List<ConfigInfo> list = configInfoRepository.findByApplicationAndProfile(configInfo().getApplication(), configInfo().getProfile());
        assertEquals(list.size(), 1);
        assertEquals(configInfo().getValue(), list.get(0).getValue());
    }

//    这个应该在config-client中测试
//    @Autowired
//    org.springframework.core.env.Environment environment;
//    @Test
//    public void envTest(){
//        assertEquals(environment.getProperty( configInfo().getKey()), configInfo().getValue() );
//    }

    @Test
    public void cloudEnvSel() {
        Environment environment = environmentRepository.findOne(configInfo().getApplication(), configInfo().getProfile(), null);
        log.info(JSON.toJSONString(environment));
        assertTrue(environment.getPropertySources().size() > 0);
        assertEquals(configInfo().getValue(), environment.getPropertySources().get(0).getSource().get(configInfo().getKey()));
    }

}

// Don't use @SpringBootApplication because we don't want to component scan
@Configuration
@EnableAutoConfiguration
@EnableDbConfigServer
@RestController
class DemoConfigServerApplication {
}