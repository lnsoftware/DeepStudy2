/**
 * 专门用来调用父类方法、私有方法和初始化方法；
 * Created by wangqinghui on 2018/2/12.
 */
public class InvokespecialTest {

    private String getStr(String str){
        return "hello:"+str;
    }

    public String hello(String str){
        return getStr(str);
    }

    public static void main(String[] args) {

    }
}
