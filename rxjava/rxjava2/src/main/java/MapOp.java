import org.junit.Test;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by wangqinghui on 2016/10/18.
 */
public class MapOp {


    // map变换
    @Test
    public void op1(){
        Observable.just("Hello, world!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " -Dan";
                    }
                })
                .subscribe(s -> System.out.println(s));

        // lambda
        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .subscribe(s -> System.out.println(s));
    }


    // map 返回类型与observable不一致
    @Test
    public void op3() {

        Observable.just("Hello, world!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(i -> System.out.println(Integer.toString(i)));


        // lambda
        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .subscribe(i -> System.out.println(Integer.toString(i)));
    }
}
