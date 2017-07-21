import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by hg on 2017/7/20.
 */
public class PhantomReferenceTest {

    public static void main(String[] args) {
        ReferenceQueue<String> refQueue = new ReferenceQueue<String>();
        PhantomReference<String> referent = new PhantomReference<String>(
            new String("T"), refQueue);
        System.out.println(referent.get());// null

        System.gc();
        System.runFinalization();

        System.out.println(refQueue.poll() == referent); //true
    }
}
