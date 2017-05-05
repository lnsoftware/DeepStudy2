package com.gohouse.service.physicalview.task;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;

public class DataUpdateContext {
	
	private String targetDb;
	
	private String targetTable;
	
	private String sourceTable;
	
	private String targetTableColumn;
	
	private String sourceTableColumn;
	
	private String targetTableRelationColumn;
	
	private String sourceTableRelationColumn;

	/**
	 * 数据库名
	 */
	private String dbName;
	
	/**
	 * 更新表名
	 */
	private String tableName;

	/**
	 * 更新的字段-值
	 */
	private List<Map<String, Object>> updataDatas;
	
	/**
	 * 
	 */
	List<PhysicalViewMapping> mappings;
	
	private Set<String> updateColumns;
	
	private MysqlConnectionConfig mysqlConnectionConfig;

	public String getTargetDb() {
		return targetDb;
	}

	public void setTargetDb(String targetDb) {
		this.targetDb = targetDb;
	}

	public String getTargetTable() {
		return targetTable;
	}

	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}

	public String getSourceTable() {
		return sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}

	public String getTargetTableColumn() {
		return targetTableColumn;
	}

	public void setTargetTableColumn(String targetTableColumn) {
		this.targetTableColumn = targetTableColumn;
	}

	public String getSourceTableColumn() {
		return sourceTableColumn;
	}

	public void setSourceTableColumn(String sourceTableColumn) {
		this.sourceTableColumn = sourceTableColumn;
	}

	public String getTargetTableRelationColumn() {
		return targetTableRelationColumn;
	}

	public void setTargetTableRelationColumn(String targetTableRelationColumn) {
		this.targetTableRelationColumn = targetTableRelationColumn;
	}

	public String getSourceTableRelationColumn() {
		return sourceTableRelationColumn;
	}

	public void setSourceTableRelationColumn(String sourceTableRelationColumn) {
		this.sourceTableRelationColumn = sourceTableRelationColumn;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
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

	public List<PhysicalViewMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<PhysicalViewMapping> mappings) {
		this.mappings = mappings;
	}

	public Set<String> getUpdateColumns() {
		return updateColumns;
	}

	public void setUpdateColumns(Set<String> updateColumns) {
		this.updateColumns = updateColumns;
	}

	public MysqlConnectionConfig getMysqlConnectionConfig() {
		return mysqlConnectionConfig;
	}

	public void setMysqlConnectionConfig(MysqlConnectionConfig mysqlConnectionConfig) {
		this.mysqlConnectionConfig = mysqlConnectionConfig;
	}

	
}
