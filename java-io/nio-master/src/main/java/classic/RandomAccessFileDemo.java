package classic;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by yifan on 3/29/17.
 */
public class RandomAccessFileDemo {

    public static void main(String[] args) throws IOException{
        RandomAccessFile raf = new RandomAccessFile("/home/yifan/work/tmp/employee.txt","rw");
        FileDescriptor fd = raf.getFD();

        //Perform a critical write operation.
        raf.write(1);

        //Synchronized with the underlying disk by flushing the operating system
        // output buffers to the disk
        fd.sync();

        //necessary
        raf.write(2);

        //do other work
        //close the file empting output buffers to the disk
        raf.close();

    }
}
