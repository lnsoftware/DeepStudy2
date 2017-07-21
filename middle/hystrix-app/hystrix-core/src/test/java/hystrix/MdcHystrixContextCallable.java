package hystrix;

import java.util.Map;
import java.util.concurrent.Callable;
import org.slf4j.MDC;

public class MdcHystrixContextCallable<K> implements Callable<K> {

    private final Callable<K> actual;
    private final Map parentMDC;

    public MdcHystrixContextCallable(Callable<K> actual) {
        this.actual = actual;
        this.parentMDC = MDC.getCopyOfContextMap();
    }

    @Override
    public K call() throws Exception {
        Map childMDC = MDC.getCopyOfContextMap();
        try {
            if (parentMDC != null && parentMDC.size() > 0) {
                MDC.setContextMap(parentMDC);
            }
            return actual.call();
        } finally {
//            if( childMDC != null && childMDC.size() > 0){
//                MDC.setContextMap(childMDC);
//            }
            MDC.clear();
        }
    }
}