package com.zhiyin.canal.processor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.zhiyin.canal.model.RowBinlog;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangqinghui on 2016/12/12.
 */
@Slf4j
public class ProcessorDispatcher {

    public static Map<String,ProcessorMethodWrapper> processorMethodWrapperMap = Maps.newConcurrentMap();

    private static ProcessorDispatcher singleton;
    private ProcessorDispatcher (){}
    public static ProcessorDispatcher getSingleton() {
        if (singleton == null) {                         //Single Checked
            synchronized (ProcessorDispatcher.class) {
                if (singleton == null) {                 //Double Checked
                    singleton = new ProcessorDispatcher();
                }
            }
        }
        return singleton ;
    }

    public static void init(String packageName){
        getSingleton().init2(packageName);
    }

    public static void dispatcher(RowBinlog op){

       if( processorMethodWrapperMap.containsKey(op.getOpUrl()) == false){
           log.error(op.getOpUrl() + " has not op method!");
           return;
       }

        ProcessorMethodWrapper method = processorMethodWrapperMap.get(op.getOpUrl());

        try {
            method.getMethod().invoke(method.getClassObj(),op);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void init2(String packageName){
        Reflections reflections = new Reflections(packageName);

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ProcessorMapping.class);

        for (Class<?> aClass : annotated) {
            ProcessorMapping classMapping = aClass.getAnnotation(ProcessorMapping.class);
            if(classMapping == null){
                continue;
            }
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                ProcessorMapping methodMapping = method.getAnnotation(ProcessorMapping.class);
                if(methodMapping == null){
                    continue;
                }

                RowOperation op = mapInfo(classMapping, methodMapping);


                ProcessorMethodWrapper wrapper = new ProcessorMethodWrapper();
                wrapper.setMethod(method);
                wrapper.setClazz(aClass);

                try {
                    wrapper.setClassObj(aClass.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                processorMethodWrapperMap.put(op.getOpUrl(),wrapper);
                log.info(JSON.toJSONString(op));

            }
        }
    }

    public RowOperation mapInfo(ProcessorMapping mapping,ProcessorMapping methodMapping){
        RowOperation op = new RowOperation();
        op.setDatabaseName(mapping.databaseName());
        op.setTableName(mapping.tableName());
        op.setRowOpType(mapping.rowOpType());

        if(Strings.isNullOrEmpty(methodMapping.databaseName()) || methodMapping.databaseName().equals(ProcessorMapping.NULL)){

        }else{
            op.setDatabaseName(methodMapping.databaseName());
        }

        if(Strings.isNullOrEmpty(methodMapping.tableName()) || methodMapping.tableName().equals(ProcessorMapping.NULL)){

        }else{
            op.setDatabaseName(methodMapping.tableName());
        }

//        if(Strings.isNullOrEmpty(methodMapping.rowOpType()) || methodMapping.rowOpType().equals(ProcessorMapping.NULL)){
//
//        }else{
//            op.setRowOpType(methodMapping.rowOpType());
//        }
        op.setRowOpType(methodMapping.rowOpType());


        return op;
    }

//    public RowOp

}
