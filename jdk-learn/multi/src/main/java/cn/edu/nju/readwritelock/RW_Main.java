package cn.edu.nju.readwritelock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**     
 * 类名称：RW_Main    
 * 类描述：      
 *     
 */
public class RW_Main {

	/**    
	 * 方法作用：  
	 * @return      
	*/
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		RWDictionary dictionary = new RWDictionary();
		for (int i = 0; i < 26; i++) {
			dictionary.put(String.valueOf((char) i), String.valueOf((char) i));
		}
		for (int i = 0; i < 10; i++) {
			executorService.execute(new Client(dictionary, i));
		}

		executorService.shutdown();

	}

}
