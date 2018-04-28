/**
 * invokestatic：调用静态方法；
 * Created by wangqinghui on 2018/2/12.
 */
public class InvokestaticTest {

    public static void main(String[] args) {
//        INVOKESTATIC InvokestaticTest.hello (Ljava/lang/String;)Ljava/lang/String;
        hello("hg");
    }

    public static String hello(String name){
        return "hello:"+name;
    }
}
