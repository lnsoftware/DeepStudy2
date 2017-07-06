package nio.buffer;

import java.nio.ByteBuffer;

/**
 * Created by yifan on 2017/4/11.
 */
public class BufferMarkerDemo {
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.put((byte)10).put((byte)20).put((byte)30).put((byte)40);
        buffer.limit(4);
        buffer.position(1).mark().position(3);
        System.out.println(buffer.get());
        System.out.println();
        buffer.reset();
        while(buffer.hasRemaining())
            System.out.println(buffer.get());
    }
}
