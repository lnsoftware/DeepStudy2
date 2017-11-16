package io.flysium.nio.c2_channel.filelock;

/**
 * 阻塞写，共享锁模式
 *
 * @author Sven Augustus
 */
public class FileLockMain_wShared extends FileChannelTest2 {

	public static void main(String[] args) throws InterruptedException {
		final String path = "file.txt";
		//阻塞写，共享锁模式
		writeByShared(path, "线程B");
	}

}
