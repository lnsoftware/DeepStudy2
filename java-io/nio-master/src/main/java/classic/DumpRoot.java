package classic;

import java.io.File;

/**
 * Created by yifan on 3/28/17.
 */
public class DumpRoot {

    public static void main(String[] args){
        File[] roots = File.listRoots();
        for(File root : roots){
            System.out.println(root);
        }
    }
}
