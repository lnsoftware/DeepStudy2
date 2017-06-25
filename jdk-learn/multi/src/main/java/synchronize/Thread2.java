package synchronize;

public class Thread2 {

	class Inner {
		
		// �����instance����
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
		
		private void methodd33() {			
			 
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
	}

	private void method1 (Inner inner) {
		
         synchronized(inner) {
        	 
        	 System.out.println("\n------"+Thread.currentThread().getName() +"�����------");
        	 inner.method11();
        	 System.out.println("------"+Thread.currentThread().getName() +"�ͷ���------\n");
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
       	 
       	 System.out.println("\n------"+Thread.currentThread().getName() +"���Class��------");
       	 inner.method11();
       	 System.out.println("------"+Thread.currentThread().getName() +"�ͷ�Class��------\n");
        }         
   }
	private void method5 (Inner inner) {
		
		inner.method11();
	}
	
	public static void main(String[] args) {
		
		/** 
    	 * ��Thread1��ͬ���Ĺ��������������ͬ������
    	 * 
    	 *����壺threadA��ThreadC
    	 * ����һ����ȷ�Ķ�����Ϊ��ʱ���Ϳ�����method1()������д����
    	 * ����û����ȷ�Ķ�����Ϊ����ֻ������һ�δ���ͬ��ʱ�����Դ���һ�������instance������������һ���������䵱����
    	 *ע�⣺method1()/method2()
    	 *    method3()
    	 *    method4()    	   
    	 *    �������͵������ڶ��߳��в��ṹ��ͬ����
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