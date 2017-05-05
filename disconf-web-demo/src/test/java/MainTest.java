


import com.hg.disconf.webdemo.GetDisconfVal;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangqinghui on 2016/5/31.
 */
public class MainTest {

    private static String[] fn = null;

    // 初始化spring文档
    private static void contextInitialized() {
        fn = new String[] {"disconf.xml"};
    }


    public static void main(String[] args){
        contextInitialized();
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(fn);


        GetDisconfVal getDisconfVal2 = ctx.getBean("getDisconfVal", GetDisconfVal.class);
        System.out.println( getDisconfVal2.getVal() );


    }
}
