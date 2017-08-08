//package source;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import rx.Observable;
//import rx.Subscriber;
//import rx.schedulers.Schedulers;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Slf4j
//public class StopEmit {
//
//    @Test
//    public void test(){
//        int[] datas = new int[]{2,5,3,1,7,4,8,3,2};
//        int sleepTime = 1000;
//        Observable<Integer> obs = Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                try{
//                    for(int data : datas){
//                        if (!subscriber.isUnsubscribed()) {
//                            subscriber.onNext(data);
//                        }else{
//                            return;
//                        }
//                        Thread.sleep(sleepTime);
//                    }
//                    if (!subscriber.isUnsubscribed()) {
//                        subscriber.onCompleted();
//                    }
//                }catch (Exception e){
//                    if (!subscriber.isUnsubscribed()) {
//                        subscriber.onError(e);
//                    }
//                }
//            }
//        });
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//      log.( "start time:" + sdf.format(new Date()));
//        obs.subscribeOn(Schedulers.newThread())
////                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.v(TAG, "onCompleted");
//
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.v(TAG, "onError:"+e.getMessage());
//                    }
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.v(TAG, "onNext:"+integer+" time:"+sdf.format(new Date()));
//                    }
//                });
//    }
//}
