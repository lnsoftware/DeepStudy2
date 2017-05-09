package treory;


/**
Java�е�ÿһ�����󶼿�����Ϊ����
����ͬ�����������ǵ�ǰʵ������
���ھ�̬ͬ�����������ǵ�ǰ�����Class����
����ͬ�������飬����Synchonized���������õĶ���
 *
 */
public class Sync4 implements Runnable{

	private static int number = 0;

	private synchronized static void addCount(){
		System.out.println(Thread.currentThread().getName()+"--"+"���뱻��ס��static����");
		number++;
	}
	private static void noSyncMethod(){
		System.out.println(Thread.currentThread().getName()+"--"+"���У�û�б����ľ�̬����");
	}
	public void run() {
		for(int i=0;i<2;i++){
			noSyncMethod();
			addCount();
		}
	}
	public static void main(String[] args) throws Exception{
		Sync4 s = new Sync4();
		for(int i=0;i<3;i++){
			new Thread(s).start();
		}
		Thread.sleep(3000);
		System.out.println(s.number);
	}
	
		
}
