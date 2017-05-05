package com.zhiyin.oauth.core;

/**
 * Created by hg on 2017/2/24.
 */
public class UserIdUtil {

    public static String UserIdKey = "user_id";

    public static String getIdStr(Long id){
        return "Long"+id;
    }

    public static Long getIdVal(String str){
        return Long.valueOf(str.substring("Long".length()));
    }

    public static void main(String[] args) {
       getIdVal( getIdStr(1L));
    }
}
