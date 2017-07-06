package classic;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by yifan on 3/28/17.
 */
public class FileDirectoryInfo {

    public static void main(final String[] args) throws IOException {
        if(args.length != 1){
            System.err.println("usage : java FileDirectoryInfo path");
            return;
        }

        File file = new File(args[0]);
        System.out.println("Abount " + file + ":");
        System.out.println("Exists = " + file.exists());
        System.out.println("Is directory = " + file.isDirectory());
        System.out.println("Is hidden = " + file.isHidden());
        System.out.println("Last modified = " + new Date(file.lastModified()));
        System.out.println("Length = " + file.length());
    }
}
