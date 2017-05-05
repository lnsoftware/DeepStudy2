package com.hg.btrace.demo.demo1;

public class TestDemo {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
            }

            WorkThread thread = new WorkThread();
            thread.start();
        }
    }
}

class WorkThread extends Thread {
    private WorkTask task = new WorkTask();

    public void run() {
        task.runInternal();
        task.runInternal("test");
        task.runInternal(1);
        task.runInternal("test", 1);
        task.runInternal(new String[]{"test"});
        task.runReturn(new String[]{"test"});
    }

}

class WorkTask {

    public void call() {
    }

    public void call(String str) {
    }


    public void runInternal() {
        //System.out.println("com.bulain.btrace.WorkThread.run()");
    }

    public void runInternal(String str) {
        //System.out.println("com.bulain.btrace.WorkThread.run(String)");
    }

    public void runInternal(int i) {
        //System.out.println("com.bulain.btrace.WorkThread.run(int)");
    }

    public void runInternal(String str, int i) {
        //System.out.println("com.bulain.btrace.WorkThread.run(String, int)");
    }

    public void runInternal(String[] strs) {
        //System.out.println("com.bulain.btrace.WorkThread.run(String[])");
    }

    public String[] runReturn(String[] strs) {
        return new String[]{"return01", "return02"};
    }

}