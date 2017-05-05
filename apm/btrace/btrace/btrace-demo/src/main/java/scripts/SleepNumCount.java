package scripts;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class SleepNumCount {
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
}