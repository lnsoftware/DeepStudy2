package source;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by hg on 2017/7/21.
 */
public class ObserverTest {

    @Test
    public void tt(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("com");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer);

    }
}
