package com.zhiyin.dir;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

/**
 * http://blog.csdn.net/fcbayernmunchen/article/details/8635427
 */
public class IOTest {
    static final int BUFFER_SIZE = 1024*2;

    String path = "./test.data";


    public static void main(String[] args) throws Exception {
        IOTest test = new IOTest();
        test.genFile();

        test.mapFile();
        test.dirBuff();
        test.norBuff();
    }

    public void genFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path));
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
        BufferedWriter out = new BufferedWriter(osw);
        int i = 0;
        while(i<30000000L) {
            i++;
            out.write("ssssssss\n");
        }
        out.close();
        System.out.println("gen file done.");
    }

    public void mapFile() throws Exception {
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();
        MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0,
                channel.size());

        byte[] b = new byte[BUFFER_SIZE];
        int len = (int) file.length();

        long begin = System.currentTimeMillis();

        for (int offset = 0; offset < len; offset += BUFFER_SIZE) {

            if (len - offset > BUFFER_SIZE) {
                buff.get(b);
            } else {
                buff.get(new byte[len - offset]);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("time is:" + (end - begin));
    }


    public void dirBuff() throws Exception {
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();
        ByteBuffer buff = ByteBuffer.allocateDirect(BUFFER_SIZE);

        long begin = System.currentTimeMillis();
        while (channel.read(buff) != -1) {
            buff.flip();
            buff.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println("time is:" + (end - begin));

    }
    public void norBuff() throws Exception {
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BUFFER_SIZE);

        long begin = System.currentTimeMillis();
        while (channel.read(buff) != -1) {
            buff.flip();
            buff.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println("time is:" + (end - begin));
    }
}  