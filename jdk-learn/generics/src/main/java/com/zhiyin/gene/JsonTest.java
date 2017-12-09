package com.zhiyin.gene;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqinghui on 2017/11/20.
 */
public class JsonTest {
    public static void main(String[] args) throws Exception {
        List<Student> list = Lists.newArrayList();
        list.add(new Student(1));
        list.add(new Student(2));

        ObjectMapper mapper = new ObjectMapper(); //jackjson转换器
        String json = mapper.writeValueAsString(list);

        // ClassCastException，list的object类型擦除，解析结果是JSONObject
        List<Student> fastJsonToJSONObjectList = JSON.parseObject(json,List.class);
        try {
            for (Student student : fastJsonToJSONObjectList) {
                System.out.println(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        List<Student> fastJsonToList = JSON.parseObject(json,new com.alibaba.fastjson.TypeReference<List<Student>>(){});

        // jackjson将json解析成LinkedHashMap
        List<Student> jackjsonToList = mapper.readValue(json, List.class);
        try {
            for (Student student : jackjsonToList) {
                System.out.println(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        List<Student> jsonToList = mapper.readValue(json, new TypeReference<List<Student>>(){});
        for (Student student : jsonToList) {
            System.out.println(student.getId());
        }



        // 测试复杂类型
        complex();

    }

    public static void complex(){
        Student s = new Student(1L);
        List<Student> list = Lists.newArrayList(s);
        Map<String,List<Student>> map = Maps.newHashMap();
        map.put("hg",list);
        String jsonStr = JSON .toJSONString(map);

        Map<String,List<Student>> fastJsonToMap = JSON.parseObject(jsonStr,new com.alibaba.fastjson.TypeReference< Map<String,List<Student>> >(){});

        for (String key: fastJsonToMap.keySet()) {
            List<Student> val = fastJsonToMap.get(key);
            for (Student tmp : val) {
                System.out.println(tmp);
            }
        }



        Batch batch = new Batch();
        batch.setMap(map);

        Batch jsonToBatch = JSON.parseObject(JSON.toJSONString(batch), Batch.class);
        for (String key: jsonToBatch.getMap().keySet()) {
            List<Student> val = jsonToBatch.getMap().get(key);
            for (Student tmp : val) {
                System.out.println(tmp);
            }
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Student {
        private long id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Batch {
        public Map<String,List<Student>> map;
    }

}
