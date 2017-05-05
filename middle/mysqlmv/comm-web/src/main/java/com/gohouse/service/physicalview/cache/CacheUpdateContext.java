package com.gohouse.service.physicalview.cache;

import java.util.List;
import java.util.Map;

public class CacheUpdateContext {
	
	/**
	 * 变更类型(1-insert，2-update，3-delete).
	 */
	private int updateType;
	
	/**
	 * 变更数据库
	 */
	private List<String> dbs;
	
	/**
	 * 变更数据库对应的数据表
	 */
	private Map<String, String> dbTables;
	
	/**
	 * 变更数据表对应的ID
	 */
	private Map<String, Object> tableIds;
	
	/**
	 * 变更类型(1-insert，2-update，3-delete).
	 */
	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	/**
	 * 变更数据库
	 */
	public List<String> getDbs() {
		return dbs;
	}

	public void setDbs(List<String> dbs) {
		this.dbs = dbs;
	}

	/**
	 * 变更数据库对应的数据表
	 */
	public Map<String, String> getDbTables() {
		return dbTables;
	}

	public void setDbTables(Map<String, String> dbTables) {
		this.dbTables = dbTables;
	}

	/**
	 * 变更数据表对应的ID
	 */
	public Map<String, Object> getTableIds() {
		return tableIds;
	}

	public void setTableIds(Map<String, Object> tableIds) {
		this.tableIds = tableIds;
	}
}
