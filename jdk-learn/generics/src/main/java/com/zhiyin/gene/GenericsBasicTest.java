package com.zhiyin.gene;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by wangqinghui on 2017/11/20.
 */
public class GenericsBasicTest {

    public static void main(String[] args) {

        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        // 泛型无关
        System.out.println(c1 == c2);


        // 取不到泛型类
        List<Integer> list = new ArrayList<Integer>();
        Map<Integer, String> map = new HashMap<Integer, String>();
//        System.out.println(list.getClass().getGenericSuperclass());
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));


        // 构造匿名类
        Map<String, Integer> map2 = new HashMap<String, Integer>() {};
        Type type = map2.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
        System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));

    }

    // 泛型擦除导致 方法一样
    public void test(List<String> ls){
        for (String tmp : ls) {
            System.out.println(tmp);
        }
    }
//    public void test(List<Integer> li){
//        System.out.println("Integer");
//    }
}
