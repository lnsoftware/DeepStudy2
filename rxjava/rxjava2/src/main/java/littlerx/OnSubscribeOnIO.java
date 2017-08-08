package littlerx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by iceGeneral on 2016/10/16.
 */
public final class OnSubscribeOnIO<T> implements OnSubscribe<T> {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    final Observable<T> source;

    public OnSubscribeOnIO(Observable<T> source) {
        this.source = source;
    }

    @Override
    public void call(final Subscriber<? super T> subscriber) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                source.subscribe(subscriber);
            }
        };
        threadPool.submit(runnable);
    }
}
