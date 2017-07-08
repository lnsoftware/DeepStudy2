package io.flysium.nio.c2_channel.filelock;

/**
 * 非阻塞读，共享锁模式
 *
 * @author Sven Augustus
 */
public class FileLockMain_rTry extends FileChannelTest2 {

    public static void main(String[] args) throws InterruptedException {
        final String path = "file.txt";
        // 非阻塞读，共享锁模式
        readTry(path, "线程A");
    }

}
