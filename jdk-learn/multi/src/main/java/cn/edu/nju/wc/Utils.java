package cn.edu.nju.wc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������
 * 
 * @author Administrator
 *
 */
public class Utils {
	
	private Lock lock=new ReentrantLock();

	/**
	 * �������ã� ������д��txt�ļ�,��һ��������·�����ڶ����������ļ�����
	 * 
	 * @return
	 * @throws IOException
	 */
	public static void writeFile(String path, String content) throws IOException {
		if (path == null || path.length() <= 0 || content == null || content.length() <= 0)
			return;
		FileOutputStream fos = new FileOutputStream(path, true); // ����׷�ӵ��ļ����
		FileChannel fc = fos.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024 * 1024 * 1024);
		byte[] byteMessage = content.getBytes();
		buf.put(byteMessage);
		buf.flip();
		fc.write(buf);
		fc.close();
		fos.close();

	}

	/**
	 * �������ã� ��ȡ�ļ�������
	 * 
	 * @return
	 */
	public static int getFileLineNum(String path) {
		if (path == null || path.length() <= 0)
			return -1;

		File test = new File(path);
		long fileLength = test.length();
		LineNumberReader rf = null;
		int lines = 0;
		try {
			rf = new LineNumberReader(new FileReader(test));

			if (rf != null) {
				rf.skip(fileLength);
				lines = rf.getLineNumber();
				rf.close();
			}
			//System.out.println(lines);

		} catch (IOException e) {
			if (rf != null) {
				try {
					rf.close();
				} catch (IOException ee) {
				}
			}
		}
		return lines;
	}

	/**
	 * ��map��ļ�ֵ��ת��Ϊ�������ַ��� string ��value
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String convertMapToString(Map<String, Integer> map) {
		if (map == null || map.size() <= 0)
			return null;
		StringBuilder sb = new StringBuilder();

		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iterator.next();
			String key = entry.getKey();
			Integer val = entry.getValue();
			String tmp = key + ":" + val + "\n";
			sb.append(tmp); // ƴ���ַ������Ժ�һ��д���ļ���
		}

		return sb.toString();
	}

}
