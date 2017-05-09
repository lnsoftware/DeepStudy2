import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.internal.DefaultMessageManager;
import com.dianping.cat.message.spi.MessageTree;
import com.hg.cat.demo.CatHystrixConcurrencyStrategy;
import com.hg.cat.demo.CatHystrixConcurrencyStrategy2;
import com.hg.cat.demo.RemoteCatContext;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.HystrixPlugins;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hg on 2015/12/25.
 */

public class RemoteCallTest {




    // 通过Hystrix并发策略传递Cat参数
    @Test
    public void testRemoteCall() throws InterruptedException {

        HystrixPlugins.getInstance().registerConcurrencyStrategy(new CatHystrixConcurrencyStrategy2());

         ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1; i++) {
            executorService.submit(new Runnable() {
                @Override public void run() {
                    runTask("-ddd");
                }
            });
        }

//        runTask("-e");


        TimeUnit.SECONDS.sleep(5000);
    }


    public void runTask(String key){
        Cat.getManager().reset();
        Transaction root = Cat.newTransaction("Gateway"+key, "showUser");

        Cat.logEvent("AppCall","UserApp"+ key);
        Transaction parent = Cat.newTransaction("UserApp" + key, "call");
        assertEquals(null, Cat.getManager().getThreadLocalMessageTree().getMessageId());

        Cat.logEvent("HystrixCall","Provider");

        // 模拟远程调用
        HystrixCommand.Setter setter = HystrixCommand.Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("Provider"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("method1"))
            .andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter()
                    .withRequestLogEnabled(true).withExecutionTimeoutInMilliseconds(1000000)
            );
        HystrixCommand<String> command = new HystrixCommand<String>(setter) {
            @Override protected String run() throws Exception {
                System.out.println("hystrix run");
                Transaction child = Cat.newTransaction("Provider" + key, "method1");
                Thread.sleep(RandomUtils.nextInt(10, 20));
                Cat.logEvent("MethodCall", "method1");
                child.setStatus(Message.SUCCESS);
                String parId = Cat.getManager().getThreadLocalMessageTree().getParentMessageId();
                child.complete();
                return parId;
            }
        };
        command.execute();

        parent.setStatus(Message.SUCCESS);
        parent.complete();

        root.setStatus(Message.SUCCESS);
        root.complete();
    }

    @Test
    public void testRemoteCall2() throws InterruptedException {

//        for (int i = 0; i < 100; i++) {
//            loop();
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        try {
                            loop();
                        }catch (Exception e){
                            e.getMessage();
//                            e.printStackTrace();
//                            System.out.println(e.getMessage());
                        }
                    }
                }
            });
        }

        System.out.println("end");
        TimeUnit.SECONDS.sleep(500);

    }

    public void loop(){
        Cat.getManager().reset();

        Transaction parent = Cat.newTransaction("UserApp", "showUser");
//        assertEquals(null, Cat.getManager().getThreadLocalMessageTree().getMessageId());

        // 设置RemoteContext
        Cat.Context context = new RemoteCatContext();
        Cat.logRemoteCallClient(context);
//        if(curMesageId == null){
//            System.out.println("ssssss");
//        }
        String aa = Cat.getManager().getThreadLocalMessageTree().getMessageId();
        MessageTree tree1 = Cat.getManager().getThreadLocalMessageTree();

        // 模拟远程调用
        String childParMsgId = "";
        try {
            childParMsgId = callRemote(context);
        }catch (Exception e){
//            e.printStackTrace();
        }
        String curMesageId = Cat.getManager().getThreadLocalMessageTree().getMessageId();

//        if(childParMsgId.equals("")){
//            return;
//        }

//        if(childParMsgId.equals(curMesageId)){
//            System.out.println(JSON.toJSONString(context));
//        }

//        if(!childParMsgId.equals(curMesageId)){
//            MessageTree tree2 = Cat.getManager().getThreadLocalMessageTree();
//            System.out.println(childParMsgId + " " + curMesageId + JSON.toJSONString(Cat.getManager().getThreadLocalMessageTree()));
//        }

        parent.setStatus(Message.SUCCESS);
        parent.complete();
        System.out.println("loop end");
    }

    public String callRemote(Cat.Context context) {
        HystrixCommand.Setter setter = HystrixCommand.Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("user"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("show"));
        HystrixCommand<String> command = new HystrixCommand<String>(setter) {
            @Override
            protected String run() throws Exception {
                Cat.getManager().reset();
                Cat.logRemoteCallServer(context);
                Transaction child = Cat.newTransaction("UserService", "getUser");
                try {
                    Cat.logEvent("UserMethodCall", "getUser");
                    if(true){
                        throw new RuntimeException("");
                    }

                    String parId = Cat.getManager().getThreadLocalMessageTree().getParentMessageId();
                    return parId;

                }finally {
                    child.setStatus(Message.SUCCESS);
                    child.complete();
                }
            }
            @Override
            public String getFallback(){

                Transaction child = Cat.newTransaction("UserService-Fallback", "getUser");
                Cat.logEvent("UserFallbackMethod", "getUser");
                child.setStatus(Message.SUCCESS);
                String parId = Cat.getManager().getThreadLocalMessageTree().getMessageId();

                child.complete();
                return parId;
            }
        };
       return command.execute();
    }

}
