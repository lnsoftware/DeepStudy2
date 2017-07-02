package com.hg.tp.task;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author onlinetechvision.com
 * @version 1.0.0
 * @since 17 Oct 2011
 */
public class TestRejectedExecutionHandler implements RejectedExecutionHandler {

    private static Logger log = LoggerFactory.getLogger(TestRejectedExecutionHandler.class);

    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        log.debug(runnable.toString() + " : has been rejected");
    }
}