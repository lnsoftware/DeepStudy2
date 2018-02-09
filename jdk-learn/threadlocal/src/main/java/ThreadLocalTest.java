
public class ThreadLocalTest {

	public static void main(String[] args){
		test1();
	}
	private static void test1(){
		Thread t1=new Thread(){

			@Override
			public void run() {
				System.out.println(this.getName()+"开始值:"+ThreadUtil.getName());
				ThreadUtil.setName("线程0");
				sleepTime(1000*10);
				System.out.println(this.getName()+"设定后:"+ThreadUtil.getName());
			}
			
		};
		Thread t2=new Thread(){

			@Override
			public void run() {
				sleepTime(1000*5);
				System.out.println(this.getName()+"开始值:"+ThreadUtil.getName());
				ThreadUtil.setName("线程1");
				System.out.println(this.getName()+"设定后:"+ThreadUtil.getName());
			}
			
		};
		t1.start();
		t2.start();
	}
	
	private static void sleepTime(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}

 class ThreadUtil {

	private static ThreadLocal<String> nameLocal=new ThreadLocal<String>();

	public static String getName(){
		return nameLocal.get();
	}

	public static void setName(String name){
		nameLocal.set(name);
	}
}