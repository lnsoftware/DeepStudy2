package com.zhiyin.jagent.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * premain的agent测试
 */
public class PremainAgentTest {

    @Test
    public void testTimeConsume() throws InterruptedException {

        Sleeping sleeping = new Sleeping();
        sleeping.randomSleep();

         sleeping = new Sleeping();
        sleeping.randomSleep();


        LoggerFactory.getLogger(PremainAgentTest.class).info("sssss");
//
//        LoggerFactory.getLogger(AgentTest.class).info("ccc");
//
//        LoggerFactory.getLogger("trace").info("trr");

    }
}

@Slf4j
class Sleeping {

    public void randomSleep() throws InterruptedException {
        long randomSleepDuration = (long) (500 + Math.random() * 700);
//        log.info("sleep for {}ms",randomSleepDuration);
        Thread.sleep(randomSleepDuration);
        hello();
    }

    public void hello() {
        log.info("hello running!");
    }
}