import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/7/20.
 */
public class Finallize {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        a.b = new B();
        a = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
    }
}
class A {
    B b;
    public void finalize() {
        System.out.println("method A.finalize at " + System.nanoTime());
    }
}

class B {
    public void finalize() {
        System.out.println("method B.finalize at " + System.nanoTime());
    }
}