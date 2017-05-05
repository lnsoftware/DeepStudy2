public class Counter {
    public static int count = 0;

    public static void inc() {
        count++;
    }

    public static void main(String[] args) {
//同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                public void run() {
//                    Counter.inc();
                    count++;
                }
            }).start();
        }
//这里每次运行的值都有可能不同可能为1000
        System.out.println("运行结果:Counter.count" + Counter.count);
    }
}