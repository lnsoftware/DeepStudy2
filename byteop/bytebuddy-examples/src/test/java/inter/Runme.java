package inter;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.any;

public class Runme {

    public static void main(String[] args) {
        ByteBuddy bb = new ByteBuddy();

        Class<?> clz = bb
                .subclass(TestObject.class)
                .method(any())
                .intercept(MethodDelegation.to(Interceptor.class))
//                .visit( Ad)
                .make()
                .load(Object.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        try {
            Object test = clz.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
