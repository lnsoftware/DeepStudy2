package com.zhiyin.byteop.demo.advice;

import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hg on 17/2/20.
 */
public class ProfiledAdvice {

    @Advice.OnMethodEnter(suppress = Exception.class)
    public static long enter(@Advice.Origin("#t") String clazzName,
                             @Advice.Origin("#m") String methodName) {
        Logger log = LoggerFactory.getLogger(clazzName);
        log.debug("enter method:{}.{}", clazzName, methodName);
        return System.nanoTime();
    }

    @Advice.OnMethodExit(suppress = Exception.class)
    static void exit(
            @Advice.Origin("#t") String clazzName,
            @Advice.Origin("#m") String methodName,
            @Advice.Enter long value) {
        Logger log = LoggerFactory.getLogger(clazzName);
//    log.debug("enter method:{}.{}",clazzName,methodName);
        log.debug("duration time:" + ((System.nanoTime() - value) / (1000 * 1000)) + "ms");
    }


//  public static void main(String[] args) throws InterruptedException {
//    long t1 = System.currentTimeMillis();
//    long t2 = System.nanoTime();
//    Thread.sleep(1000);
//
//    System.out.println("System.currentTimeMillis() : " + (System.currentTimeMillis() - t1));
//    System.out.println("System.nanoTime() : " + (System.nanoTime() - t2));
//
//  }

}
