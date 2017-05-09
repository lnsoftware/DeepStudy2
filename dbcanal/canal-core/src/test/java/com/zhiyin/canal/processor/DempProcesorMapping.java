package com.zhiyin.canal.processor;

import com.zhiyin.canal.model.RowBinlog;
import com.zhiyin.canal.model.RowOpType;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wangqinghui on 2016/12/12.
 */

@Slf4j
@ProcessorMapping(databaseName = "zhiyin",tableName = "userinfo")
public class DempProcesorMapping {

    @ProcessorMapping(rowOpType = RowOpType.DELETE)
    public void del(RowBinlog rowBinlog){
        log.info("del "+ rowBinlog.getTableName() + " " + rowBinlog.getRowId());
    }


    @ProcessorMapping(rowOpType = RowOpType.INSERT)
    public void add(RowBinlog rowBinlog){
        log.info("del "+ rowBinlog.getTableName() + " " + rowBinlog.getRowId());
    }



}
