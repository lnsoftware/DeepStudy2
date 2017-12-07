package com.zhiyin.gene;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

/**
 * Created by wangqinghui on 2017/11/20.
 */
public class JsonTest {
    public static void main(String[] args) throws IOException {
        List<Student> list = Lists.newArrayList();
        list.add(new Student(1));
        list.add(new Student(2));

        ObjectMapper mapper = new ObjectMapper(); //转换器
        String json = mapper.writeValueAsString(list);

        // ClassCastException
        List<Student> fastJsonToList = JSON.parseObject(json,List.class);
        try {
            for (Student student : fastJsonToList) {
                System.out.println(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        List<Student> fastJsonToList2 = JSON.parseObject(json,new com.alibaba.fastjson.TypeReference<List<Student>>(){});

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
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Student {
        private long id;
    }

}
