//package oceanus.sdk.meta.fetch;
//
//import com.google.common.collect.Lists;
//import com.netflix.hystrix.*;
//import oceanus.common.meta.entity.HystrixCommandInfo;
//import oceanus.common.meta.entity.HystrixGroupInfo;
//
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by wangqinghui on 2018/3/13.
// */
//public class HystrixInfoFetcher {
//
//    public HystrixInfoFetcher(){
//
//    }
//
//    public HystrixGroupInfo fetch(String groupKey,String commandKey) {
//        List<String> list = Lists.newArrayList();
//        list.add(commandKey);
//        return fetch(groupKey,list);
//    }
//
//    public HystrixGroupInfo fetch(String groupKey, List<String> commandKeys){
//        HystrixThreadPoolMetrics poolMetrics = HystrixThreadPoolMetrics.getInstance(HystrixThreadPoolKey
//                .Factory.asKey(groupKey));
//        HystrixGroupInfo info = new HystrixGroupInfo();
//        info.setGroupKey(groupKey);
//        info.setCurrentCorePoolSize(poolMetrics.getCurrentCorePoolSize().intValue());
//
//        // 获取Command信息
//        for (String commandKey : commandKeys) {
//            HystrixCommandMetrics commandMetrics = HystrixCommandMetrics.getInstance(HystrixCommandKey.Factory.asKey(commandKey));
//            HystrixCommandInfo commandInfo = new HystrixCommandInfo();
//            commandInfo.setGroupKey(groupKey);
//            commandInfo.setCommandKey(commandKey);
//            commandInfo.setErrorCount(commandMetrics.getHealthCounts().getErrorCount());
//            info.addCommandInfo(commandInfo);
//        }
//
//        return info;
//
//    }
//
////    public HystrixInfo fetchAll(){
////        Collection<HystrixThreadPoolMetrics> ins = HystrixThreadPoolMetrics.getInstances();
////
////        HystrixCommandMetrics.getInstances();
////    }
//
//}
