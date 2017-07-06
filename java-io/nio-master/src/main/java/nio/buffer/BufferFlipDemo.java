package nio.buffer;

import java.nio.CharBuffer;

/**
 * Created by yifan on 2017/4/10.
 */
public class BufferFlipDemo {
    public static void main(String[] args){
        String[] poem = {
                "Roses are red",
                "Violets are blue",
                "Sugar is sweet",
                "And so are you."
        };

        CharBuffer buffer = CharBuffer.allocate(50);

        for(int i=0;i<poem.length;i++){

            //Fill the nio.nio.buffer
            for(int j=0;j<poem[i].length();j++){
                buffer.put(poem[i].charAt(j));
            }

            //Flip the buffers so that its contents can be read.
            buffer.flip();

            //Drain the nio.nio.buffer
            while(buffer.hasRemaining())
                System.out.print(buffer.get());

            //Empty the nio.nio.buffer to prevent BufferOverflowException
            buffer.clear();

            System.out.println();
        }
    }
}
