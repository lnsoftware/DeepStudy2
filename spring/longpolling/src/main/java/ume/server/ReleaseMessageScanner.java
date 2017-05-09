package ume.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ume.ApolloThreadFactory;
import ume.TableChangeMeta;

@Component
public class ReleaseMessageScanner implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(ReleaseMessageScanner.class);
    private static final int DEFAULT_SCAN_INTERVAL_IN_MS = 1000;
    @Autowired
    private Environment env;

    private ScheduledExecutorService executorService;


    public ReleaseMessageScanner() {
        executorService = Executors.newScheduledThreadPool(1, ApolloThreadFactory
            .create("ReleaseMessageScanner", true));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("start schedule task scan msg.");
        executorService.scheduleWithFixedDelay((Runnable) () -> {
            try {
                scanMessages();
            } catch (Throwable ex) {
                logger.error("Scan and send message failed", ex);
            } finally {
            }
        }, 5000, 5000, TimeUnit.MILLISECONDS);

    }

    private void scanMessages() {
//            TableChangeMeta meta = new TableChangeMeta();
//            meta.setTableName("Hello");
//            MessageQueue.queue.add(meta);
        logger.info("scan msg.");
    }

}
