package om.zhiyin.eureka;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.InstanceInfoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * Created by hg on 2017/1/18.
 */
@Slf4j
@Configuration
public class EurekaServerContextPathEnable {

    @Autowired
    private Environment env;

    // 覆盖 org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration.RefreshableEurekaClientConfiguration.eurekaApplicationInfoManager()
    @Primary
    @Bean
    @org.springframework.cloud.context.config.annotation.RefreshScope
    public ApplicationInfoManager eurekaApplicationInfoManager(
            EurekaInstanceConfig config) {

        String contextPath = Optional.fromNullable(env.getProperty("server.contextPath", String.class)).or( env.getProperty("server.context-path", String.class) );
        if(!Strings.isNullOrEmpty(contextPath)) {
            log.info("server.contextPath is set, auto set eureka info.");
            EurekaInstanceConfigBean bean = (EurekaInstanceConfigBean) config;

            if( !bean.getHealthCheckUrlPath().contains(contextPath)){
                bean.setHealthCheckUrlPath( contextPath + bean.getHealthCheckUrlPath() );
            }
            if( bean.getStatusPageUrlPath().contains(contextPath)){
                bean.setStatusPageUrlPath( contextPath + bean.getStatusPageUrlPath());
            }
            if(bean.getHomePageUrlPath().contains(contextPath)){
                bean.setHomePageUrlPath( contextPath + bean.getHomePageUrlPath());
            }

            if ( !bean.getMetadataMap().containsKey("management.context-path") ) {
                bean.getMetadataMap().put("management.context-path", contextPath);
            }
        }

        InstanceInfo instanceInfo = new InstanceInfoFactory().create(config);
        ApplicationInfoManager tmp = new ApplicationInfoManager(config, instanceInfo);

        return tmp;
    }
}
