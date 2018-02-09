package com.zhiyin.cases;

/**
 * Created by hg on 2018/2/8.
 */
public class Producer {

    public String send(String str){
        System.out.println("发送消息："+str);
        return "succ";
    }

}
