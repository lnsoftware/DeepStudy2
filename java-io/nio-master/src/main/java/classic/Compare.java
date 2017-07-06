package classic;

import java.io.File;
import java.io.IOException;

/**
 * Created by yifan on 3/28/17.
 */
public class Compare {

    public static void main(String[] args) throws IOException{
        if(args.length != 1){
            System.err.println("usage:java Compare filespec1 filespec2");
            return;
        }

        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        System.out.println(file1.compareTo(file2));
        System.out.println(file1.getCanonicalFile().compareTo(file2.getCanonicalFile()));
    }
}
