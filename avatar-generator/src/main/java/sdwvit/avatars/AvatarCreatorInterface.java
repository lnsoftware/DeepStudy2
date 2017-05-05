package sdwvit.avatars;

import sdwvit.utils.CallbackFunction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Set;

public interface AvatarCreatorInterface {
    AvatarCreatorClassBuilder builder = new AvatarCreatorClassBuilder();
    Random r = new Random();
    Color backgroundColor = new Color(240, 240, 240);

    BufferedImage createOne();
    Set<BufferedImage> createMany();
    void createManyAndCallBack(CallbackFunction<BufferedImage> arg);
}
