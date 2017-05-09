package future;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

// future main 方法
public class FutureMain {

    private static final Logger logger = Logger.getLogger("ss");

    public static void main(String[] args) throws Exception{

        LocalCacheConnection localCacheConnection = new LocalCacheConnection();
        Future<?> future = localCacheConnection.getResult("connection");

        new Thread(){
            @Override
            public void run() {
                try {
                    logger.info("future.get() : " + future.get(2 , TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    logger.info("future.get() over");
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    logger.info("future.get() : " + future.get(4, TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    logger.info("future.get() over");
                }
            }
        }.start();
    }
}