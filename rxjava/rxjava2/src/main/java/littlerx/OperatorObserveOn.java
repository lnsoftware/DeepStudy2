package littlerx;

import os.Handler;

/**
 * Created by iceGeneral on 2016/10/16.
 */
public final class OperatorObserveOn<T> implements Operator<T, T> {
    private Handler handler;

    public OperatorObserveOn(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Subscriber<T> s = new Subscriber<T>() {
            @Override
            public void onNext(final T t) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        subscriber.onNext(t);
                    }
                });
            }
        };
        return s;
    }
}