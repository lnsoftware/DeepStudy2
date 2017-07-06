package classic;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by yifan on 3/28/17.
 */
public class FileInputStreamResourceDemo {

    public static void main(String[] args) {
        try(FileInputStream fis = new FileInputStream("image.jpg");
            BufferedInputStream bis = new BufferedInputStream(fis)){

            //read bytes from file
            int _byte;
            while((_byte = bis.read()) != -1){
                //process _byte
            }


        }catch (IOException ioe){
            //
        }

    }
}
