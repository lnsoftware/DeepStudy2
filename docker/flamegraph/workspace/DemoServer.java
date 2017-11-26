import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangqinghui on 2017/11/26.
 */
public class DemoServer {
    Random random = new Random();
    public static void main(String[] args) throws InterruptedException {
        new DemoServer().start();
    }

    public void start(){
        while (true) {
            for (int i = 0; i < random.nextInt(1000); i++) {
                int sum = cal();
            }
        }
    }

    public int cal(){
        int sum = 0;
        for (int i = 0; i < random.nextInt(1000); i++) {
            sum += i;
        }
        return sum;
    }
}
