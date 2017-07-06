package classic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yifan on 3/30/17.
 */
public class Copy {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println("usage: java copy srcfile dstfile");
            return;
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try{
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            // I chose b instead of byte because byte is a reserved word
            int b ;

            while((b = fis.read()) != -1)
                fos.write(b);
        }catch (FileNotFoundException fnfe){
            System.err.println(args[0] + " could not be opened for input, or "
                               + args[1] + " could not be created for output");
        }catch(IOException e){
            System.err.println("I/O error: " + e.getMessage());
        }finally {
            if(fis != null){
                try{
                    fis.close();
                }catch(IOException ioe){
                    //shouldn't happen in this context
                    assert false;
                }
            }

            if(fos != null){
                try{
                    fos.close();
                }catch(IOException ioe){
                    assert false;
                }
            }
        }

    }
}
