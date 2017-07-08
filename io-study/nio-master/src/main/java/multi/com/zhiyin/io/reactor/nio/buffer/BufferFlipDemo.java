package multi.com.zhiyin.io.reactor.nio.buffer;

import java.nio.CharBuffer;

/**
 * Created by yifan on 2017/4/10.
 */
public class BufferFlipDemo {
    public static void main(String[] args) {
        String[] poem = {
            "Roses are red",
            "Violets are blue",
            "Sugar is sweet",
            "And so are you."
        };

        CharBuffer buffer = CharBuffer.allocate(50);

        for (int i = 0; i < poem.length; i++) {

            //Fill the multi.com.zhiyin.io.reactor.nio.multi.com.zhiyin.io.reactor.nio.buffer
            for (int j = 0; j < poem[i].length(); j++) {
                buffer.put(poem[i].charAt(j));
            }

            //Flip the buffers so that its contents can be read.
            buffer.flip();

            //Drain the multi.com.zhiyin.io.reactor.nio.multi.com.zhiyin.io.reactor.nio.buffer
            while (buffer.hasRemaining())
                System.out.print(buffer.get());

            //Empty the multi.com.zhiyin.io.reactor.nio.multi.com.zhiyin.io.reactor.nio.buffer to prevent BufferOverflowException
            buffer.clear();

            System.out.println();
        }
    }
}
