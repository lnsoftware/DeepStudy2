import com.sun.btrace.annotations.BTrace;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TraceJInfo {
    static {
        println("java vm properties:===>");
        printVmArguments();
        println("System properties:===>");
        printProperties();
        println("OS properties:===>");
        printEnv();
        exit();
    }
}
