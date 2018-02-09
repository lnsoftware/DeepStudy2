import com.zhiyin.classloader.loaders.LocalPathClassLoader;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hg on 2017/6/24.
 */
public class LocalPathClassLoaderTest {

    String clazRootPath = "/Users/hg/Github/DeepStudy2/jdk-learn/classloader/clasz";
    String className = "com.zhiyin.classloader.TestLoadClass";

    @Test
    public void findClass() throws Exception {

        LocalPathClassLoader loader = new LocalPathClassLoader(clazRootPath);
        Class clazz = loader.loadClass(className);
        System.out.println(clazz.getClassLoader());
        assertEquals(loader,clazz.getClassLoader());
    }


    @Test
    public void findClass2() throws Exception {

        LocalPathClassLoader loader = new LocalPathClassLoader(clazRootPath);
        Class clazz = loader.loadClass(className);
        System.out.println(clazz.getClassLoader());
        assertEquals(loader,clazz.getClassLoader());
    }

}