package com.zhiyin.devtools.demo.jdi;

import java.util.Random;

/**
 * Created by wangqinghui on 2018/1/7.
 */
public class BreakpointUtil {

    public static int randomVal(){
        int random = new Random().nextInt();
        //获取断点值的行数
        System.out.println(random);
        return random;
    }

}
