package inter;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

public class Interceptor {

    @RuntimeType
    public static Object intercept(@Origin String method, @AllArguments Object[] args) throws Throwable {
        System.out.println("I have intercepted a call");

        return "Hello";

    }

}