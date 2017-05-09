//package canal.example;
//
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Event;
//import com.dianping.cat.message.Transaction;
//
//import java.util.Random;
//
///**
// * Created by wangqinghui on 2015/12/17.
// */
//public class CatTest {
//    public static void main(String[] args){
//        Transaction t = Cat.getProducer().newTransaction("trans", "TestCat.count");
//        try {
//
//
//            Cat.getProducer().logEvent("event.type", "event.name", Event.SUCCESS, "keyValuePairs");
//            t.setStatus(Transaction.SUCCESS);
//        } catch (Exception e) {
//            Cat.getProducer().logError(e);//用log4j记录系统异常，以便在Logview中看到此信息
//            t.setStatus(e);
//            //throw e;
//        } finally {
//            t.complete();
//        }
//
//        System.out.println("cat end");
//
//         t = Cat.newTransaction("TEST", "test.method");
//
//        Cat.logEvent("Method_test", "good");
//        Cat.logEvent("Method_event", "bad");
//
//        int nextInt = new Random().nextInt(3);
//
//        if (nextInt % 2 == 0) {
//            t.setStatus(Transaction.SUCCESS);
//        } else {
//            t.setStatus(String.valueOf(nextInt));
//        }
//
//        t.complete();
//    }
//}
