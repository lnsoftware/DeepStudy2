package classic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yifan on 3/30/17.
 */
public class Copy2 {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println("usage : java Copy srcfile dstfile");
            return;
        }

        try(FileInputStream fis = new FileInputStream(args[0]);
            FileOutputStream fos = new FileOutputStream(args[1])){

            int b;
            while((b = fis.read()) != -1)
                fos.write(b);

        }catch(FileNotFoundException fnfe){
            System.err.println(args[0] + " could not be open for input"
                               + args[1] + " could not be open for wirte");
            return;
        }catch(IOException ioe){
            System.err.println("I/O error: " + ioe.getMessage());
        }
    }
}
