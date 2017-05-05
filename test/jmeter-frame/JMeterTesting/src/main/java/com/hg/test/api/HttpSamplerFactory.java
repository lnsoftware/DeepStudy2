package com.hg.test.api;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.testelement.TestElement;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.util.PoiPublicUtil;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by hg on 2016/6/2.
 */
@Slf4j
public class HttpSamplerFactory {

    public static List<HTTPSamplerProxy> genByExcel(String fileName){

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        long start = new Date().getTime();

        List<RestApiInfo> list = ExcelImportUtil.importExcel(new File( PoiPublicUtil.getWebRootPath("jmeter.xlsx") ), RestApiInfo.class,params);


        System.out.println(new Date().getTime() - start);
        System.out.println(list.size());

        System.out.println(JSON.toJSONString(list));

        return genByList(list);


    }

    public static List<HTTPSamplerProxy> genByList(List<RestApiInfo> apiInfoList){

        List<HTTPSamplerProxy> retList = Lists.newArrayList();
        for(RestApiInfo tmp : apiInfoList){

            String method = tmp.getMethod().toUpperCase();
            HTTPSamplerProxy examplecomSampler = new HTTPSamplerProxy();
            examplecomSampler.setDomain( tmp.getDomain() );
            examplecomSampler.setPort( tmp.getPort() );
            examplecomSampler.setPath( tmp.getPrePath() + tmp.getPath());
            examplecomSampler.setMethod( method );
            examplecomSampler.setName( tmp.getName() );

            examplecomSampler.setContentEncoding("utf-8");


            HeaderManager man = new HeaderManager() ;
            man.add(new Header("content-type","application/json;charset=UTF-8" ) );
            examplecomSampler.setHeaderManager(man);


            if( !Strings.isNullOrEmpty(tmp.getProduces())){
//                examplecomSampler.setProperty("Content-Type",tmp.getProduces());
            }

            if( method.equals("POST") ){
                examplecomSampler.setPostBodyRaw(true);
                examplecomSampler.addArgument("",tmp.getBody(),"=");
            }

            examplecomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
            examplecomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());

            retList.add(examplecomSampler);
        }

        return retList;



    }

}
