package sdwvit.avatars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvatarHelper {
    private static final Logger logger = LoggerFactory.getLogger(AvatarHelper.class);
    private static final String USAGE =
            "int count, int size, double fillFactor, float alphaFactor, int pixelSize, boolean needBorder";
    private static final String DEFAULT_VALUES = "Image count: 1,\n" +
            "Size of each pixel image: 5x5 pixels,\n" +
            "Filling factor (How often pixels are generated): 70%,\n" +
            "Color alpha channel factor: 50%,\n" +
            "Pixel size: 1x1 pixels,\n" +
            "Border size: 1 pixel,\n" +
            "Full image size: 7x7 pixels.";
    private static final ImageToDiskWriter writer = new ImageToDiskWriter();

    public static AvatarCreatorClassBuilder getAvatarCreatorObjectBuilderFromArgs(final String[] args) {
        try {
            return AvatarCreatorInterface.builder
                    .withImageCount(args[0])
                    .withBaseSize(args[1])
                    .withFillFactor(args[2])
                    .withAlphaFactor(args[3])
                    .withPixelSize(args[4])
                    .whichNeedsBorder(args[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.info("Some args are missing, using default values");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid command line parameters", e.getCause());
            logger.error("Usage:", USAGE);
            logger.error("Using default values\n", DEFAULT_VALUES);
        }
        return AvatarCreatorInterface.builder;
    }

    public static ImageToDiskWriter writer() {
        return writer;
    }
}
