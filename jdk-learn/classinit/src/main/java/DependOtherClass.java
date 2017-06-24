import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hg on 2017/6/24.
 */
public class DependOtherClass {

    // 不会触发类加载
    static Logger logger = null;

    // 不会触发类加载
    private Logger fieldLogNull = null;

    // 只有在初始化DependOtherClass时，才会触发类加载
    private Logger fieldLog = LoggerFactory.getLogger("f");

    public static void main(String[] args) {

//        new DependOtherClass();

        System.out.println("Depend Other");

        System.out.println("set log null");
        Logger mainNullLog = null;

        System.out.println("new main log");
        Logger mainNewLog = LoggerFactory.getLogger("ss");
    }
}
