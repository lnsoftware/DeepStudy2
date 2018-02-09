public class ShutdownHookDemo {

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new DbShutdownWork());
        System.out.println("DemoServer Started");

        while(true){
            Thread.sleep(10L);
        }
    }

    static class DbShutdownWork extends Thread{
        public void run(){
            System.out.println("DemoServer Clean");
        }
    }
}