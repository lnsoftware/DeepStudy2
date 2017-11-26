
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        new Thread() {

            @Override
            public void run() {
                Test test = new Test();
                while (true) {
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    printHello();
                }
            }
        }.start();
    }

    protected static void printHello() {
        Random r = new Random();
        int i = r.nextInt();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());

        System.out.println(date + " : " + i);
    }

}

