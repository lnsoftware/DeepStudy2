package com.hg.db.mysql.binlog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.google.common.collect.Maps;

public class TableDataProcessUtil {
	
	private static Logger log = LoggerFactory.getLogger(TableDataProcessUtil.class);

    Map<String,String> tableProcessMap = new HashMap<String,String>(){
        {
            put("test.binloguser.delete","www.baidu.com");
        }
    };
	
	public static void record(String tableInfo,RowData rowData, EventType eventType) {

		TableDataEventInfo eventInfo = new TableDataEventInfo();
		if (eventType == EventType.DELETE) {
			Map<String, Object> map = getColValMap(rowData
					.getBeforeColumnsList());
			eventInfo.setDataId(Long.valueOf((String) map.get("id")));
			eventInfo.setOldRowDataMap(map);
			eventInfo.setEventType(TableDataEventType.DELETE);

		} else if (eventType == EventType.INSERT) {

			Map<String, Object> newMap = getColValMap(rowData
					.getAfterColumnsList());
			eventInfo.setDataId(Long.valueOf((String) newMap.get("id")));
			eventInfo.setNewRowDataMap(newMap);

			eventInfo.setEventType(TableDataEventType.INSERT);

		} else if (eventType == EventType.UPDATE) {
			Map<String, Object> oldMap = getColValMap(rowData
					.getBeforeColumnsList());
			Map<String, Object> newMap = getColValMap(rowData
					.getAfterColumnsList());
			eventInfo.setDataId(Long.valueOf((String) newMap.get("id")));
			eventInfo.setOldRowDataMap(oldMap);
			eventInfo.setNewRowDataMap(newMap);
			eventInfo.setEventType(TableDataEventType.UPDATE);
		} else {

		}
        eventInfo.setTableInfo(tableInfo);
        
		log.info("row data event info:"+JSON.toJSONString(eventInfo));
		
	}
	
	
	public static Map<String,Object> getColValMap(List<Column> columns) {
        Map<String,Object> map = Maps.newHashMap();
		for (Column column : columns) {
			map.put(column.getName(), column.getValue());
        }
		return map;
    
	}
	
    protected static void printColumn(List<Column> columns) {
        for (Column column : columns) {
          if(column.getName().equals("id")){
        	  column.getValue();
          }
        }
    }

}
