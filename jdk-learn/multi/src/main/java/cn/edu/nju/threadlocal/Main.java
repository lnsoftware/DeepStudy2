package cn.edu.nju.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**     
 * 类名称：Main    
 * 类描述：    主方法
 *     
 */
public class Main {


	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);//线程池

		LocalVar localVar = new LocalVar();
		for (int i = 0; i < 3; i++) {
			threadPool.execute(new Client(localVar));  //运行线程，放入线程池
		}

		threadPool.shutdown();  //关闭线程池

	}

}
