package com.zhiyin.gene.sss;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

public class TypeReference4Reflect<T> extends TypeReference<T> {
    TypeReference4Reflect(T t) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {  
        Class<?> cla = TypeReference.class ;  
        Field field =  cla.getDeclaredField("_type");
        field.setAccessible(true);  
        field.set(this, t);  
    }


    public static void main(String[] args) throws Exception{
        ClassA a = new ClassA();
        a.setName("ss");
        List<ClassA> list = Lists.newArrayList(a);

        TestJson.reflect(JSON.toJSONString(list));
    }

}

class TestXMLAndJson {

    public static void reflect(String jackson) throws JsonParseException, JsonMappingException, IllegalArgumentException, SecurityException, IOException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        ObjectMapper mapper = new ObjectMapper();
        Method[] methods = TestXMLAndJson.class.getDeclaredMethods();
        for(Method m: methods ) {
            System.out.println(m.getName());
            if(m.getName().equals("testReflect")) {
                Type[] types = m.getGenericParameterTypes();
                Object o = mapper.readValue(jackson, new TypeReference4Reflect(types[0]));
                TestJson testJSON = new TestJson();
                m.invoke(testJSON, o);
            }
        }
    }

    public void testReflect(List<ClassA> psrList) {
        for(Iterator<ClassA> iter = psrList.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next().getName());
        }
    }

}

class TestJson extends TestXMLAndJson{

}

@Data
class ClassA{
    String name;
}