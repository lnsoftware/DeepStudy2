import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;

/**
 * Created by hg on 2017/7/20.
 */
public class ReferenceQueueTest {

    static class A {
    }

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue queue = new ReferenceQueue();
        WeakReference ref = new WeakReference(new A(), queue);
        Assert.assertNotNull(ref.get());

        Object obj = null;
        obj = queue.poll();
        Assert.assertNull(obj);

        System.gc();
        TimeUnit.MILLISECONDS.sleep(200);

        Assert.assertNull(ref.get());
        obj = queue.poll();
        //注意有时候最后的Assert.assertNotNull(obj);有时会失败，因为还没有来的及把WeakReference放入监听的ReferenceQueue中。
        Assert.assertNotNull(obj);
    }

}
