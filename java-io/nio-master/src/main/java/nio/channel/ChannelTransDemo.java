package nio.channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 将参数指定文件的内容传输到控制台输出
 * Created by yifan on 2017/4/14.
 */
public class ChannelTransDemo {
    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("usage: java ChannelTransDemo filespec");
            return;
        }

        try(FileInputStream fis = new FileInputStream(args[0])){

            FileChannel inChannel = fis.getChannel();
            WritableByteChannel outChannel = Channels.newChannel(System.out);
            inChannel.transferTo(0,inChannel.size(),outChannel);

        }catch (IOException ioe){
            System.out.println("I/O error: " + ioe.getMessage());
        }
    }
}
