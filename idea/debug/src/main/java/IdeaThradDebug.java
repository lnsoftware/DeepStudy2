/**
 * Created by hg on 2017/7/10.
 */
public class IdeaThradDebug {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("main start.");

        new Thread() { // 断点0
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " b1"); // 断点1
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " b2"); // 断点2
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " b1");
            }
        }.start();

        System.out.println("3"); // 断点3
        //Thread.sleep(2000);
        System.out.println("4"); // 断点4
    }
}
