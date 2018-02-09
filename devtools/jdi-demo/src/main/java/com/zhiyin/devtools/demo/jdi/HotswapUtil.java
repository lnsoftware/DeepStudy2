package com.zhiyin.devtools.demo.jdi;

import java.util.Random;

/**
 * Created by wangqinghui on 2018/1/7.
 */
public class HotswapUtil {

    public static int randomVal(){
        int random = new Random().nextInt();
        //重置变量
        random = 111;
        return random;
    }
}
