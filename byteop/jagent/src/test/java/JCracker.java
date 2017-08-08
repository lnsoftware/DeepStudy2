import java.io.IOException;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class JCracker {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        tt();
      c2();
      c3();
    }

    public static void tt() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        // 解压jar包后的路径
        pool.insertClassPath("/Users/hg/Desktop/tmp");

        CtClass cc = pool.get("com.ejt.framework.e.c");

        CtClass[] param = new CtClass[3];
        param[0] = pool.get("java.lang.String");
        param[1] = pool.get("java.lang.String");
        param[2] = pool.get("java.lang.String");

        assert cc != null;
        CtMethod method = cc.getDeclaredMethod("a", param);

        assert method != null;
        method.setBody("{return 1;}");

        cc.writeFile("/Users/hg/Desktop/tmp");
    }


    public static void c2() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        // 解压jar包后的路径
        pool.insertClassPath("/Users/hg/Desktop/tmp");

        CtClass cc = pool.get("com.jprofiler.frontend.FrontendApplication");

        CtClass[] param = new CtClass[3];

        assert cc != null;
        CtMethod method = cc.getDeclaredMethod("p");

        assert method != null;
        method.setBody("{return true;}");

        cc.writeFile("/Users/hg/Desktop/tmp");
    }

    public static void c3() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        // 解压jar包后的路径
        pool.insertClassPath("/Users/hg/Desktop/tmp");

        CtClass cc = pool.get("com.jprofiler.frontend.FrontendApplication");

        CtClass[] param = new CtClass[3];

        assert cc != null;
        CtMethod method = cc.getDeclaredMethod("h");

        assert method != null;
        method.setBody("{return true;}");

        cc.writeFile("/Users/hg/Desktop/tmp");
    }
}