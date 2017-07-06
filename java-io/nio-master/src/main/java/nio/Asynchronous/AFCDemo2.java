package nio.Asynchronous;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yifan on 2017/4/17.
 */
public class AFCDemo2 {
    public static void main(String[] args) throws Exception{
        if(args.length != 1){
            System.err.println("usage:java AFCDemo path");
            return;
        }

        Path path = Paths.get(args[0]);
        AsynchronousFileChannel ch = AsynchronousFileChannel.open(path);
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Thread mainThd = Thread.currentThread();
        ch.read(buf, 0, null, new CompletionHandler<Integer, Void>() {

            @Override
            public void completed(Integer result, Void v) {
                System.out.println("bytes read = " + result);
                mainThd.interrupt();
            }

            @Override
            public void failed(Throwable t, Void v) {
                System.out.println("failure: " + t.toString());
                mainThd.interrupt();
            }
        });

        System.out.println("waiting for completion");
        try{
            mainThd.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        ch.close();
    }
}
