package practice.abc;

import java.util.concurrent.Semaphore;

public class ABC3 {

    public static void main(String[] args) {
        //
        Semaphore firstSema = new Semaphore(1);
        Semaphore secondSema = new Semaphore(0);
        Semaphore thirdSema = new Semaphore(0);

        PrintTask3 printTask = new PrintTask3(10, firstSema, secondSema, thirdSema);
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

class PrintTask3 implements Runnable {
    Semaphore firstSema;
    Semaphore secondSema;
    Semaphore thirdSema;
    private int cycleTimes;

    PrintTask3(int cycleTimes, Semaphore firstSema, Semaphore secondSema, Semaphore thirdSema) {
        this.cycleTimes = cycleTimes;
        this.firstSema = firstSema;
        this.secondSema = secondSema;
        this.thirdSema = thirdSema;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < cycleTimes; i++) {
                String name = Thread.currentThread().getName();
                switch (name) {
                    case "A":
                        firstSema.acquire();
                        System.out.print(name);
                        secondSema.release();
                        break;
                    case "B":
                        secondSema.acquire();
                        System.out.print(name);
                        thirdSema.release();
                        break;

                    case "C":
                        thirdSema.acquire();
                        System.out.print(name);
                        firstSema.release();
                        break;

                    default:
                        break;
                }
            }
        } catch (InterruptedException e) {

        }
    }
}