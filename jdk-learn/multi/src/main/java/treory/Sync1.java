package treory;
/**
 *      然而，当一个线程访问object的一个synchronized(this)同步代码块时，
 *      另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
 * @author bixiao.zy
 *
 */
public class Sync1 implements Runnable{

	private int number = 0;

	public void run() {
		System.out.println(Thread.currentThread().getName()+"---"+"开始进入run方法，但是没有进入sync方法块");
		synchronized (this) {
			for(int i=0;i<4;i++){
				number++;
				System.out.println(Thread.currentThread().getName()+"---"+this.number);
			}
		}
	}
	public static void main(String[] args) {
		Sync1 s = new Sync1();
		for(int i=0;i<5;i++){
			new Thread(s).start();
		}
	}
	
		
}
