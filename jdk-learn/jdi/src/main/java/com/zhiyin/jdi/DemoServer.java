package com.zhiyin.jdi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DemoServer {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int i = 0;
        while(i<10000) {
            int sleep = random.nextInt(1000);
            TimeUnit.MILLISECONDS.sleep(sleep);
            i++;

            printHello();
        }
    }

    private static void printHello() {
            Random r = new Random();
            int i = r.nextInt();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());

            System.out.println(date + " : " + i);
        }

    public static void userInfo() {
        System.out.println(new UserInfo(1));
    }

}
class UserInfo {
    private long id;

    public UserInfo(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return "com.zhiyin.jdi.UserInfo(id=" + this.getId() + ")";
    }
}