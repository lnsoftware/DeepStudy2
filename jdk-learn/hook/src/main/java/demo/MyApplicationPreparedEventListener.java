package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public class MyApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    private Logger logger = LoggerFactory.getLogger(MyApplicationPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext cac = event.getApplicationContext();
        passContextInfo(cac);
    }

    /**
     * 传递上下文
     * @param cac
     */
    private void passContextInfo(ApplicationContext cac) {
        //dosomething()
    }

}