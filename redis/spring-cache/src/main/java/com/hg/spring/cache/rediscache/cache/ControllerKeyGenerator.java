package com.hg.spring.cache.rediscache.cache;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Created by wangqinghui on 2016/1/12.
 */
public class ControllerKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {

        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(method.getName());

        for (Object obj : params) {
            if( obj instanceof  C2sObject ){
                sb.append(((C2sObject) obj).getCacheKey());
            }
        }

        return sb.toString();
    }

    /**
     * Generate a key based on the specified parameters.
     */
//    public static Object generateKey(Object... params) {
//        if (params.length == 0) {
//            return SimpleKey.EMPTY;
//        }
//
//        if (params.length == 1) {
//            Object param = params[0];
//
//            if (param != null && !param.getClass().isArray()) {
//
//                return param;
//            }
//        }
//        return new SimpleKey(params);
//    }

}

