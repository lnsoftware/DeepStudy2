import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by wangqinghui on 2016/11/17.
 */
public class Demo {
    public static void main(String[] args) {

        System.out.println(System.getProperty("java.io.tmpdir"));

        // 使用默认配置文件创建CacheManager
        CacheManager manager = CacheManager.create();
// 通过manager可以生成指定名称的Cache对象
        Cache cache = manager.getCache("demoCache");
// 使用manager移除指定名称的Cache对象
//        manager.removeCache("demoCache");
//        可以通过调用manager.removalAll()来移除所有的Cache。通过调用manager的shutdown()方法可以关闭CacheManager。
//        有了Cache对象之后就可以进行一些基本的Cache操作，例如：
//往cache中添加元素
        for (int i = 0; i < 100000 ; i++) {
            Element element = new Element("key"+i, "value");
            cache.put(element);
        }
        Element element = new Element("key", "value");
        cache.put(element);
//从cache中取回元素
        element = cache.get("key");

        System.out.println(element.getObjectValue());
//从Cache中移除一个元素
//        cache.remove("key");

    }
}
