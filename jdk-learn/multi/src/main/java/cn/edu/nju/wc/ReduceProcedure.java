package cn.edu.nju.wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * reduce阶段的处理类
 * 
 * @author Administrator
 *
 */
public class ReduceProcedure implements Runnable {

	private CountDownLatch mapSignal; // map计数器减为0后，reduce线程才能运行
	private String srcfilePath = "D:/ResultFiles/"; // 文件路径
	private String resultFile = "D:/ResultFiles/result.txt"; // 结果文件
	private Map<String, Integer> r_Map = new HashMap<String, Integer>();

	public ReduceProcedure(CountDownLatch countDownLatch) {
		this.mapSignal = countDownLatch;
	}

	public ReduceProcedure(CountDownLatch mapSignal, String src, String dst) {
		this.mapSignal = mapSignal;
		this.srcfilePath = src;
		this.resultFile = dst;
	}

	/**
	 * 处理文件
	 */
	@SuppressWarnings("resource")
	public void dealFile() {
		try {
			File file = new File(srcfilePath);// Text文件
			File[] fileList = null;
			if (file.isDirectory()) {
				fileList = file.listFiles();
			}

			for (File f : fileList) {
				System.out.println(f.getAbsolutePath());
			}

			for (File f : fileList) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String s = null;
				while ((s = br.readLine()) != null && s.length() > 0) {
					String[] strs = s.split(":");
					String key = strs[0].trim();
					String value = strs[1].trim();

					if (r_Map.containsKey(key)) { // 如果包含该单词
						Integer v = r_Map.get(key) + 1;
						r_Map.put(key, v);

					} else { // 不包含，则put进去
						r_Map.put(key, 1);
					}
				}
			}
			String resultStr = Utils.convertMapToString(r_Map); // 拼接了字符串，写入结果文件
			Utils.writeFile(resultFile, resultStr); /// 写结果

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			mapSignal.await(); // 等待计数器减到0
			System.out.println("reduce thread" + Thread.currentThread().getName() + "is running ......");
			this.dealFile(); // 处理，计算出结果
			System.out.println("reduce run at end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
