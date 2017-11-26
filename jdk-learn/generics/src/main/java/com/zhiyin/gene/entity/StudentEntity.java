package com.zhiyin.gene.entity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Created by wangqinghui on 2017/11/21.
 */
public class StudentEntity<T> {
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public static void main(String[] args) {

        StudentEntity<String> studentEntity = new StudentEntity<>();
        studentEntity.setId("hg");
        System.out.println(studentEntity.getId());

        System.out.println(Arrays.toString(studentEntity.getClass().getTypeParameters()));

        StudentEntityWithType studentEntityWithType = new StudentEntityWithType();
        Type superClass = studentEntityWithType.getClass().getGenericSuperclass();
        Type argType = ((ParameterizedType) superClass).getActualTypeArguments()[0];

        System.out.println( argType.getTypeName() );

        StudentEntity<String> annStud = new StudentEntity<String>(){};
        ParameterizedType parameterizedType = ParameterizedType.class.cast( annStud.getClass().getGenericSuperclass() );
        System.out.println(Arrays.toString( parameterizedType.getActualTypeArguments() ));
    }

}
