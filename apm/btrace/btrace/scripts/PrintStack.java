import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnTimer;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TraceMemory {
    //heapUsage()/nonHeapUsage() �C ��ӡ��/�Ƕ��ڴ���Ϣ������init��used��commit��max
    @OnTimer(4000)
    public static void printM() {
        //��ӡ�ڴ���Ϣ
        println("heap:");
        println(heapUsage());
        println("no-heap:");
        println(nonHeapUsage());
    }
}
