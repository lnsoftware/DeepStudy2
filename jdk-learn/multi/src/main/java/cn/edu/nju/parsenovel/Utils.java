//package cn.edu.nju.parsenovel;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//public class Utils {
//	/**
//	 * �������ã� jsoup����URL������ݣ�����document
//	 *
//	 * @return
//	 */
//	public static Document getFromUrl(String url) throws IOException {
//		Document doc = null;
//		// blacklist��blacklistdetailҳ���
//		Connection con = Jsoup.connect(url).timeout(50000000).maxBodySize(1000000000).ignoreContentType(true)
//				.followRedirects(false);// ��ȡ��������
//		// ������ɽ��ܵ�MIME����
//		con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		con.header("Accept-Encoding", "gzip, deflate");
//		con.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
//		con.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");
//		try {
//			doc = con.get();
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage() + " has problem.");
//		}
//		return doc;
//	}
//
//	/**
//	 * �������ã� ������д��txt�ļ�,��һ��������·�����ڶ����������ļ�����
//	 *
//	 * @return
//	 * @throws IOException
//	 */
//	public static void writeFile(String path, String content) throws IOException {
//		FileOutputStream fos = new FileOutputStream(path, true); // ����׷�ӵ��ļ����
//		FileChannel fc = fos.getChannel();
//		ByteBuffer buf = ByteBuffer.allocate(1024 * 1024 * 1024);
//		byte[] byteMessage = content.getBytes();
//		buf.put(byteMessage);
//		buf.flip();
//		fc.write(buf);
//		fc.close();
//		fos.close();
//
//	}
//}
