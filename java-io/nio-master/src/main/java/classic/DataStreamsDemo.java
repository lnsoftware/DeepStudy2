package classic;

import java.io.*;

/**
 * Created by yifan on 3/31/17.
 */
public class DataStreamsDemo {
    final static String FILENAME = "/home/yifan/work/tmp/values.txt";

    public static void main(String[] args) throws Exception {
        try(FileOutputStream fos = new FileOutputStream(FILENAME);
            DataOutputStream dos = new DataOutputStream(fos)){

            dos.writeInt(1995);
            dos.writeUTF("Saving this string in modified UTF-8 format!");
            dos.writeFloat(1.0F);

        }catch (IOException ioe){
            System.err.println("I/O error:" + ioe.getMessage());
        }

        try(FileInputStream fis = new FileInputStream(FILENAME);
            DataInputStream dis = new DataInputStream(fis)){
            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());
            System.out.println(dis.readFloat());
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }
}
