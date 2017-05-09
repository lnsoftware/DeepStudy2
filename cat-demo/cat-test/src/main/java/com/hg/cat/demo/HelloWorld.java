package com.hg.cat.demo;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2015/12/25.
 */

public class HelloWorld {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10000; i++) {
            Transaction t = Cat.newTransaction("Call", "Hello");
            Cat.logEvent("as","ss");
            TimeUnit.MILLISECONDS.sleep(500);
            t.setStatus(Message.SUCCESS);
            System.out.println("是是是");
            t.complete();
        }

    }


}
