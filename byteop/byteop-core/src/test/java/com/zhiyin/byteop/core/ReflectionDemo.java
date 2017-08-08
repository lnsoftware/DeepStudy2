package com.zhiyin.byteop.core;

import com.zhiyin.byteop.core.OuterClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

class ReflectionDemo {

    //no comment version
    public static Object giveMeInnerInstance() throws Exception{
        OuterClass outerObject = new OuterClass();
        Class<?> innerClass = Class.forName("com.zhiyin.byteop.core.OuterClass$InnerClass");
        Constructor<?> constructor = innerClass.getDeclaredConstructor(OuterClass.class, int.class);
        constructor.setAccessible(true);
        return constructor.newInstance(outerObject,42);
    }

    public static Object getFieldValue(Object obj, String fieldName) throws Exception{
        Class<?> clazz = obj.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    //lets test our code
    public static void main(String[] args) throws Exception {
        Object innerClassObject = giveMeInnerInstance();
        System.out.println(getFieldValue(innerClassObject, "x"));           
    }
}