package annotation;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.implementation.FixedValue;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by wangqinghui on 2016/12/5.
 */
public class ClassMethodAnnotationExample {

    // 测试方法注解
    @Test
    public void test() {

        AnnotationDescription routeDe = AnnotationDescription.Builder.ofType(Route.class).define("pattern","a/b").build();

        Class<?> example = new ByteBuddy()
                .subclass(Object.class)

                .defineMethod("run", String.class, Modifier.PUBLIC)
                .intercept(FixedValue.value("Hello World!"))
                .annotateMethod(routeDe)

                .make()
                .load( this.getClass().getClassLoader() )
                .getLoaded();

        for (Method method : example.getDeclaredMethods()) {
            if(method.getAnnotations().length> 0){
                System.out.println(method.getName() + " " + method.getAnnotations()[0].toString());
            }
        }

    }


}
