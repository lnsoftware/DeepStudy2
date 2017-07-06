package nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by yifan on 17-4-9.
 */
public class BufferDemo {

    public static void main(String[] args){
        Buffer buffer = ByteBuffer.allocate(7);
        System.out.println("Capacity:"+buffer.capacity());
        System.out.println("Limit:"+buffer.limit());
        System.out.println("Position:"+buffer.position());
        System.out.println("Remaining:"+buffer.remaining());
        System.out.println("Changing nio.nio.buffer limit to 5");
        buffer.limit(5);
        System.out.println("Limit:"+buffer.limit());
        System.out.println("Position:"+buffer.position());
        System.out.println("Remaining:"+buffer.remaining());
        System.out.println("Changing nio.nio.buffer position to 3");
        buffer.position(3);
        System.out.println("Position:"+buffer.position());
        System.out.println("Remaining:"+buffer.remaining());
        System.out.println(buffer);
    }
}
