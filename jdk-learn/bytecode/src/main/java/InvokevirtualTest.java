/**
 * invokevirtual：调用对象的一般方法。
 * Created by wangqinghui on 2018/2/12.
 */
public class InvokevirtualTest {

    public String getStr(String str){
        return "hello:"+str;
    }

    public String hello(String str){
//        INVOKEVIRTUAL InvokevirtualTest.getStr (Ljava/lang/String;)Ljava/lang/String;
        return getStr(str);
    }

    public static void main(String[] args) {

    }
}
