package cn.edu.nju.readwritelock;

/**     
 * 类名称：Client    
 * 类描述：    
 
 *     
 */
public class Client implements Runnable {

	private RWDictionary rw;
	private int i;

	public Client(RWDictionary rw, int i) {
		this.rw = rw;
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("client " + i + "get the value is" + rw.get("a"));
		String str = "" + i;
		System.out.println("client" + i + "set the value " + i
				+ rw.put("a", str));

	}

}
