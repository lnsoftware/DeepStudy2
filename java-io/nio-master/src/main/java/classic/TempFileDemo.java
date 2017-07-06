package classic;

import java.io.File;
import java.io.IOException;

/**
 * Created by yifan on 3/28/17.
 */
public class TempFileDemo {
    public static void main(String[] args) throws IOException{
        System.out.println(System.getProperty("java.io.tmpdir"));
        File temp = File.createTempFile("text",".txt");
        System.out.println(temp);
        temp.deleteOnExit();
    }
}
