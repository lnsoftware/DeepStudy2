package cn.edu.nju.wc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 运行入口类
 * 
 * @author Administrator
 *
 */
public class Master {
	public static String srcRootPath = "E:/ProcessFiles/";
	public static String dstRootPath = "D:/ResultFiles/";

	public static void main(String[] args) {
		CountDownLatch mainSignal = new CountDownLatch(1);
		CountDownLatch mapSignal = new CountDownLatch(4);
		ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);// 创建一个线程池
		for (int i = 1; i <= 4; i++) {
			MapProcedure mp = new MapProcedure(mainSignal, mapSignal, srcRootPath + "text" + i + ".txt",
					dstRootPath + "result" + i + ".txt");
			threadPool.execute(mp);
		}

		threadPool.execute(new ReduceProcedure(mapSignal));

		splitFile(); // 分割文件

		mainSignal.countDown(); // main函数让出CPU让其他线程运行
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadPool.shutdown();
		System.out.println("======main thread run at end=====");

	}

	/**
	 * 分割文件
	 */
	public static void splitFile() {
		try {
			FileReader read = new FileReader("E:\\ProcessFiles\\kjv12.txt");
			BufferedReader br = new BufferedReader(read);
			String row;
			int rownum = 1;
			FileWriter fw1 = new FileWriter("E:/ProcessFiles/text1.txt");
			FileWriter fw2 = new FileWriter("E:/ProcessFiles/text2.txt");
			FileWriter fw3 = new FileWriter("E:/ProcessFiles/text3.txt");
			FileWriter fw4 = new FileWriter("E:/ProcessFiles/text4.txt");
			while ((row = br.readLine()) != null) {
				if (rownum % 4 == 1) {
					fw1.append(row + "\r\n");
				} else if (rownum % 4 == 2) {
					fw2.append(row + "\r\n");
				} else if (rownum % 4 == 3) {
					fw3.append(row + "\r\n");
				} else if (rownum % 4 == 0) {
					fw4.append(row + "\r\n");
				}
				rownum++;
			}
			fw1.close();
			fw2.close();
			fw3.close();
			fw4.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
