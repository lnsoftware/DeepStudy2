package io.flysium.bio.c4_piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道测试1
 * 
 * @author SvenAugustus(蔡政滦)  e-mail: SvenAugustus@outlook.com
 * @version 2017年1月26日
 */
public class PipedTest1 {

	static class Input implements Runnable {

		private final PipedInputStream inputStream = new PipedInputStream();

		public Input() {
		}

		public PipedInputStream getInputStream() {
			return inputStream;
		}

		@Override
		public void run() {
			try {
				byte[] buf = new byte[1024];
				int len = -1;
				System.out.println("管道读取准备。");
				StringBuffer result = new StringBuffer();

				while ((len = inputStream.read(buf)) > 0) {
					//System.out.println(new String(buf, 0, len));
					result.append(new String(buf, 0, len));
				}

				System.out.println("管道读取结果：" + result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (inputStream != null)
						inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	static class Output implements Runnable {

		private final PipedOutputStream outputStream = new PipedOutputStream();

		public Output() {
		}

		public PipedOutputStream getOutputStream() {
			return outputStream;
		}

		@Override
		public void run() {
			try {
				System.out.println("管道写出准备。");
				StringBuilder sb = new StringBuilder();
				// 模拟 通过for循环写入2050个字节
				for (int i = 0; i < 201; i++) {
					sb.append("0123456789");
					if (i > 0 && (i % 10 == 0)) {
						sb.append("\r\n");
					}
				}
				String str = sb.toString();
				outputStream.write(str.getBytes());
				System.out.println("管道写出完成。");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (outputStream != null)
						outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		Input input = new Input();
		Output output = new Output();
		/**
		 * 将“管道输入流”和“管道输出流”关联起来。
		 */
		//input.getInputStream().connect(output.getOutputStream());// 与下面一行等价
		output.getOutputStream().connect(input.getInputStream());

		new Thread(input).start();
		new Thread(output).start();
	}

}
