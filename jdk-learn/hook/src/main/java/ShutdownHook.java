public class ShutdownHook {

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new DbShutdownWork());
        System.out.println("JVM 已启动");

        while(true){
            Thread.sleep(10L);
        }
    }

    static class DbShutdownWork extends Thread{
        public void run(){
            System.out.println("关闭数据库连接");
        }
    }
}