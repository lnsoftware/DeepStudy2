package classic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


/**
 * Created by yifan on 3/31/17.
 */
public class Unscramble {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println("usage : java Unscramle srcpath destpath");
            return;
        }

        ScrambleInputStream sis = null;
        FileOutputStream fos = null;

        try {
            FileInputStream fis = new FileInputStream(args[0]);
            sis = new ScrambleInputStream(fis,makeMap());
            fos = new FileOutputStream(args[1]);

            int b;
            while((b = sis.read()) != -1)
                fos.write(b);


        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            if(sis != null)
                try{
                   sis.close();
                }catch(IOException ioe){
                   ioe.printStackTrace();
                }

            if(fos != null)
                try{
                    fos.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
        }
    }

    static int[] makeMap(){
        int[] map = new int[256];
        for(int i=0;i<map.length;i++)
            map[i] = i;

        Random r = new Random(0);
        for(int i=0;i<map.length;i++){
            int n = r.nextInt(map.length);
            int temp = map[i];
            map[i] = map[n];
            map[n] = temp;
        }

        int[] temp = new int[256];
        for(int i=0;i<temp.length;i++)
            temp[map[i]] = i;

        return temp;
    }
}
