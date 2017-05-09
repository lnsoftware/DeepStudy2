import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class BlockingQueueTest2 {
    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<String> queue = new ArrayBlockingQueue(3);

        String poll = queue.poll(3000, TimeUnit.MILLISECONDS);

        System.out.println(poll);

        String take = "";

//        take = queue.take();
        System.out.println(take);

        queue.put("a");

        take = queue.take();
        System.out.println(take);
    }

    @Test
    public void it() {
        int capacity = 10;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(capacity);

        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        Iterator<Integer> it = queue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("queue size:" + queue.size());

    }

    @Test
    public void drain() {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue(100);
        ArrayList al = new ArrayList(100);
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        Iterator<Integer> it = al.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}