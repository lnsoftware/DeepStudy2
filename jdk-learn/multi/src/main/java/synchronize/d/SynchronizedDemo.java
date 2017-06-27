package synchronize.d;

public class SynchronizedDemo {

    private Integer count = 0;
    public synchronized void sout() {
        System.out.println(count);
    }

    public void add() {
        synchronized (this) {
            System.out.println(count);
        }
    }

    public static void main(String[] args) {

    }
}