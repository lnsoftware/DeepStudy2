package source;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by callin on 2015-11-05.
 */
//@Slf4j
public class LiftTest {

    @Test
    public void demo1() {
        Observable.from(
                new String[]{"1", "2", "3", "44", "55"}
        ).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                System.out.println("> " + s);
                return new Integer(s);
            }
        }).lift(new Observable.Operator<Integer, Integer>() {
            @Override
            public Subscriber<? super Integer> call(Subscriber<? super Integer> subscriber) {
                System.out.println("hehe");
//                return subscriber;
                final Subscriber<Integer> s = (Subscriber<Integer>) subscriber;
                return new Subscriber<Integer>(subscriber) {
                    @Override
                    public void onCompleted() {
                        if (s.isUnsubscribed()) return;
                        s.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (s.isUnsubscribed()) return;
                        s.onError(e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (s.isUnsubscribed()) return;
                        s.onNext(integer);
                        s.onNext(integer);
                    }
                };
            }
        }).map(new Func1<Integer, String>() {

            @Override
            public String call(Integer i) {
                System.out.println(">> " + i);
                return "[" + (i + 100) + "]";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void showStudentName() throws ExecutionException, InterruptedException {

        List<Student> students = new ArrayList<>();
        students.add(new Student("a"));
        students.add(new Student("b"));


        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("subscriber onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscriber onError:" + e.getMessage());
            }

            @Override
            public void onNext(String name) {
                System.out.println(name);
            }

        };

        Observable<Student> observable =

//                Observable.from(students);

                Observable.create(new Observable.OnSubscribe<Student>() {
                    @Override
                    public void call(Subscriber<? super Student> subscriber) {
                        for (Student student : students) {
                            subscriber.onNext(student);
                        }

                        if (true)
                            throw new RuntimeException("run error");
                        subscriber.onCompleted();
                    }

                });

        Observable<String> observable2 = observable
                .lift(new Observable.Operator<String, Student>() {
                    @Override
                    public Subscriber<? super Student> call(final Subscriber<? super String> subscriber) {
                        Subscriber<Student> newSubscriber = new Subscriber<Student>() {
                            @Override
                            public void onCompleted() {
                                System.out.println("lift onCompleted");
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println("lift error");
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(Student student) {
                                System.out.println("lift onNext");
                                subscriber.onNext(student.getName());
                            }
                        };
                        return newSubscriber;
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnTerminate");
                    }
                });


        Future<String> future = observable2.toBlocking().toFuture();
        future.get();
//                observable.subscribe(subscriber);
    }

    @Data
    @AllArgsConstructor
    static class Student {
        private String name;
    }
}