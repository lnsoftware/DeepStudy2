package cn.edu.nju.wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * map�׶εĴ�����
 * 
 * @author Administrator
 *
 */
public class MapProcedure implements Runnable {

	private CountDownLatch mapSignal; // ����map���֮��reduce���ܿ�ʼִ��Ŷ
	private CountDownLatch mainSignal; // ���������зָ��ļ�������map��������
	private String srcfilePath; // Դ�ļ�·��
	private String desFilePath; // Ŀ���ļ�·��
	private Map<String, Integer> m_Map = new HashMap<String, Integer>(); // ���word��map

	/**
	 * ���캯��
	 * 
	 * @param countDownLatch
	 */
	public MapProcedure(CountDownLatch countDownLatch) {
		this.mapSignal = countDownLatch;
	}

	/**
	 * ���ع��캯��
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
	 * ��ȡ�ļ����ָ����map��
	 */
	@SuppressWarnings("resource")
	public void dealFile() {
		try {
			File file = new File(srcfilePath);// Text�ļ�
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			int fileLines = Utils.getFileLineNum(srcfilePath);
			int index = 0;
			while ((s = br.readLine()) != null && s.length() > 0 && index < fileLines) {
				String[] strs = s.split("\\s+"); // �ָ��һ�����ַ�����
				for (String indexStr : strs) {
					if (indexStr != null && indexStr.length() > 0) {
						m_Map.put(indexStr.trim(), 1); // ����map��

						if (index >= 1000) { // �ﵽ1000�󣬴���д�ļ���Ȼ�����map
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
			mainSignal.await(); // �ȴ�main�����ָ����ļ���map������ʼ
			System.out.println("map thread" + Thread.currentThread().getName() + "is running......");
			this.dealFile();
			mapSignal.countDown(); // map�̱߳���Ҫ����������1���ȵ�map������ɺ�reduce����ִ��
			System.out.println("map thread" + Thread.currentThread().getName() + "run at end......");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
