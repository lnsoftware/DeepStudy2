package com.zhiyin.spring.mvc;

import com.google.common.collect.Maps;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * Created by wangqinghui on 2017/12/9.
 */
public class Tes {


    public static void main(String[] args) throws Exception {
        ResolvableType t = ResolvableType.forField(Tmp.class.getDeclaredField("myMap"));

        System.out.println(t);


        Map<String,Integer> type = Maps.newConcurrentMap();

        ResolvableType mapType = ResolvableType.forClass(type.getClass());
        System.out.println(mapType);

        Method[] classMethods = Tmp.class.getMethods();

        Method firstMethod = classMethods[0];
        Parameter[] params = firstMethod.getParameters();

        Parameter firstParam = params[0];
        System.out.println( firstParam.getName() );
        System.out.println(params[0].getType());
        System.out.println(firstParam.);
//        System.out.println( firstParam.get);
        
//        ResolvableType methodType = ResolvableType.forMethodParameter( classMethods[0], 0);
//        System.out.println(methodType);

    }





    public static class Tmp{

        private Map<String,Integer> myMap;

        public void postReq(Map<String,String> map){

        }
    }
}
