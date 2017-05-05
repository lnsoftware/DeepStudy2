package com.hg.test.api;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;

/**
 * Created by hg on 2016/6/2.
 */
@Getter
@Setter
public class RestApiInfo implements java.io.Serializable {

    @Excel(name = "name")
    private String name;

    @Excel(name = "port")
    private Integer port;

    @Excel(name = "domain")
    private String domain;

    @Excel(name = "body")
    private String body;

    @Excel(name = "path")
    private String path;

    @Excel(name = "method")
    private String method;


    @Excel(name = "pre_path")
    private String prePath;

    @Excel(name = "produces")
    private String produces;


}
