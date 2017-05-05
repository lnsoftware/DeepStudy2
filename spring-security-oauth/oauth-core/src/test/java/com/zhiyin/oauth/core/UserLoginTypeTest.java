package com.zhiyin.oauth.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hg on 2017/2/11.
 */
public class UserLoginTypeTest {


    @Test
    public void test(){

        String loginName = "ssss";
        String oauthName = UserLoginType.Telephone.oauthName(loginName);
        String findVal = UserLoginType.Telephone.findVal(oauthName);

        System.out.println(findVal);

    }

    @Test
    public void test2(){

        String loginName = "ssss";
        String oauthName = UserLoginType.Third.oauthName(loginName);
        String findVal = UserLoginType.Third.findVal(oauthName);

        System.out.println(findVal);

    }

}