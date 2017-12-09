package com.zhiyin.gene;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqinghui on 2017/12/11.
 */
public class GenericParse {


    public static void main(String[] args) throws Exception {
        classPar();
    }


    public static void classPar() throws Exception {

        // Map类型的属性
        Field mapField = Parame.class.getField("map");

        Class<?> mapFieldType = mapField.getType();
        System.out.println("map属性类型："+mapFieldType);

        System.out.println(mapField.getGenericType());

        System.out.println(Arrays.toString(mapFieldType.getGenericInterfaces()));
        Type type = mapFieldType.getGenericSuperclass();
        System.out.println(type);
//        ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
//        System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));

    }



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Parame<T> {
        public Map<String,List<T>> map;
    }

}
