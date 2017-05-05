package sdwvit.avatars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class ImageToDiskWriter {
    private static final String filePath = "./images/";
    private static final Logger logger = LoggerFactory.getLogger(ImageToDiskWriter.class);
    private static final String templateAttributeName = "number";
    private static final String fileNameTemplate = "img<" + templateAttributeName + ">.png";

    public void write(final BufferedImage image) {
        prepareDir();
        generateFileNameAndWriteToDisk(image);
    }

    public void writeMany(final Set<BufferedImage> imageSet) {
        prepareDir();

        Iterator<BufferedImage> it = imageSet.iterator();
        while (it.hasNext()) {
            this.generateFileNameAndWriteToDisk(it.next());

        }
//        imageSet.forEach(this::generateFileNameAndWriteToDisk);
    }

    public File findFreeFileName(ST st) {
        String name = filePath + st.render();
        File outputFile = new File(name);
        if (outputFile.exists()) {
            int shift = (Integer)st.getAttribute(templateAttributeName);
            st.remove(templateAttributeName);
            st.add(templateAttributeName, shift + 1);
            return findFreeFileName(st);
        } else {
            logger.info("Found free filename [" + name + "]");
            return outputFile;
        }
    }

    private void generateFileNameAndWriteToDisk(BufferedImage image) {
        try {
            ST st = new ST(fileNameTemplate);
            st.add(templateAttributeName, 0);
            File file = findFreeFileName(st);
            logger.info("Writing file [" + file.getName() + "] at [" + filePath + "]");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void prepareDir() {
        new File(filePath).mkdir();
    }
}
