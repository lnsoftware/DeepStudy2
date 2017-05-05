package com.hg.avatar;

import org.hackrslab.avatar.RandomAvatar;
import org.hackrslab.avatar.RandomAvatarBuilder;

import java.io.File;
import java.util.Random;
import java.util.UUID;

/**
 * Created by wangqinghui on 2016/3/3.
 */
public class AvatarGenFactory {


    public String gen(String name){
        Random random = new Random();
        RandomAvatar generator = new RandomAvatarBuilder()
                .squareSize(400)
                .blockSize(5)
                .asymmetry(false)
                .cache(true) // since 0.2.3
                .padding(20)
                .backgroundColor(0xeeeeee) // since 0.2.1
                .fontColor(0xffffff) // since 0.2.1
                .addColor(127, 127, 220)
                .addColor(100, 207, 172)
                .addColor(198, 87, 181)
                .addColor(134, 166, 220)
                .build();



        String savepath = "/tmp/" + UUID.randomUUID().toString();
        generator.generate(new File(savepath), RandomAvatar.Extra.initial(name));
        return savepath;
    }
}
