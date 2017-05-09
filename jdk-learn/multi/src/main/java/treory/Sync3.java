package treory;
/**
 *  三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，
 *  其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞
 *
 */
public class Sync3 implements Runnable{

	private int number = 0;

	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+"---"+"进入run方法，第一个synchronized");
			for(int i=0;i<2;i++){
				number++;
			}
		}
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+"---"+"进入run方法，第二个synchronized");
			for(int i=0;i<2;i++){
				number++;
			}
		}
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+"---"+"进入run方法，第三个synchronized");
			for(int i=0;i<2;i++){
				number++;
			}
		}
	}
	public static void main(String[] args) {
		Sync3 s = new Sync3();
		for(int i=0;i<5;i++){
			new Thread(s).start();
		}
	}
	
		
}
