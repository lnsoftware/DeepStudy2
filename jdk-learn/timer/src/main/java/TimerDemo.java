
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TimerDemo {

    static class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
        }
    }

    public static void main(String args[]) {

        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 1000,2000);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel(); //Terminate the timer thread




    }
}
