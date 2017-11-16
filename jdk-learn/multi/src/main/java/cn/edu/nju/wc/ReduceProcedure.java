package cn.edu.nju.wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * reduce�׶εĴ�����
 * 
 * @author Administrator
 *
 */
public class ReduceProcedure implements Runnable {

	private CountDownLatch mapSignal; // map��������Ϊ0��reduce�̲߳�������
	private String srcfilePath = "D:/ResultFiles/"; // �ļ�·��
	private String resultFile = "D:/ResultFiles/result.txt"; // ����ļ�
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
	 * �����ļ�
	 */
	@SuppressWarnings("resource")
	public void dealFile() {
		try {
			File file = new File(srcfilePath);// Text�ļ�
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

					if (r_Map.containsKey(key)) { // ��������õ���
						Integer v = r_Map.get(key) + 1;
						r_Map.put(key, v);

					} else { // ����������put��ȥ
						r_Map.put(key, 1);
					}
				}
			}
			String resultStr = Utils.convertMapToString(r_Map); // ƴ�����ַ�����д�����ļ�
			Utils.writeFile(resultFile, resultStr); /// д���

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			mapSignal.await(); // �ȴ�����������0
			System.out.println("reduce thread" + Thread.currentThread().getName() + "is running ......");
			this.dealFile(); // ������������
			System.out.println("reduce run at end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
