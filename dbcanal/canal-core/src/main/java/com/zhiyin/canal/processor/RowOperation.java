package com.zhiyin.canal.processor;

import com.zhiyin.canal.model.RowOpType;
import lombok.Data;

/**
 * Created by wangqinghui on 2016/12/12.
 */
@Data
public class RowOperation {

    private String databaseName;
    private String tableName;
//    private Integer rowOpCode;

    private RowOpType rowOpType;


    public String getOpUrl(){
        return databaseName + "/" + tableName + "/" + rowOpType.getValue();
    }

}
