package annotation;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 测试类属性的注解
 * Created by wangqinghui on 2016/12/5.
 */
public class ClassFieldAnnotationExample {


    public void test( ) {

        AnnotationDescription annotationDescription = AnnotationDescription.Builder.ofType(MapToProperty.class)
                .define("property", "user_name")
                .build();

        Class<?> example = new ByteBuddy()
                .subclass(Object.class)
                .defineField("userName", String.class,Modifier.PUBLIC)
                .annotateField(annotationDescription)
                .make()
                .load(this.getClass().getClassLoader())
                .getLoaded();

        // 创建完class
        Field[] fields = example.getDeclaredFields();
        for (Field field : fields) {
            if(field.getAnnotations().length > 0){
                System.out.println( field.getName() + " " + field.getAnnotations()[0].toString() );
            }
        }

        for (Method method : example.getDeclaredMethods()) {
            if(method.getAnnotations().length> 0){
                System.out.println(method.getName() + " " + method.getAnnotations()[0].toString());
            }
        }


    }

    public static void main(String[] args) {
        new ClassFieldAnnotationExample().test();
    }
}
