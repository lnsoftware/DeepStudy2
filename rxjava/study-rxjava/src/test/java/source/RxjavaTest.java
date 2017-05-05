package source;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
 
/**
 */
public class RxjavaTest {
 
    public static void main(String args[]){
        testSubscribe();
//        testMySubscribe();
        testMap();
        testMyMap();
    }
 
    // ---------- test ------------
    private static Observable.OnSubscribe<String> mOnSubscribeStr = new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("hello rxjava.");
        }
    };
 
    private static Observable<String> mObservableStr = Observable.create(mOnSubscribeStr);  // 数据源
 
    private static Subscriber<String> mSubscriberStr = new Subscriber<String>() {
        @Override
        public void onCompleted() {}
 
        @Override
        public void onError(Throwable e) {}
 
        @Override
        public void onNext(String String) {
            System.out.println(String);
        }
    };
 
    private static void testSubscribe(){
        System.out.println("testSubscribe: ");
 
        mObservableStr.subscribe(mSubscriberStr);
 
        System.out.println("");
    }
    // ---------- test ------------
 
    // ---------- testMySubscribe ------------
    private static void testMySubscribe(){
        System.out.println("testMySubscribe: ");
 
        mySubscribe(mObservableStr, mSubscriberStr);
 
        System.out.println("");
    }
 
    private static void mySubscribe(Observable<String> observableStr, Subscriber<String> subscriberStr){
        mOnSubscribeStr.call(subscriberStr);
    }
    // ---------- testMySubscribe ------------
 
    // ---------- testMap ------------
    private static Func1<String, Integer> mFuncStrToInt = new Func1<String, Integer>() {
        @Override
        public Integer call(String String) {
            return String.length();
        }
    };
 
    private static Subscriber<Integer> mSubscriberInt = new Subscriber<Integer>() {
        @Override
        public void onCompleted() {}
 
        @Override
        public void onError(Throwable e) {}
 
        @Override
        public void onNext(Integer length) {
            System.out.println("length = " + length);
        }
    };
 
    private static void testMap(){
        System.out.println("testMap: ");
 
        mObservableStr
                .map(mFuncStrToInt)
                .subscribe(mSubscriberInt);
 
        System.out.println("");
    }
    // ---------- testMap ------------
 
    // ---------- testMyMap ------------
    private static void testMyMap(){
        System.out.println("testMyMap: ");
 
        myMap(mObservableStr, mFuncStrToInt)    // 等价于 mObservableStr.map(mFuncStrToInt)
                .subscribe(mSubscriberInt); // 1: 传入 mSubscriberInt , 由 onSubscribeInt.call() 接收
 
        System.out.println("");
    }
 
    private static Observable<Integer> myMap(final Observable<String> observableStr, final Func1<String, Integer> func){
 
        Observable.OnSubscribe<Integer> onSubscribeInt = new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriberInt) { // 2: 接收 mSubscriberInt
 
                Subscriber<String> subscriberStr = new Subscriber<String>() {   // 3: 包裹 subscriberInt, 转换成 subscriberStr
                    @Override
                    public void onCompleted() {
                        subscriberInt.onCompleted();
                    }
 
                    @Override
                    public void onError(Throwable e) {
                        subscriberInt.onError(e);
                    }
 
                    @Override
                    public void onNext(String String) { // 5: 回溯到最上游 create 里, 再不断下发数据
                        Integer length = func.call(String); // 6: 变换
                        subscriberInt.onNext(length);   // 7: 下发新数据给下游
                    }
                };
 
                observableStr.subscribe(subscriberStr); // 4: 包裹了 mSubscriberInt 以后, 丢给上游 (即订阅了上游)
                // 等价于 mOnSubscribeStr.call(subscriberStr), 有点像责任链模式
            }
        };
 
        return Observable.create(onSubscribeInt);
    }
    // ---------- testMyMap ------------
}