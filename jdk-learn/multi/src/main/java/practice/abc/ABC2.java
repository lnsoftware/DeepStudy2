package practice.abc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABC2 {
    private static final int MAX_TASK_NUMBER = 3;

    public static void main(String[] args) {
        PrintTask2 printTask = new PrintTask2(MAX_TASK_NUMBER, 10);
        Thread t1 = new Thread(printTask);
        Thread t2 = new Thread(printTask);
        Thread t3 = new Thread(printTask);
        t1.setName("A");
        t2.setName("B");
        t3.setName("C");
        t1.start();
        t2.start();
        t3.start();
    }

}

class PrintTask2 implements Runnable {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int count = 0;
    private int taskNum;
    private int cycleTimes;
    private int id;

    public PrintTask2(int taskNum, int cycleTimes) {
        this.taskNum = taskNum;
        this.cycleTimes = cycleTimes;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            lock.lock();
            while (true) {
                id = count % taskNum;
                switch (name) {

                    case "A":
                        while (id != 0) {
                            condition.await();
                        }
                        break;
                    case "B":
                        while (id != 1) {
                            condition.await();
                        }
                        break;

                    case "C":
                        while (id != 2) {
                            condition.await();
                        }

                        break;

                    default:
                        break;
                }

                count++;
                if (count > cycleTimes * taskNum) {
                    Thread.currentThread().interrupt();
                } else {
                    System.out.print(name);
                }
                condition.signalAll();
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }
}