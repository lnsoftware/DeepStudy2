package com.zhiyin.hystrix.core.property;

import java.util.Timer;
import java.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j class MyTimerTask extends TimerTask {

    Timer timer;

    public MyTimerTask(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {

        LoadHystrixConfig.load();
        log.info("Executing timer task");
    }

}
