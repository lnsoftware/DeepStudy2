package treory;
/**
 *      �����������̷߳���ͬһ������object�е����synchronized(this)ͬ�������ʱ��
 *      һ��ʱ����ֻ����һ���̵߳õ�ִ�С���һ���̱߳���ȴ���ǰ�߳�ִ�������������Ժ����ִ�иô���顣
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
