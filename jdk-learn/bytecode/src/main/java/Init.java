/**
 * Created by wangqinghui on 2018/3/5.
 */
public class Init {

    // 在jvm第一次加载class文件时调用，包括静态变量初始化语句和静态块的执行

    static {
        System.out.println("cinit");
    }

    // 在实例创建出来的时候调用
    {
        System.out.println("init");//实例化构造器，
    }

    public static void main(String[] args) {

        new Init();
        new Init();
        String a = "aaaaa";
        System.out.println(a.length());
    }

}
