//package oceanus.sdk.meta.fetch;
//
//import lombok.Data;
//import oceanus.common.meta.entity.NodeMetricInfo;
//import oceanus.sdk.meta.metric.HealthCountCollector;
//import oceanus.sdk.meta.metric.HealthCountSummary;
//import oceanus.sdk.meta.metric.OceanusMetric;
//
///**
// * Created by wangqinghui on 2018/3/13.
// */
//@Data
//public class MetricsInfoFetcher {
//
//
//    public NodeMetricInfo fetch(String oceanusNodeId){
//        NodeMetricInfo info = new NodeMetricInfo();
//        info.setId(oceanusNodeId);
////        OceanusMetric.Factory.getInstance(oceanusNodeId);
//        HealthCountSummary tmp = HealthCountCollector.getInstance().getHealthCountSummary(oceanusNodeId);
//        info.setErrorCount(tmp.getErrorCount());
////        info.setQps(tmp.get);
//        info.setSuccessCount(tmp.getSuccessCount());
//        info.setTotalCount(tmp.getTotalCount());
//        info.setErrorPercentage(tmp.getErrorPercentage());
//        return info;
//    }
//
//}
