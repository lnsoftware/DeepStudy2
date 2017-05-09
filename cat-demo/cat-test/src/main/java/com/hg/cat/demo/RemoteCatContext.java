package com.hg.cat.demo;

import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.google.common.collect.Maps;

import java.util.Map;

public class RemoteCatContext implements Cat.Context {

    Map<String,String> map = Maps.newHashMap();

    @Override
    public void addProperty(String s, String s1) {
        map.put(s,s1);
    }

    @Override
    public String getProperty(String s) {
        return map.get(s);
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

}
