package use;

/**
 * Created by wangqinghui on 2017/11/10.
 */
public class TImeoutTest {
    @Test(timeout = 5000)
    public void testInfiniteTametakingLoop() throws InterruptedException {
        while (true)
        {
            Thread.currentThread().sleep(1000);
        }
    }
}
