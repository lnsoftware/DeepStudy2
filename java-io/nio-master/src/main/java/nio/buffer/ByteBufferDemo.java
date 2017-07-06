package nio.buffer;

import java.nio.ByteBuffer;

/**
 * Created by yifan on 17-4-9.
 */
public class ByteBufferDemo {

    public static void main(String[] args){
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        if(buffer1.hasArray()){
            System.out.println("buffer1 array:"+buffer1.array());
            System.out.println("buffer1 array offset:"+buffer1.arrayOffset());
            System.out.println("capacity :" + buffer1.capacity());
            System.out.println("limit:"+buffer1.limit());
            System.out.println("position:"+buffer1.position());
            System.out.println("remaining:"+buffer1.remaining());
            System.out.println();
        }

        byte[] bytes = new byte[200];
        ByteBuffer buffer2 = ByteBuffer.wrap(bytes);
        buffer2 = ByteBuffer.wrap(bytes,10,50);
        if(buffer2.hasArray()){
            System.out.println("buffer2 array:"+buffer2.array());
            System.out.println("buffer2 array offset:"+buffer2.arrayOffset());
            System.out.println("capacity :" + buffer2.capacity());
            System.out.println("limit:"+buffer2.limit());
            System.out.println("position:"+buffer2.position());
            System.out.println("remaining:"+buffer2.remaining());
            System.out.println();
        }
    }
}
