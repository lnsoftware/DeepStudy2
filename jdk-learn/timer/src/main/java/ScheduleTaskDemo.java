import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/6/16.
 */
public class ScheduleTaskDemo {
    public static void main(String[] args) {

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override public void run() {
                System.out.println("timer task.");
            }
        },5,5, TimeUnit.SECONDS);

    }
}
