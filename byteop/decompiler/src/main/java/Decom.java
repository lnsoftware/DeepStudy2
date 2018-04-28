import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.benf.cfr.reader.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by wangqinghui on 2018/2/11.
 */
public class Decom {
    public static void main(String[] args) {
        String[] str = new String[]{"class"};
        String basePath = "/Users/hg/Gitopen/dumpclass/target/dump2";
        Collection<File> files = FileUtils.listFiles(new File(basePath), str, true);
//        for (File file : files) {
//            Zip     
//        }
        make(files);
        String[] tar = new String[]{"target.jar","--outputdir","decompiler/src/main/java"};
        Main.main(tar);
    }

    public static void make(Collection<File> files ){
//        String[] source = new String[]{"source1", "source2"};

        // Create a buffer for reading the files
        byte[] buf = new byte[1024];

        try {
            // Create the ZIP file
            String target = "target.jar";
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target));

            // Compress the files
            for (File file : files) {
                String source = file.getAbsolutePath();
                FileInputStream in = new FileInputStream(source);

                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(source));

                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                // Complete the entry
                out.closeEntry();
                in.close();
            }

            // Complete the ZIP file
            out.close();
        } catch (IOException e) {
        }
    }

}
