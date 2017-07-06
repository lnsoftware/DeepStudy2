package nio.buffer;

import java.nio.ByteBuffer;

/**
 * Created by yifan on 17-4-9.
 */
public class ByteBufferWR {
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(7);
        System.out.println("Capacity = " + buffer.capacity());
        System.out.println("Limit = " + buffer.limit());
        System.out.println("Position = " + buffer.position());
        System.out.println("Remaining = " + buffer.remaining());


        buffer.put((byte)10).put((byte)20).put((byte)30);

        System.out.println("Capacity = " + buffer.capacity());
        System.out.println("Limit = " + buffer.limit());
        System.out.println("Position = " + buffer.position());
        System.out.println("Remaining = " + buffer.remaining());


        for(int i=0;i<buffer.position();i++){
            System.out.println(buffer.get(i));
        }
    }
}
