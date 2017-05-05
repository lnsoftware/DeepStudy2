package com.zhiyin.hystrix.core.property;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.netflix.config.ConfigurationManager;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
//@WebListener
public class ReloadHystrixPropertyContextListener implements ServletContextListener {

    public static void loadConfig(String config) {

        String configTrim = Optional.fromNullable(config).or("").trim();
        if (Strings.isNullOrEmpty(configTrim)) {
            return;
        }

        String[] infos = configTrim.split(";");
        if (infos == null || infos.length == 0) {
            return;
        }

        for (String tmp : infos) {

            if (Strings.isNullOrEmpty(tmp)) {
                continue;
            }

            String[] keyVal = tmp.split("=");
            if (keyVal == null || keyVal.length != 2) {
                continue;
            }

            ConfigurationManager.getConfigInstance().setProperty(keyVal[0], keyVal[1]);
        }

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("starting server");

        // It will create new thread
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(timer), new Date(), 5000);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("stopping server");
    }


}

