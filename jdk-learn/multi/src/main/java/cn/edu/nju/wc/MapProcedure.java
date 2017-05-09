package cn.edu.nju.wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * map阶段的处理类
 * 
 * @author Administrator
 *
 */
public class MapProcedure implements Runnable {

	private CountDownLatch mapSignal; // 所有map完成之后，reduce才能开始执行哦
	private CountDownLatch mainSignal; // 主函数运行分割文件后，运行map函数运行
	private String srcfilePath; // 源文件路径
	private String desFilePath; // 目的文件路径
	private Map<String, Integer> m_Map = new HashMap<String, Integer>(); // 存放word的map

	/**
	 * 构造函数
	 * 
	 * @param countDownLatch
	 */
	public MapProcedure(CountDownLatch countDownLatch) {
		this.mapSignal = countDownLatch;
	}

	/**
	 * 重载构造函数
	 * 
	 * @param countDownLatch
	 * @param src
	 * @param dst
	 */
	public MapProcedure(CountDownLatch mainCountDownLatch, CountDownLatch mapCountDownLatch, String src, String dst) {
		this.mainSignal = mainCountDownLatch;
		this.mapSignal = mapCountDownLatch;
		this.srcfilePath = src;
		this.desFilePath = dst;
	}

	/**
	 * 读取文件，分割，存入map中
	 */
	@SuppressWarnings("resource")
	public void dealFile() {
		try {
			File file = new File(srcfilePath);// Text文件
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			int fileLines = Utils.getFileLineNum(srcfilePath);
			int index = 0;
			while ((s = br.readLine()) != null && s.length() > 0 && index < fileLines) {
				String[] strs = s.split("\\s+"); // 分割出一个个字符串来
				for (String indexStr : strs) {
					if (indexStr != null && indexStr.length() > 0) {
						m_Map.put(indexStr.trim(), 1); // 放入map中

						if (index >= 1000) { // 达到1000后，处理，写文件，然后清空map
							String onceStr = Utils.convertMapToString(m_Map);
							Utils.writeFile(desFilePath, onceStr);
							m_Map.clear();
						}
					}

				}
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			mainSignal.await(); // 等待main函数分割完文件后，map函数开始
			System.out.println("map thread" + Thread.currentThread().getName() + "is running......");
			this.dealFile();
			mapSignal.countDown(); // map线程必须要将计数器减1，等到map所有完成后，reduce才能执行
			System.out.println("map thread" + Thread.currentThread().getName() + "run at end......");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
