package com.hg.spring.cache.rediscache.cache;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hg on 2016/1/12.
 */

@Slf4j
public class C2sObject {

    public String getCacheKey(){
        log.warn("should not use this.");
        try {
            return this.getFieldNamesAndValues(this,false);
        } catch (IllegalAccessException e) {
            log.error("gen key error,{}",e);
        }
        return "ERROR_"+ UUID.randomUUID();
    }


    public  String getFieldNamesAndValues(final Object obj, boolean publicOnly)
            throws IllegalArgumentException,IllegalAccessException {
        Class<? extends Object> c1 = obj.getClass();
        Map<String, Object> map = Maps.newLinkedHashMap();
        Field[] fields = c1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if (publicOnly) {
                if(Modifier.isPublic(fields[i].getModifiers())) {
                    Object value = fields[i].get(obj);
                    map.put(name, value);
                }
            }
            else {
                fields[i].setAccessible(true);
                Object value = fields[i].get(obj);
                map.put(name, value);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(obj.getClass().getName()+"_");
        for(Map.Entry<String,Object> entry : map.entrySet()){
            sb.append(entry.getKey());
            sb.append("_");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }
}
