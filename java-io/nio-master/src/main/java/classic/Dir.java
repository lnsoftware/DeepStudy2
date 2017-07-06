package classic;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by yifan on 3/28/17.
 */
public class Dir {
    public static void main(final String[] args){
        if(args.length != 2){
            System.err.println("usage:java Dir dirpath ext");
            return;
        }
        File file = new File(args[0]);
        FilenameFilter fnf = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(args[1]);
            }
        };

        String[] names = file.list(fnf);
        for(String name : names){
            System.out.println(name);
        }
    }
}
