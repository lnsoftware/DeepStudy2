package io.flysium.nio.c2_channel.filelock;

/**
 * 阻塞写
 *
 * @author Sven Augustus
 */
public class FileLockMain_w extends FileChannelTest2 {

    public static void main(String[] args) throws InterruptedException {
        final String path = "file.txt";
        //阻塞写
        write(path, "线程B");
    }

}
