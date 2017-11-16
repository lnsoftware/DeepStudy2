package io.flysium.nio.c2_channel.filelock;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 同一进程测试
 *
 * @author Sven Augustus
 */
public class FileLockMain_1JVM extends FileChannelTest2 {

	public static void main(String[] args) throws InterruptedException {
		final String path = "file.txt";
		// 同一进程，即使是共享锁，同时读并且重叠，一样 文件重叠锁异常【OverlappingFileLockException】
		new Thread(new Runnable() {
			@Override
			public void run() {
				read(path, "线程A");
			}
		}).start();
		Thread.sleep(2000);
		new Thread(new Runnable() {
			@Override
			public void run() {
				read(path, "线程B");
			}
		}).start();
	}

}
