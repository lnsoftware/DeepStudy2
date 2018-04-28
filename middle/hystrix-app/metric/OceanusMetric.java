package oceanus.sdk.meta.metric;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangqinghui on 2018/3/13.
 */
public interface OceanusMetric {

    public void markFailed(Throwable throwable);


    /**
     * mark success
     */
    public void markSuccess();

    /**
     * 清空数据
     */
    public void reset() ;



    class Factory {

        private static final OceanusMetric EMPTY = new NoOpCircuitBreaker();
        private static final ConcurrentHashMap<String, OceanusMetric> circuitBreakers = new ConcurrentHashMap<>();


        public static OceanusMetric getInstance(String rhinoKey) {
            if (StringUtils.isBlank(rhinoKey)) {
                return EMPTY;
            }

            OceanusMetric circuitBreaker = circuitBreakers.get(rhinoKey);
            if (circuitBreaker != null) {
                return circuitBreaker;
            }

//            if (circuitBreakerProperties == null) {
//                circuitBreakerProperties = new DefaultCircuitBreakerProperties(rhinoKey);
//            }

            OceanusMetric newOne = new DefaultRhinoMetric(rhinoKey, null);
            OceanusMetric oldOne = circuitBreakers.putIfAbsent(rhinoKey, newOne);

            // 保证并发下都返回同一个对象，并只有一个线程能上报
            if (oldOne == null) {
//                RhinoManager.report(new RhinoEntity(rhinoKey, RhinoType.CircuitBreaker, useMode, (DefaultCircuitBreakerProperties) circuitBreakerProperties));
//                circuitBreakerProperties.addConfigChangedListener(null);
                oldOne = newOne;
            }
            return oldOne;
        }

        /**
         * return no operation circuitBreaker
         *
         * @return
         */
//        public static OceanusMetric getEmpty() {
//            return EMPTY;
//        }
    }


}

class NoOpCircuitBreaker implements OceanusMetric {

    @Override
    public void markFailed(Throwable throwable) {

    }

    @Override
    public void markSuccess() {

    }

    @Override
    public void reset() {

    }
}