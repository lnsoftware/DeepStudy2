package com.hg.btrace.demo.demo1;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class BtraceScript {

    @OnMethod(clazz = "/com\\.hg\\..*/", method = "/.*/", location = @Location(Kind.ENTRY))
    public static void logArgs(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        printArray(args);
    }

    @OnMethod(clazz = "/com\\.hg\\..*/", method = "/.*/", location = @Location(Kind.RETURN))
    public static void methodEnd(@ProbeClassName String pcn, @ProbeMethodName String pmn, @Return Object i,
                                 @Duration long duration) {
        String method = strcat(strcat(pcn, "."), pmn);
        String returnValue = strcat(", Return value:", str(i));
        String cost = strcat(", Call cost:", str(duration));
        println(strcat(strcat(method, returnValue), cost));
    }
}
