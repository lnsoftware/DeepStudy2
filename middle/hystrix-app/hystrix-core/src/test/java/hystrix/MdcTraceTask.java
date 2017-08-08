package hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.MDC;

import static org.assertj.core.api.Assertions.assertThat;

public class MdcTraceTask implements Runnable {

    public String tid;

    public MdcTraceTask(String tid) {
        this.tid = tid;
    }

    @Override
    public void run() {
        boolean trace = false;
        if (Integer.valueOf(tid) % 2 == 0) {
            MDC.put("traceId", tid);
            trace = true;
        }
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            MdcHystrixCommand d = new MdcHystrixCommand("mdc");
            String retTid = d.execute();
            if (trace) {
                assertThat(tid).isEqualTo(retTid);
            } else {
                assertThat(tid).isNotEmpty();
                assertThat(retTid).isNullOrEmpty();
            }

          //  System.out.println(Thread.currentThread().getName() + " " + tid + " " + retTid);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        context.shutdown();
    }
}
