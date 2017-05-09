//package cn.edu.nju.parsenovel;
//
//import java.io.IOException;
//
///**
// * @author wytan ��ȡС˵
// *
// */
//public class Main {
//	private static String rootUrl = "http://jiaren.org/2015/02/03/xiaoshuo-223/";
//	private static int novelPages = 71;
//	private static String filePath = "D:/ResultFiles/";
//	private static String fileName = "��������.doc";
//
//	public static void main(String[] args) {
//		String absolutePath = filePath + fileName;
//		try {
//			for (int i = 1; i <= novelPages; i++) {
//				String url = rootUrl + i + "/";
//				String article = Utils.getFromUrl(url).select("article").text();
//				int lastIndex = article.lastIndexOf("��ǩ");
//				String finalArticle = article.substring(0, lastIndex) + "\n======================================\n";
//				Utils.writeFile(absolutePath, finalArticle);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
