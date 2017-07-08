package io.flysium.nio.c2_channel.pipe;

import io.flysium.nio.ByteBufferUtils;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 管道测试
 *
 * @author Sven Augustus
 */
public class PipeTest {

    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        new Thread(new Input(pipe)).start();
        new Thread(new Output(pipe)).start();
    }

    static class Input implements Runnable {

        private final Pipe pipe;

        public Input(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            try {
                Pipe.SourceChannel sourceChannel = pipe.source();

                System.out.println("管道读取准备。");
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int bytesRead = sourceChannel.read(byteBuffer);
                Serializable serializable = ByteBufferUtils.readObject(byteBuffer);
                System.out.println("管道读取结果：" + serializable);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    static class Output implements Runnable {
        private final Pipe pipe;

        public Output(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            try {
                Pipe.SinkChannel sinkChannel = pipe.sink();

                System.out.println("管道写出准备。");
                Serializable object = "您好啊，Pipe。";
                ByteBuffer byteBuffer = ByteBufferUtils.writeObject(object);
                while (byteBuffer.hasRemaining()) {
                    sinkChannel.write(byteBuffer);
                }
                System.out.println("管道写出完成：" + object);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
