package cn.edu.nju.readwritelock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**     
 * 类名称：RWDictionary    
 * 类描述：    用读写锁来实现对map的添加元素和修改元素
 *     
 */
public class RWDictionary {
	private final Map<String, String> m = new TreeMap<String, String>();
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();   //读锁
	private final Lock w = rwl.writeLock();  //写锁

	/**    
	 * 方法作用：  
	 * @return      
	*/
	public String get(String key) {
		r.lock();
		try {
			return m.get(key);
		} finally {
			r.unlock();
		}
	}

	/**    
	 * 方法作用：  
	 * @return      
	*/
	public String[] allKeys() {
		r.lock();
		try {
			return (String[]) m.keySet().toArray();
		} finally {
			r.unlock();
		}
	}

	/**    
	 * 方法作用：  
	 * @return      
	*/
	public String put(String key, String value) {
		w.lock();
		try {
			return m.put(key, value);
		} finally {
			w.unlock();
		}
	}

	/**    
	 * 方法作用：  
	 * @return      
	*/
	public void clear() {
		w.lock();
		try {
			m.clear();
		} finally {
			w.unlock();
		}
	}
}