package synchronize;

public class Thread1 {

	 private byte[] lock = new byte[0]; // �����instance����	
	
	 public void method1() {
		 
         synchronized(this) {
        	 
        	  System.out.println("------"+Thread.currentThread().getName() +"�����------");
             
              int i = 3;  
              while( i-- > 0) {  
                   System.out.println("method1 >>>"+Thread.currentThread().getName() + " : " + i);  
                   try {  
                        Thread.sleep(500);  
                   } catch (InterruptedException e) { 
                	   e.printStackTrace();
                   }  
              }
              System.out.println("------"+Thread.currentThread().getName() +"�ͷ���------\n");
         }  
    }  
	 
	    
	 public synchronized void method2() {  
	        
	    	System.out.println("------"+Thread.currentThread().getName() +"�����------");
	    	
	   	    int i = 3;  
	        while( i-- > 0) {  
	             System.out.println("method2 >>>"+Thread.currentThread().getName() + " : " + i);  
	             try {  
	                  Thread.sleep(500);  
	             } catch (InterruptedException e) {
	           	  e.printStackTrace();
	             }  
	        }
	        System.out.println("------"+Thread.currentThread().getName() +"�ͷ���------\n");
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
        
    	System.out.println("------"+Thread.currentThread().getName() +"���Class��------");
    	
   	    int i = 3;  
        while( i-- > 0) {  
             System.out.println("method4 >>>"+Thread.currentThread().getName() + " : " + i);  
             try {  
                  Thread.sleep(500);  
             } catch (InterruptedException e) {
           	  e.printStackTrace();
             }  
        }
        System.out.println("------"+Thread.currentThread().getName() +"�ͷ�Class��------\n");
   } 
    
    public void method5() {  
        
    	synchronized(Thread1.class){
    		
    		System.out.println("------"+Thread.currentThread().getName() +"���Class��------");        	
       	    int i = 3;  
            while( i-- > 0) {  
                 System.out.println("method5 >>>"+Thread.currentThread().getName() + " : " + i);  
                 try {  
                      Thread.sleep(500);  
                 } catch (InterruptedException e) {
               	  e.printStackTrace();
                 }  
            }
            System.out.println("------"+Thread.currentThread().getName() +"�ͷ�Class��------\n");	
    	}    	
   }
    
    private void method6() {			
		 
		synchronized(lock) {
			
			System.out.println("\n------"+Thread.currentThread().getName() +"�����------");
			int i = 3;			
			while (i-- > 0) {
				System.out.println("method2 >>>"+Thread.currentThread().getName() + " : " + i);  
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
			
			System.out.println("------"+Thread.currentThread().getName() +"�ͷ���------\n");			
		}		
	}	
    
    public static void main(String[] args) {
    	
    	/**
    	 * ���һ��ThreadA��ThreadAAA�Ա�
    	 * �����������̷߳���ͬһ��Ĳ�ͬ����object�е����synchronized(this)ͬ�������ʱ�����ṹ��ͬ����
    	 * 
    	 * �������ThreadA��ThreadA�Ա�
    	 * �����������̷߳���ͬһ������object�е����synchronized(this)ͬ�������ʱ��һ��ʱ����ֻ����һ���̵߳õ���������ִ�С���һ���̱߳���ȴ���ǰ�߳�ִ�������������ͷ����Ժ����ִ�иô���顣
    	 * 
    	 * �������ThreadA��ThreadC�Ա�
    	 * ��һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ����һ���߳���Ȼ���Է��ʸ�object�еķ�synchronized(this)ͬ������顣
    	 * 
    	 * ����ģ�ThreadA��ThreadB�Ա�
    	 * ��һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�������̶߳�object����������synchronized(this)ͬ�������ķ��ʽ���������
    	 * 
    	 * ����ģ�ThreadA��ThreadD�Ա�
    	 * һ����synchronized���ε�static����method4()��һ����synchronized���ε�instance����method3()��
    	 * ��ô������ͬһ����Obj�ڶ��߳��зֱ����A��B��������ʱ�����ṹ��ͬ������Ϊ���ǵ�������һ����method3()������Obj������󣬶�method4()������Obj�������Ǹ�Class
    	 *
    	 * ����壺threadA��ThreadD��ThreadF
    	 * �������͵���������ͬ��
    	 *  
    	 **/
    	
    	/**
    	 * method1()��method2()������������ͬ-->������
    	 * method4()��method5()������������ͬ-->����
    	 * method6()-->����һ�������instance������������һ���������䵱��
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


