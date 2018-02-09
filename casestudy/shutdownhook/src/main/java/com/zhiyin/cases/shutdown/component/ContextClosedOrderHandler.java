package com.zhiyin.cases.shutdown.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 控制bean关闭顺序
 */
@Slf4j
@ConditionalOnProperty(name = "elegant.close", havingValue = "true")
@Component
public class ContextClosedOrderHandler implements ApplicationListener<ContextClosedEvent>, ApplicationContextAware, BeanPostProcessor {

    private ApplicationContext context;

    public void onApplicationEvent(ContextClosedEvent event) {

        // 如果不能正常复现shutdown时，线程池先于数据连接关闭，采用这种方式
//        DataSource dataSource = context.getBean(DataSource.class);
//        try {
//            ((BasicDataSource) dataSource).close();
//        } catch (SQLException e) {
//            log.error("manual close dataSource",e);
//        }

        //@Schedule的处理Bean，不关闭会一直提交任务。
        ScheduledAnnotationBeanPostProcessor pro = context.getBean(ScheduledAnnotationBeanPostProcessor.class);
        pro.destroy();

        log.info("context close event.");
        Map<String, ThreadPoolTaskScheduler> schedulers = context.getBeansOfType(ThreadPoolTaskScheduler.class);

        for (ThreadPoolTaskScheduler scheduler : schedulers.values()) {
            scheduler.getScheduledExecutor().shutdown();
            try {
                scheduler.getScheduledExecutor().awaitTermination(2000, TimeUnit.MILLISECONDS);
                if (scheduler.getScheduledExecutor().isTerminated() || scheduler.getScheduledExecutor().isShutdown())
                    log.info("Scheduler " + scheduler.getThreadNamePrefix() + " has stoped");
                else {
                    log.info("Scheduler " + scheduler.getThreadNamePrefix() + " has not stoped normally and will be shut down immediately");
                    scheduler.getScheduledExecutor().shutdownNow();
                    log.info("Scheduler " + scheduler.getThreadNamePrefix() + " has shut down immediately");
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Map<String, ThreadPoolTaskExecutor> executers = context.getBeansOfType(ThreadPoolTaskExecutor.class);

        for (ThreadPoolTaskExecutor executor : executers.values()) {
            int retryCount = 0;
            while (executor.getActiveCount() > 0 && ++retryCount < 51) {
                try {
                    log.info("Executer " + executor.getThreadNamePrefix() + " is still working with active " + executor.getActiveCount() + " work. Retry count is " + retryCount);
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!(retryCount < 51))
                log.info("Executer " + executor.getThreadNamePrefix() + " is still working.Since Retry count exceeded max value " + retryCount + ", will be killed immediately");
            executor.shutdown();
            log.info("Executer " + executor.getThreadNamePrefix() + " with active " + executor.getActiveCount() + " work has killed");
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context = context;

    }

    @Override
    public Object postProcessAfterInitialization(Object object, String arg1)
            throws BeansException {
        return object;
    }

    @Override
    public Object postProcessBeforeInitialization(Object object, String arg1)
            throws BeansException {
        if (object instanceof ThreadPoolTaskScheduler)
            ((ThreadPoolTaskScheduler) object).setWaitForTasksToCompleteOnShutdown(true);
        if (object instanceof ThreadPoolTaskExecutor)
            ((ThreadPoolTaskExecutor) object).setWaitForTasksToCompleteOnShutdown(true);
        return object;
    }
}