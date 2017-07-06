package classic;

import java.io.File;
import java.io.IOException;

/**
 * Created by yifan on 3/28/17.
 */
public class PathInfo {

    public static void main(final String[] args) throws IOException{

        if(args.length != 1){
            System.err.println("usage:java PathInfo path");
            return;
        }

        File file = new File(args[0]);
        System.out.println("Absolute path = " + file.getAbsolutePath());
        System.out.println("Canonical path = " + file.getCanonicalPath());
        System.out.println("Name = " + file.getName());
        System.out.println("Parent = " + file.getParent());
        System.out.println("Path = " + file.getPath());
        System.out.println("Is absolute = " + file.isAbsolute());

    }
}
