package io.flysium.nio.c2_channel.filelock;

/**
 * 阻塞读，共享锁模式
 *
 * @author Sven Augustus
 */
public class FileLockMain_r extends FileChannelTest2 {

	public static void main(String[] args) throws InterruptedException {
		final String path = "file.txt";
		// 阻塞读，共享锁模式
		read(path, "线程A");
	}

}
