package treory;
/**
 *  ��������ؼ����ǣ���һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ��
 *  �����̶߳�object����������synchronized(this)ͬ�������ķ��ʽ�������
 *
 */
public class Sync3 implements Runnable{

	private int number = 0;

	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+"---"+"����run��������һ��synchronized");
			for(int i=0;i<2;i++){
				number++;
			}
		}
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+"---"+"����run�������ڶ���synchronized");
			for(int i=0;i<2;i++){
				number++;
			}
		}
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+"---"+"����run������������synchronized");
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
