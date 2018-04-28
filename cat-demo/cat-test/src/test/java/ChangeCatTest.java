//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Message;
//import com.dianping.cat.message.Transaction;
//import com.dianping.cat.message.internal.DefaultMessageManager;
//import com.hg.cat.demo.RemoteCatContext;
//import com.netflix.hystrix.HystrixCommand;
//import com.netflix.hystrix.HystrixCommandGroupKey;
//import com.netflix.hystrix.HystrixCommandKey;
//import com.netflix.hystrix.HystrixCommandProperties;
//import com.netflix.hystrix.HystrixThreadPoolProperties;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import org.apache.commons.lang3.RandomUtils;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by hg on 2017/6/7.
// */
//public class ChangeCatTest {
//
//
//    // 更改Cat代码，获取Manager的ThreadLocal变量
//    @Test
//    public void testRemoteCall22() throws InterruptedException {
//
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//
//        for (int i = 0; i < 50; i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    for (int j = 0; j < 100; j++) {
//                        runTask2("-888");
//                    }
//                }
//            });
//        }
//
//        TimeUnit.SECONDS.sleep(500);
//    }
//
//    public void runTask2(String key){
//        Cat.getManager().reset();
//        Transaction root = Cat.newTransaction("Gateway"+key, "showUser");
//
//        Cat.logEvent("AppCall","UserApp"+ key);
//        Transaction parent = Cat.newTransaction("UserApp" + key, "call");
//        assertEquals(null, Cat.getManager().getThreadLocalMessageTree().getMessageId());
//
//        Cat.logEvent("HystrixCall","Provider");
//
//        DefaultMessageManager.Context cont = Cat.getManager().getContext();
//
//        // 模拟远程调用
//        HystrixCommand.Setter setter = HystrixCommand.Setter
//            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("Provider"))
//            .andCommandKey(HystrixCommandKey.Factory.asKey("method1"))
//            .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(50))
//            .andCommandPropertiesDefaults(
//                HystrixCommandProperties.Setter()
//                    .withRequestLogEnabled(true).withExecutionTimeoutInMilliseconds(1000000)
//            );
//        HystrixCommand<String> command = new HystrixCommand<String>(setter) {
//            @Override
//            protected String run() throws Exception {
//                ( (DefaultMessageManager) Cat.getManager()).m_context.set(cont);
//
//                Transaction child = Cat.newTransaction("RemoteProviderRun" + key, "method1");
//                try {
//                    Thread.sleep(RandomUtils.nextInt(10, 20));
//                    Cat.logEvent("RemoteMethodCall", "method1");
//
////                    if (true) {
////                        throw new RuntimeException("e");
////                    }
//                    callRemote();
//                    String parId = Cat.getManager().getThreadLocalMessageTree().getParentMessageId();
//                    return parId + " " + Cat.getCurrentMessageId();
//                }finally {
//                    child.setStatus(Message.SUCCESS);
//                    child.complete();
//                    ((DefaultMessageManager) Cat.getManager()).m_context.set(null);
//                }
//            }
//            @Override
//            public String getFallback(){
////                ( (DefaultMessageManager) Cat.getManager()).m_context.set(cont);
//                Transaction child = Cat.newTransaction("RemoteProviderFallback" + key, "method1" + "-HystrixFail");
//                try {
//                    Cat.logEvent("RemoteFallbackMethodCall", "method1" + "-HystrixFail");
//                }finally {
//                    child.setStatus(Message.SUCCESS);
//                    String parId = Cat.getManager().getThreadLocalMessageTree().getParentMessageId();
//                    child.complete();
//                    ((DefaultMessageManager) Cat.getManager()).m_context.set(null);
//                }
//
////                System.out.println( this.getFailedExecutionException());
////                System.out.println(this.isResponseThreadPoolRejected());
//                return "";
//            }
//
//        };
//
//        String getParId = "";
//        getParId = command.execute();
////        if( getParId.equals(Cat.getCurrentMessageId())){
////            System.out.println("ok");
////        }
//        System.out.println(getParId + " " + Cat.getCurrentMessageId());
////        assertEquals(getParId,Cat.getCurrentMessageId());
//
//        parent.setStatus(Message.SUCCESS);
//        parent.complete();
//
//        Cat.logEvent("Gateway" +key,"End");
//        root.setStatus(Message.SUCCESS);
//        root.complete();
//    }
//
//
//    public void callRemote(){
//        // 设置RemoteContext
//        Cat.Context context = new RemoteCatContext();
//        Cat.logRemoteCallClient(context);
////        context.equals()
//        // 模拟远程调用
//        String childParMsgId = callRemote(context);
//
//    }
//
//
//    public String callRemote(Cat.Context context) {
//        HystrixCommand.Setter setter = HystrixCommand.Setter
//            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("user"))
//            .andCommandKey(HystrixCommandKey.Factory.asKey("show"));
//        HystrixCommand<String> command = new HystrixCommand<String>(setter) {
//            @Override protected String run() throws Exception {
//                Cat.logRemoteCallServer(context);
//                Transaction child = Cat.newTransaction("UserService", "getUser");
////                Thread.sleep(RandomUtils.nextInt(10, 20));
//                try {
//
//                    Cat.logEvent("UserMethodCall", "getUser");
//                    if(true){
//                        throw new RuntimeException("eee");
//                    }
//                    String parId = Cat.getManager().getThreadLocalMessageTree().getParentMessageId();
//                    System.out.println(parId);
//                   return parId;
//                }finally {
//                    child.setStatus(Message.SUCCESS);
//                    child.complete();
//                }
////                ((DefaultMessageManager) Cat.getManager()).m_context.set(null);
//            }
//            @Override
//            public String getFallback(){
//                Cat.logRemoteCallServer(context);
//                Transaction child = Cat.newTransaction("UserService-Fallback", "getUser");
//                try {
//                    Cat.logEvent("UserMethodCall", "getUser");
//                }finally {
//                    child.setStatus(Message.SUCCESS);
//                    child.complete();
//                }
////                ((DefaultMessageManager) Cat.getManager()).m_context.set(null);
//
//                return "";
//            }
//        };
//        return command.execute();
//    }
//
//}
