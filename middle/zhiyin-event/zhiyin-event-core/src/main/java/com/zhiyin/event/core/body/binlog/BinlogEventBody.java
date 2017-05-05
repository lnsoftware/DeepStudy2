package com.zhiyin.event.core.body.binlog;

import com.zhiyin.event.core.body.BasicEventBody;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BinlogEventBody extends BasicEventBody {
	private Long dataId;

    private String dbName;
    private String tableName;
	
	private BinlogOpType opType;
	
	private  Map<String,Object> oldRowDataMap; // 旧的行值
	private  Map<String,Object> newRowDataMap; // 新的行值



	
}
