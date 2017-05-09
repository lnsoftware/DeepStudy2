package treory;
/**
 *      当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，
 *      一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
 * @author bixiao.zy
 *
 */
public class Sync2 implements Runnable{

	private int number = 0;

	public void run() {
		synchronized (this) {
			for(int i=0;i<4;i++){
				number++;
				System.out.println(Thread.currentThread().getName()+"---"+this.number);
			}
		}
	}
	public static void main(String[] args) {
		Sync2 s = new Sync2();
		for(int i=0;i<5;i++){
			new Thread(s).start();
		}
	}
	
		
}
