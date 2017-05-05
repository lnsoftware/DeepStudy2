package om.zhiyin.eureka;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.netflix.appinfo.EurekaInstanceConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import static org.springframework.cloud.commons.util.IdUtils.getDefaultInstanceId;

/**
 * Created by wangqinghui on 2017/1/17.
 */
@Deprecated
public class ServerContextPathEnableAfterStartup implements ApplicationListener<ContextRefreshedEvent> {
        @Override

        public void onApplicationEvent(ContextRefreshedEvent event) {

            Environment env = event.getApplicationContext().getBean(Environment.class);
            if(env == null){
                return;
            }

//            /democlient 带有/
            String contextPath = Optional.fromNullable(env.getProperty("server.contextPath", String.class)).or( env.getProperty("server.context-path", String.class) );
            if(Strings.isNullOrEmpty(contextPath)){
                return;
            }

            EurekaInstanceConfigBean bean = event.getApplicationContext().getBean(EurekaInstanceConfigBean.class);

            bean.getMetadataMap().put("management.context-path",contextPath);
            bean.setHomePageUrlPath( contextPath + bean.getHomePageUrlPath());
            bean.setStatusPageUrlPath( contextPath + bean.getStatusPageUrlPath());
            bean.setHealthCheckUrlPath( contextPath + bean.getHealthCheckUrlPath() );

            System.out.println(JSON.toJSONString(bean));
        }
    }


