import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/7/20.
 */
public class FinalizeRunOnlyOnce {

    public static void main(String[] args) throws InterruptedException {
        B b = new B();
        b = null;
        System.gc();
        B.b = null;
        System.gc();

        TimeUnit.MILLISECONDS.sleep(200);
    }

    static class B {

        static B b;

        public void finalize() {
            System.out.println("method B.finalize");
            b = this;
        }
    }
}
