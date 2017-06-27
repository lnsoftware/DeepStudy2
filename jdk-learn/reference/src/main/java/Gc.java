import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by hg on 2017/6/26.
 */
public class Gc {
    public static void main(String[] args) throws Exception {
        String abc = new String("abc");
        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        WeakReference<String> abcWeakRef = new WeakReference<String>(abc,
            referenceQueue);
        abc = null;
        System.gc();
        Thread.currentThread().sleep(3000);
    }
}
//https://vinoit.me/2017/06/23/java-phantom-reference/