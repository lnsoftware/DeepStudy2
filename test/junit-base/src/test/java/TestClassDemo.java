import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

import java.util.List;

public class TestClassDemo{
    public static void main(String[] args) {


        TestClass klass = new TestClass(TestUnit.class);
        System.out.println(klass.getName() );
        List<FrameworkMethod> list = klass.getAnnotatedMethods(Test.class);
        for(FrameworkMethod fm :list){  
            try {  
                fm.invokeExplosively((TestUnit)klass.getJavaClass().newInstance(), new Object[0]) ;  
            }catch (Throwable e) {
                System.out.println(e.getMessage());
            }finally{
                System.out.println(fm.getName()+" invoked!" );
            }  
        }  
    }  
}  
