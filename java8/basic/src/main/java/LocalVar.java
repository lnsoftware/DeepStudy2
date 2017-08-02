import org.junit.Test;

/**
 * Created by hg on 2017/7/26.
 */
public class LocalVar {

    @Test
    public void test(){

        int num = 22;
        Runnable runnable = () -> System.out.println(num);
//        num = 88;

        new Thread(runnable).start();
    }
}
