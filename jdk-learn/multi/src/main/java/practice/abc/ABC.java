package practice.abc;

public class ABC {

    private static final int MAX_TASK_NUMBER = 3;

    public static void main(String[] args) {
        PrintTask printTask = new PrintTask(MAX_TASK_NUMBER, 10);
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

class PrintTask implements Runnable {
    private int taskNum;
    private int count = 0;
    private int cycleTimes;
    private int id;

    PrintTask(int taskNum, int cycleTimes) {
        this.taskNum = taskNum;
        this.cycleTimes = cycleTimes;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                String name = Thread.currentThread().getName();
                while (true) {

                    id = count % taskNum;
                    switch (name) {
                        case "A":
                            while (id != 0) {
                                wait();
                            }
                            break;

                        case "B":
                            while (id != 1) {
                                wait();
                            }

                            break;
                        case "C":
                            while (id != 2) {
                                wait();
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

                    notifyAll();
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
