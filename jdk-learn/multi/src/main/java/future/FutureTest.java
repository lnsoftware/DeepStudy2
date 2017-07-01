package future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();

		Task task = new Task();
		Future<Integer> result = executor.submit(task);
		executor.shutdown();

		try {
			System.out.println("task运行结果" + result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("所有任务执行完毕");
	}

	static class Task implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			System.out.println("子线程正在进行计算");
			Thread.sleep(3000);
			int sum = 0;
			for (int i = 0; i < 100; i++) {
				sum += i;
			}
			return sum;
		}
	}

}
