package synchronizedCase;

public class Thread2 {

	class Inner {
		
		// 特殊的instance变量
		private byte[] lock = new byte[0]; 
		
		private void method11() {
			
			int i = 3;
			while (i-- > 0) {
				System.out.println("method1 >>>"+Thread.currentThread().getName() + " : " + i);  
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}

		private synchronized void methodd22() {			
		
			System.out.println("\n------"+Thread.currentThread().getName() +"获得锁------");
			int i = 3;			
			while (i-- > 0) {
				System.out.println("method2 >>>"+Thread.currentThread().getName() + " : " + i);  
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
			
			System.out.println("------"+Thread.currentThread().getName() +"释放锁------\n");
		}
		
		private void methodd33() {			
			 
			synchronized(lock) {
				
				System.out.println("\n------"+Thread.currentThread().getName() +"获得锁------");
				int i = 3;			
				while (i-- > 0) {
					System.out.println("method2 >>>"+Thread.currentThread().getName() + " : " + i);  
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}				
				}
				
				System.out.println("------"+Thread.currentThread().getName() +"释放锁------\n");			
			}
			
		}	
	}

	private void method1 (Inner inner) {
		
         synchronized(inner) {
        	 
        	 System.out.println("\n------"+Thread.currentThread().getName() +"获得锁------");
        	 inner.method11();
        	 System.out.println("------"+Thread.currentThread().getName() +"释放锁------\n");
         }         
    }

	private void method2 (Inner inner) {
		inner.methodd22();
	}
	
	private void method3 (Inner inner) {		
		inner.methodd33();
	}	

	private void method4 (Inner inner) {
		
        synchronized(Inner.class) {
       	 
       	 System.out.println("\n------"+Thread.currentThread().getName() +"获得Class锁------");
       	 inner.method11();
       	 System.out.println("------"+Thread.currentThread().getName() +"释放Class锁------\n");
        }         
   }
	private void method5 (Inner inner) {
		
		inner.method11();
	}
	
	public static void main(String[] args) {
		
		/** 
    	 * 类Thread1中同步的规则对其它对象锁同样适用
    	 * 
    	 *情况五：threadA和ThreadC
    	 * 当有一个明确的对象作为锁时，就可以像method1()那样编写程序，
    	 * 但当没有明确的对象作为锁，只是想让一段代码同步时，可以创建一个特殊的instance变量（它得是一个对象）来充当锁：
    	 *注意：method1()/method2()
    	 *    method3()
    	 *    method4()    	   
    	 *    三种类型的锁，在多线程中不会构成同步。
    	 **/ 
		
		final Thread2 target = new Thread2(); 
        final Inner inner = target.new Inner(); 
        
        Thread threadA = new Thread( new Runnable() {public void run() { target.method1(inner);} }, "ThreadA");   
        Thread threadAA = new Thread( new Runnable() {public void run() { target.method1(inner);} }, "ThreadAA");   
        Thread threadB = new Thread( new Runnable() {public void run() { target.method2(inner);} }, "ThreadB");
        Thread threadC = new Thread( new Runnable() {public void run() { target.method3(inner);} }, "ThreadC");
        Thread threadCC = new Thread( new Runnable() {public void run() { target.method3(inner);} }, "ThreadCC"); 
        Thread threadD = new Thread( new Runnable() {public void run() { target.method4(inner);} }, "ThreadD"); 
        Thread threadE = new Thread( new Runnable() {public void run() { target.method5(inner);} }, "ThreadE"); 
        
        threadA.start();        
        threadAA.start();        
        threadB.start();        
        threadC.start();
        threadCC.start();
        threadD.start();
        threadE.start();
	}
          
 }