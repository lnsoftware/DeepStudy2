package com.gohouse.service.physicalview.mysqllog;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;

public class DataUpdateInfo {

	/**
	 * 更新类型:1-新增，2-修改，3-删除
	 */
	private int updateType;
	
	/**
	 * 数据库名
	 */
	private String dbName;
	
	/**
	 * 更新表名
	 */
	private String tableName;
	
	/**
	 * 更新字段
	 */
	private Set<String> updateColumns;
	
	private List<Map<String, Object>> updataDatas;
	
	private String updateCondition;
	
	List<PhysicalViewMapping> mappings;
	
	List<PhysicalViewMapping> affiliatedMappings;

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	

	public List<Map<String, Object>> getUpdataDatas() {
		return updataDatas;
	}

	public void setUpdataDatas(List<Map<String, Object>> updataDatas) {
		this.updataDatas = updataDatas;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public List<PhysicalViewMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<PhysicalViewMapping> mappings) {
		this.mappings = mappings;
	}

	public String getUpdateCondition() {
		return updateCondition;
	}

	public void setUpdateCondition(String updateCondition) {
		this.updateCondition = updateCondition;
	}

	public Set<String> getUpdateColumns() {
		return updateColumns;
	}

	public void setUpdateColumns(Set<String> updateColumns) {
		this.updateColumns = updateColumns;
	}

	public List<PhysicalViewMapping> getAffiliatedMappings() {
		return affiliatedMappings;
	}

	public void setAffiliatedMappings(List<PhysicalViewMapping> affiliatedMappings) {
		this.affiliatedMappings = affiliatedMappings;
	}
	
	
	
}
