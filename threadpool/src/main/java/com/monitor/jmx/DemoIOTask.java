package com.monitor.jmx;

import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/6/24.
 */
public class DemoIOTask implements Runnable {
    @Override
    public void run() {

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
