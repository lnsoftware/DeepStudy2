package com.zhiyin.delay.queue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {
    public static void main(String[] args) {
        // Creates an instance of blocking queue using the DelayQueue.
        final BlockingQueue<DelayObject> queue = new DelayQueue<>();
        final Random random = new Random();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Put some Delayed object into the Queue.
                        int delay = random.nextInt(10000);
                        DelayObject object = new DelayObject(
                                UUID.randomUUID().toString(), delay);

                        System.out.printf("Put object = %s%n", object);
                        queue.put(object);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Producer Thread").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Take elements out from the DelayQueue object.
                        DelayObject object = queue.take();
                        System.out.printf("[%s] - Take object = %s%n",
                                Thread.currentThread().getName(), object);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Consumer Thread-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Take elements out from the DelayQueue object.
                        DelayObject object = queue.take();
                        System.out.printf("[%s] - Take object = %s%n",
                                Thread.currentThread().getName(), object);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Consumer Thread-2").start();
    }
}

class DelayObject implements Delayed {
    private String data;
    private long startTime;

    public DelayObject(String data, long delay) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.startTime < ((DelayObject) o).startTime) {
            return -1;
        }
        if (this.startTime > ((DelayObject) o).startTime) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                "data='" + data + "'"+
                ", startTime=" + startTime +
                '}';
    }
}