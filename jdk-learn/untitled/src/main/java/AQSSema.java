import java.util.concurrent.locks.AbstractQueuedSynchronizer;

class AQSSema   {
    private final Sync _sync;

    AQSSema(final int i) {
        _sync = new Sync(i);
    }

    // 通过一个 volatile state 数据来控制互斥
    private static class Sync extends AbstractQueuedSynchronizer {
        Sync(int permits) {
            setState(permits);
        }

        /* * if remaining < 0, this thread should await; * else, should acquire permits, until remaining < 0 */
        protected int tryAcquireShared(int acquires) {
            for (;;) {
                int available = getState();
                int remaining = available - acquires;
                if (remaining < 0 ||
                    compareAndSetState(available, remaining))
                    return remaining;
            }
        }

        /* * because other thread will set state, * so need spin to insure release success */
        protected boolean tryReleaseShared(int releases) {
            for (;;) {
                int p = getState();
                if (compareAndSetState(p, p + releases))
                    return true;
            }
        }
    }

    public void acquire() throws InterruptedException {
        _sync.acquireSharedInterruptibly(1);
    }

    public void release() {
        _sync.releaseShared(1);
    }




}