package cancel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TestThreadPoolExecutor extends ThreadPoolExecutor {
   Set<Runnable> executions = Collections.synchronizedSet(new HashSet<Runnable>());

   public TestThreadPoolExecutor(LinkedBlockingQueue<Runnable> q) {      
      super(2, 5, 1, TimeUnit.MINUTES, q);      
   }

   public void execute(Runnable command) {
      if (executions.contains(command)) {
         System.out.println("Previous execution still running");
         return;
      }
      else {
         System.out.println("No previous execution");
      }
      super.execute(command);      
      executions.add(command);      
   }

   protected void afterExecute(Runnable r, Throwable t) {
      super.afterExecute(r, t);        
      executions.remove(r);
   }      
}