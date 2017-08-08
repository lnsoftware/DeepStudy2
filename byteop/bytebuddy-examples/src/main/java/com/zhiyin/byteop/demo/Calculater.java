package com.zhiyin.byteop.demo;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class Calculater {


    public int add(Integer a,Integer b){

        return a+b;

    }


    public int div(Integer a,Integer b){

        if(b<0){
            System.out.println("error");
            throw new RuntimeException("error");
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  a/b;
    }

}
