package cancel;

import java.util.concurrent.LinkedBlockingQueue;

public class TestExecution implements Runnable {
   String key1;
   String key2;      
   static TestThreadPoolExecutor tpe = new TestThreadPoolExecutor(new LinkedBlockingQueue<Runnable>());

   public static void main(String[] args) {
      try {
         tpe.execute(new TestExecution("A", "A"));
         tpe.execute(new TestExecution("A", "A"));
         tpe.execute(new TestExecution("B", "B"));
         Thread.sleep(8000);
         tpe.execute(new TestExecution("B", "B"));
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public TestExecution(String key1, String key2) {
      this.key1 = key1;
      this.key2 = key2;      
   } public boolean equals(Object obj)
   {
      if (obj instanceof TestExecution)
      {
         TestExecution t = (TestExecution) obj;
         return (key1.equals(t.key1) && key2.equals(t.key2));
      }
      return false;
   }

   public int hashCode ()
   {
      return key1.hashCode()+key2.hashCode();
   }

   public void run() {
      try {
         System.out.println("Start processing "+key1+":"+key2);
         Thread.sleep(4000);
         System.out.println("Finish processing "+key1+":"+key2);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}