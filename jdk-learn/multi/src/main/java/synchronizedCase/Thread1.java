package synchronizedCase;

public class Thread1 {

	 private byte[] lock = new byte[0]; // 特殊的instance变量	
	
	 public void method1() {
		 
         synchronized(this) {
        	 
        	  System.out.println("------"+Thread.currentThread().getName() +"获得锁------");
             
              int i = 3;  
              while( i-- > 0) {  
                   System.out.println("method1 >>>"+Thread.currentThread().getName() + " : " + i);  
                   try {  
                        Thread.sleep(500);  
                   } catch (InterruptedException e) { 
                	   e.printStackTrace();
                   }  
              }
              System.out.println("------"+Thread.currentThread().getName() +"释放锁------\n");
         }  
    }  
	 
	    
	 public synchronized void method2() {  
	        
	    	System.out.println("------"+Thread.currentThread().getName() +"获得锁------");
	    	
	   	    int i = 3;  
	        while( i-- > 0) {  
	             System.out.println("method2 >>>"+Thread.currentThread().getName() + " : " + i);  
	             try {  
	                  Thread.sleep(500);  
	             } catch (InterruptedException e) {
	           	  e.printStackTrace();
	             }  
	        }
	        System.out.println("------"+Thread.currentThread().getName() +"释放锁------\n");
	   } 
	 
	 
    public void method3() {  
        
    	 int i = 3;  
         while( i-- > 0) {  
              System.out.println("method3 >>>"+Thread.currentThread().getName() + " : " + i);  
              try {  
                   Thread.sleep(500);  
              } catch (InterruptedException e) {
            	  e.printStackTrace();
              }  
         }  
    } 
    

    
    public synchronized static void method4() {  
        
    	System.out.println("------"+Thread.currentThread().getName() +"获得Class锁------");
    	
   	    int i = 3;  
        while( i-- > 0) {  
             System.out.println("method4 >>>"+Thread.currentThread().getName() + " : " + i);  
             try {  
                  Thread.sleep(500);  
             } catch (InterruptedException e) {
           	  e.printStackTrace();
             }  
        }
        System.out.println("------"+Thread.currentThread().getName() +"释放Class锁------\n");
   } 
    
    public void method5() {  
        
    	synchronized(Thread1.class){
    		
    		System.out.println("------"+Thread.currentThread().getName() +"获得Class锁------");        	
       	    int i = 3;  
            while( i-- > 0) {  
                 System.out.println("method5 >>>"+Thread.currentThread().getName() + " : " + i);  
                 try {  
                      Thread.sleep(500);  
                 } catch (InterruptedException e) {
               	  e.printStackTrace();
                 }  
            }
            System.out.println("------"+Thread.currentThread().getName() +"释放Class锁------\n");	
    	}    	
   }
    
    private void method6() {			
		 
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
    
    public static void main(String[] args) {
    	
    	/**
    	 * 情况一：ThreadA和ThreadAAA对比
    	 * 当两个并发线程访问同一类的不同对象object中的这个synchronized(this)同步代码块时，不会构成同步。
    	 * 
    	 * 情况二：ThreadA和ThreadA对比
    	 * 当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到对象锁并执行。另一个线程必须等待当前线程执行完这个代码块释放锁以后才能执行该代码块。
    	 * 
    	 * 情况三：ThreadA和ThreadC对比
    	 * 当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
    	 * 
    	 * 情况四：ThreadA和ThreadB对比
    	 * 当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
    	 * 
    	 * 情况四：ThreadA和ThreadD对比
    	 * 一个用synchronized修饰的static函数method4()，一个用synchronized修饰的instance函数method3()，
    	 * 那么这个类的同一对象Obj在多线程中分别访问A和B两个方法时，不会构成同步，因为它们的锁都不一样。method3()的锁是Obj这个对象，而method4()的锁是Obj所属的那个Class
    	 *
    	 * 情况五：threadA和ThreadD和ThreadF
    	 * 三种类型的锁不构成同步
    	 *  
    	 **/
    	
    	/**
    	 * method1()和method2()函数的作用相同-->对象锁
    	 * method4()和method5()函数的作用相同-->类锁
    	 * method6()-->创建一个特殊的instance变量（它得是一个对象）来充当锁
    	 */
    	
         final Thread1 target = new Thread1();  
         final Thread1 target1 = new Thread1(); 
    
         Thread threadA = new Thread(  new Runnable() {  public void run() {  target.method1();  }  },"ThreadA");
         Thread threadAA = new Thread(  new Runnable() {  public void run() {  target.method1();  }  },"ThreadAA");
         Thread threadAAA = new Thread(  new Runnable() {  public void run() {  target1.method1();  }  },"ThreadAAA");  
         
         Thread threadB = new Thread(  new Runnable() {  public void run() { target.method2();   }  },"ThreadB");
         Thread threadC = new Thread(  new Runnable() {  public void run() { target.method3();   }  },"ThreadC");
         
         Thread threadD = new Thread(  new Runnable() {  public void run() { target.method4();   }  },"ThreadD"); 
         Thread threadE = new Thread(  new Runnable() {  public void run() { target.method5();   }  },"ThreadE");  
         
         Thread threadF = new Thread(  new Runnable() {  public void run() { target.method6();   }  },"ThreadF");  
                 
         threadA.start();
         threadAA.start();
         threadAAA.start();         
         /**
         threadB.start();
         
         threadC.start();
         
         threadD.start();
         
         threadE.start();
         
         threadF.start();**/     
         
    } 
} 


