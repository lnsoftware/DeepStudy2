package com.zhiyin.jagent;

import com.google.common.collect.Lists;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqinghui on 2016/12/8.
 */
public class ClazzUtil {

//    public static boolean methodMatch






    public static boolean methodForbidModify(CtBehavior ctMethod) {
        return !methodCouldModify(ctMethod);
    }

    public static boolean methodCouldModify(CtBehavior ctMethod) {
        if ( ctMethod.isEmpty() == true) {
            return false;
        }
        if( AgentConfig.NotModifyMethodNames.contains(ctMethod.getName())){
            return false;
        }
        return true;
    }


    public static boolean methodCouldModify(CtMethod ctMethod) {

        if (Modifier.isNative(ctMethod.getModifiers())) {
            return false;
        }

        if (AgentConfig.NotModifyMethodNames.contains(ctMethod.getName())) {
            return false;
        }

        return true;
    }




    public static boolean classCouldModify(CtClass ctClass) {
        if (ctClass.isInterface()) {
            return false;
        }

        return classCouldModify(ctClass.getName());
    }

    public static boolean classCouldModify(String className) {

//        if(className.startsWith("com/zhiyin/jagent") || className.startsWith("com.zhiyin.jagent")){
//            return false;
//        }
//        if (className.startsWith("com/zhiyin") || className.startsWith("com.zhiyin")) {
//            return true;
//        }

        // 白名单策略
        if( AgentConfig.PackageStrategy.equals( AgentConfig.PackageStrategyWhite) ){
            for (String tmp : AgentConfig.IncludePackages) {
                if( className.startsWith(tmp) ||  className.replace(".","/").startsWith(tmp) ){
                    return true;
                }
            }
            return false;
        }

        // 默认黑名单
        for (String excludePackage : AgentConfig.ExcludePackages) {
            if( className.startsWith(excludePackage) ||  className.replace(".","/").startsWith(excludePackage) ){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String clazz= "java.lang.Long";
        System.out.println(classCouldModify( clazz ));



        System.out.println(AgentConfig.ExcludePackages);
        System.out.println(classCouldModify(""));

    }
}