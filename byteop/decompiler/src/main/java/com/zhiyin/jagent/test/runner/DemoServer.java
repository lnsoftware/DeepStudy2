/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.test.runner.AttachAgent
 *  com.zhiyin.jagent.test.runner.Calculater
 *  com.zhiyin.jagent.test.runner.DemoServer
 *  com.zhiyin.jagent.test.runner.User
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.zhiyin.jagent.test.runner;

import com.zhiyin.jagent.test.runner.AttachAgent;
import com.zhiyin.jagent.test.runner.Calculater;
import com.zhiyin.jagent.test.runner.User;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Exception performing whole class analysis ignored.
 */
public class DemoServer {
    private static final Logger log = LoggerFactory.getLogger(DemoServer.class);

    public DemoServer() {
    }

    static {
    }

    public static void main(String[] args) throws Exception {
        AttachAgent.attach(DemoServer.class);
        String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("server pid:" + nameOfRunningVM);
        do {
            DemoServer.runBody();
        } while (true);
    }

    public static void runBody() throws InterruptedException {
        Calculater calculater = new Calculater();
        calculater.add(new Random().nextInt(), 1);
        try {
            User u = new User();
            u.setName(UUID.randomUUID().toString());
            u.hello();
        }
        catch (Exception e) {
            System.out.println("java.lang.ArithmeticException");
        }
        Thread.sleep(5000L);
    }
}

