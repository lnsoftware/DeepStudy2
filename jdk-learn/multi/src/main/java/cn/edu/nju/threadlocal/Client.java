package cn.edu.nju.threadlocal;

/**     
 * 类名称：Client    
 * 类描述：      客户端类
 *     
 */
public class Client implements Runnable {

	private LocalVar localVar; //维护一个threadlocal的类

	public Client(LocalVar localVar) {
		this.localVar = localVar;
	}

	@Override
	public void run() {

		for (int i = 0; i < 3; i++) { //测试是否每个线程单独享有变量，即threadlocal有没有维护私有变量
			System.out.println(Thread.currentThread() + "] ===>localVar:"
					+ localVar.getNextNum());
		}

	}

}
