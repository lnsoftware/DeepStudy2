import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class LockTest {

    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(new CountRunnable(countDownLatch, cyclicBarrier), "test-1").start();
        new Thread(new CountRunnable(countDownLatch, cyclicBarrier), "test-2").start();
        new Thread(new CountRunnable(countDownLatch, cyclicBarrier), "test-3").start();
        countDownLatch.await();
        System.out.println(LockTest.i);
    }

    static class CountRunnable implements Runnable{

        private CountDownLatch countDownLatch;
        private CyclicBarrier cyclicBarrier;

        public CountRunnable(CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier){
            this.countDownLatch = countDownLatch;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(int j = 0; j < 10000; j++){
                LockTest.i++;
            }
            countDownLatch.countDown();
        }
    }
}