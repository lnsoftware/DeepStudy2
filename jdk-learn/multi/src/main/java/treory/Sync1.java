package treory;
/**
 *      Ȼ������һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ��
 *      ��һ���߳���Ȼ���Է��ʸ�object�еķ�synchronized(this)ͬ������顣
 * @author bixiao.zy
 *
 */
public class Sync1 implements Runnable{

	private int number = 0;

	public void run() {
		System.out.println(Thread.currentThread().getName()+"---"+"��ʼ����run����������û�н���sync������");
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
