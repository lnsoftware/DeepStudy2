package cn.edu.nju.threadlocal;

/**     
 * 类名称：LocalVar    
 * 类描述：    threadlocal的测试类

 *     
 */
public class LocalVar {

	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() { //静态类，重写了init方法
		public Integer initialValue() {
			return 0;
		}

	};

	// ②获取下一个序列值   
	public int getNextNum() {
		threadLocal.set(threadLocal.get() + 1);
		return threadLocal.get();
	}

	public int getNum() {
		return threadLocal.get();
	}

}
