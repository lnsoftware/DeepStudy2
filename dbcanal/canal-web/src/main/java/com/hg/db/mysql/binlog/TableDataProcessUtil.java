package com.hg.db.mysql.binlog;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.hg.dbcanal.process.BinlogProcessor;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.body.binlog.BinlogOpType;
import com.zhiyin.http.factory.HttpRequestFactory;
import com.zhiyin.http.factory.HttpUrlFactory;
import com.zhiyin.queue.core.event.AliQueueEvent;
import com.zhiyin.queue.core.factory.AliQueueEventFactory;
import com.zhiyin.utils.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TableDataProcessUtil {

    Map<String,String> tableProcessMap = new HashMap<String,String>(){
        {
            put("test.binloguser.delete","www.baidu.com");
        }
    };
	
	public static void record(String dbName,String tableName,RowData rowData, EventType eventType) {

        String tableInfo = dbName + "." + tableName;
//        if(!tableInfo.equals("zhiyin.zhiyin_content_basic_content")){
//            log.warn("skip table "+tableInfo);
//        }

		BinlogEventBody eventInfo = new BinlogEventBody();

		if (eventType == EventType.DELETE) {
			Map<String, Object> map = getColValMap(rowData
					.getBeforeColumnsList());
            if( !containId(map)){
                log.error("event info not contain id.");
                return;
            }
			eventInfo.setDataId(Long.valueOf((String) map.get("id")));
			eventInfo.setOldRowDataMap(map);
			eventInfo.setOpType(BinlogOpType.DELETE);

		} else if (eventType == EventType.INSERT) {

			Map<String, Object> newMap = getColValMap(rowData
					.getAfterColumnsList());
            if( !containId(newMap)){
                log.error("event info not contain id.");
                return;
            }
			eventInfo.setDataId(Long.valueOf((String) newMap.get("id")));
			eventInfo.setNewRowDataMap(newMap);

			eventInfo.setOpType(BinlogOpType.INSERT);

		} else if (eventType == EventType.UPDATE) {
			Map<String, Object> oldMap = getColValMap(rowData
					.getBeforeColumnsList());
			Map<String, Object> newMap = getColValMap(rowData
					.getAfterColumnsList());
            if( !containId(newMap)){
                log.error("event info not contain id.");
                return;
            }

            // del_status == 1 代表删除
            if( isFakeDel(oldMap,newMap) ){
                eventInfo.setDataId(Long.valueOf((String) newMap.get("id")));
                eventInfo.setOpType(BinlogOpType.DELETE);
            }else{
                eventInfo.setDataId(Long.valueOf((String) newMap.get("id")));
                eventInfo.setOpType(BinlogOpType.UPDATE);
            }

		} else {
            log.warn("not process");
            return;
		}
        eventInfo.setTableName(tableName);
        eventInfo.setDbName(dbName);


        // 本地(通过binlog)处理事件
        BinlogProcessor.process(eventInfo);


        // 20160605废除事件队列
        // 构造ali队列事件
   /*     AliQueueEvent queueEvent = AliQueueEventFactory.genDbOpEvent(eventInfo);
        log.info("ali queue event:" + JSONUtil.toJson(queueEvent));

        try {
            String resp = HttpRequestFactory.post(HttpUrlFactory.get("ali.dbqueue.put"), queueEvent);
            log.info("put event result:{}",resp);
        } catch (Exception e) {
            log.error("http req error:{}" ,JSON.toJSONString(queueEvent),e);
        }
*/

        log.info("row data event info:"+JSON.toJSONString(eventInfo));
	}
	
	public static boolean containId(Map<String,Object> map){
        if(!map.containsKey("id")){
            return false;
        }
        String val = (String) map.get("id");
        if(Strings.isNullOrEmpty(val)){
            return  false;
        }

        if(! isNumeric(val)){
            return false;
        }

        return true;

    }

    public static boolean isNumeric(String str) {
        String regEx = "^-?[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        }
        else {
            return false;
        }
    }


    public static Boolean isFakeDel(Map<String,Object> oldMap, Map<String,Object> newMap){
        Boolean fakeDel = false;
        // 之前未删除，现在删除
        if( isDel(oldMap) == false && isDel(newMap) == true ){
            fakeDel = true;
        }
        return fakeDel;
    }

    public static boolean isDel(Map<String,Object> map){
        Object del = map.get("del_status");
        if(del == null){
            return false;
        }
        String delStr = String.valueOf(del);
        if( delStr.equals("1") ){
            return true;
        }
        return false;
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


    public static void main(String[] args){

        Map<String,Object> map = Maps.newHashMap();
        map.put("del_status","1");

        System.out.println("val:"+isDel(map));

    }


}
