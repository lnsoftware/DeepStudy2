package com.hg.test.api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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

public class ExcelUtil {
    public static void main(String[] args){


        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        long start = new Date().getTime();
        List<MsgClient> list = ExcelImportUtil.importExcel(
                new File(PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx")),
                MsgClient.class, params);
        System.out.println(new Date().getTime() - start);
        System.out.println(list.size());
        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));



//        ImportParams params = new ImportParams();
//        params.setTitleRows(1);
//
        List<RestApiInfo> objects = ExcelImportUtil.importExcel(new File( PoiPublicUtil.getWebRootPath("jmeter.xlsx") ), RestApiInfo.class,params);
//
        System.out.println(objects.size());
//
        System.out.println( JSON.toJSONString(objects) );
        log.info(JSON.toJSONString(objects));

    }



}
