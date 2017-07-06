package classic;

import java.io.File;

/**
 * Created by yifan on 3/28/17.
 */
public class Permissions {
    public static void main(String[] args){
        if(args.length != 1){
            System.err.println("usage:java Permissions filespec");
            return;
        }
        File file = new File(args[0]);
        System.out.println("Checking permissons for " + args[0]);
        System.out.println("   Execute = " + file.canExecute());
        System.out.println("   Read = " + file.canRead());
        System.out.println("   Write = " + file.canWrite());
    }
}
