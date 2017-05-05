
import com.google.common.collect.Lists;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by hg on 2016/8/11.
 */
public class Demo2 {


    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            System.out.println(s);
        }
    };


    Action1<String> onNextAction2 = new Action1<String>() {
        @Override
        public void call(String s) {
            System.out.println(s);
        }
    };


    @Test
    public void test1() {
        Observable.just("Hello, world!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " -Dan";
                    }
                })
                .subscribe(
                        new Action1<String>() {
                            @Override
                            public void call(String s) {
                                System.out.println(s);
                            }
                        }
                );

    }


    @Test
    public void test2() {

        Observable.just("Hello, world!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, String>() {
                         @Override
                         public String call(Integer s) {
                             return Integer.toString(s);
                         }
                     }
                )
                .subscribe(onNextAction);


    }


    @Test
    public void test3() {

        List<String> urls = Lists.newArrayList();
        urls.add("hello1");
        Observable.just(urls).flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        })
                .subscribe(
                        new Action1<String>() {
                            @Override
                            public void call(String s) {
                                System.out.println(s);
                            }
                        }
                );
    }


    @Test
    public void test4() {

        List<String> urls = Lists.newArrayList();
        urls.add("hello1");
        urls.add("sfd ");
        Observable.just(urls).flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s+"sss");
            }
        })
                .subscribe(
                        new Action1<String>() {
                            @Override
                            public void call(String s) {
                                System.out.println(s);
                            }
                        }
                );
    }


}
