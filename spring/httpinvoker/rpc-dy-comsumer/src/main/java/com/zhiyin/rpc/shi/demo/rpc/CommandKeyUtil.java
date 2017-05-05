package com.zhiyin.rpc.shi.demo.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.net.InetAddresses;
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by hg on 2016/6/24.
 */
@Slf4j
public class CommandKeyUtil {

//    private Logger log = LoggerFactory.getLogger(CommandKeyUtil.class);

  public static   Map<String,Integer> keyIndexMap = Maps.newConcurrentMap();


    public static String getCk(String command){

        return command + getIndex(command);

    }

    public static Integer getIndex(String command){

        if(keyIndexMap.containsKey(command)){
            return keyIndexMap.get(command);
        }
        return 0;
    }

    public static void setIndex(String conf){
        if(Strings.isNullOrEmpty(conf)){
            log.error("conf str is null.");
            return;
        }

        List<String> changeKey = Lists.newArrayList();

        Map<String,String> kv = JSON.parseObject(conf, Map.class);
        for(Map.Entry<String ,String> entry : kv.entrySet()){
            String key = entry.getKey().trim();
            String val = entry.getValue().trim();
            if(Strings.isNullOrEmpty( key )){
                continue;
            }
            String[] keyInfo = key.split("\\.");
            if(keyInfo==null || keyInfo.length<3 ){
                continue;
            }

            String tmpCk = keyInfo[2];

            if("default".equals( tmpCk )){
                continue;
            }


            String newCk = tmpCk + (getIndex(tmpCk)+1);
            keyInfo[2] = newCk;

            String newConfKey = "";
            for(String t: keyInfo){
                newConfKey += t+".";
            }
            newConfKey=newConfKey.substring(0,newConfKey.length()-1);
            ConfigurationManager.getConfigInstance().setProperty(newConfKey,val);
            log.info(""+newConfKey + ":" + val);

            changeKey.add(tmpCk);
        }

        for(String key : changeKey ){
            Integer in = getIndex(key);
            in = in+1;
            keyIndexMap.put(key ,in);
        }

        //HystrixCommandMetrics.getInstance("").re

    }
}
