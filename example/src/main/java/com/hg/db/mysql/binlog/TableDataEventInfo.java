package com.hg.db.mysql.binlog;

import java.util.Map;

public class TableDataEventInfo {
	private Long dataId;
//	private Long oldId;
//	private Long newId;

    private String tableInfo;
	
	private TableDataEventType eventType;
	
	private  Map<String,Object> oldRowDataMap; // 旧的行值
	private  Map<String,Object> newRowDataMap; // 新的行值


    public String getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(String tableInfo) {
        this.tableInfo = tableInfo;
    }

    public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public TableDataEventType getEventType() {
		return eventType;
	}

	public void setEventType(TableDataEventType eventType) {
		this.eventType = eventType;
	}

	public Map<String, Object> getOldRowDataMap() {
		return oldRowDataMap;
	}

	public void setOldRowDataMap(Map<String, Object> oldRowDataMap) {
		this.oldRowDataMap = oldRowDataMap;
	}

	public Map<String, Object> getNewRowDataMap() {
		return newRowDataMap;
	}

	public void setNewRowDataMap(Map<String, Object> newRowDataMap) {
		this.newRowDataMap = newRowDataMap;
	}
	
	
	
	
	
	
}
