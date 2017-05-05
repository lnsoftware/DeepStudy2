package scripts;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class RegEvent {
    private static long count;

    @OnMethod(
            clazz = "/.*/",
            method = "execute",
            location = @Location(value = Kind.CALL, clazz = "/.*/", method = "sleep")
    )
    public static void traceExecute(@ProbeClassName String pcm, @ProbeMethodName String pmn,
                                    @TargetInstance Object instance, @TargetMethodOrField String method) {
        println("====== ");
        println(strcat("ProbeClassName: ", pcm));
        println(strcat("ProbeMethodName: ", pmn));
        println(strcat("TargetInstance: ", str(classOf(instance))));
        println(strcat("TargetMethodOrField : ", str(method)));
        count++;
    }

    @OnEvent
    public static void getCount() {
        println(strcat("count: ", str(count)));
    }

    @OnEvent("A")
    public static void getCountA() {
        println("==AAAA==== ");
        println(strcat("count: ", str(count)));
    }

    @OnEvent("B")
    public static void getCountB() {
        println("==BBB==== ");
        println(strcat("count: ", str(count)));
    }
}