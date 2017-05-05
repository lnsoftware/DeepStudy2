package com.gohouse.service.physicalview.mysqllog.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;
import com.gohouse.service.physicalview.mapping.PhysicalViewMappingFactory;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;
import com.gohouse.service.physicalview.mysqllog.DataUpdateInfo;

public class GeneralInsertIntoSqlParser implements MysqlSqlHandler {
	
	private Set<String> updateColumns = null;
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	private String database;
	
	public GeneralInsertIntoSqlParser(MysqlConnectionConfig mysqlConnectionConfig){
		this.mysqlConnectionConfig = mysqlConnectionConfig;
	}

	@Override
	public DataUpdateInfo execute(String sql) throws PhysicalViewException {
		
		database = sql.substring(sql.indexOf("[")+1, sql.indexOf("]"));
		
		sql = sql.substring(sql.indexOf("]")+1, sql.length());
		
		Map<String, String> dbTable = getDatabaseTable(sql);
		
		String db = dbTable.get("db");
		if(db != null && !"".equals(db))
			database = db;
		
		List<PhysicalViewMapping> mappings = getPhysicalViewMapping(dbTable.get("table"));
		if(mappings == null)
			return null;
		
		List<Map<String, Object>> columnValues = getColumnValues(sql);
		
		DataUpdateInfo cxt = new DataUpdateInfo();
		cxt.setDbName(database);
		cxt.setUpdateType(1);
		cxt.setUpdateColumns(updateColumns);
		cxt.setUpdataDatas(columnValues);
		cxt.setTableName(dbTable.get("table"));
		cxt.setMappings(mappings);
	
		return cxt;
		
	}
	
	private List<PhysicalViewMapping> getPhysicalViewMapping(String tableName){
		
		PhysicalViewMappingFactory physicalViewMappingFactory = 
				PhysicalViewMappingFactory.init(mysqlConnectionConfig);
		
		List<PhysicalViewMapping> mappings = physicalViewMappingFactory.getToUpdateSubjectPhysicalView(database, tableName);
		
		return mappings;
	}
	
	private List<Map<String,  Object>> getColumnValues(String sql){
		
		String columnStr = sql.substring(sql.indexOf("(")+1, sql.indexOf(")"));
		
		String valueStr = sql.substring(sql.lastIndexOf("(")+1, sql.lastIndexOf(")"));
		
		columnStr = columnStr.trim();
		
		valueStr = valueStr.trim().replaceAll("    ", "");
		
		String[] columns = columnStr.split(",");
		String[] values = valueStr.split(",");
		
		List<Map<String,  Object>> colmunValues = new ArrayList<Map<String,Object>>();
		
		Map<String,  Object> map = new HashMap<String, Object>();
		
		updateColumns = new HashSet<String>();
		
		for(int i=0;i<columns.length;i++){
			
			String col = columns[i].replaceAll("`", "").trim();
		
			map.put(col, values[i]);
			
			updateColumns.add(col);
		}
		
		colmunValues.add(map);
		
		return colmunValues;
	}
	
	@Override
	public Map<String, String> getDatabaseTable(String sql){
		
		String str = sql.substring(0, sql.indexOf("("));
		
		str = str.replaceAll("insert into", "").replaceAll("INSERT INTO", "");
			
		str = str.replaceAll("`", "").trim();
		
		Map<String, String> dbTable = new HashMap<String, String>();
		
		if(str.contains(".")){
			String[] strs = str.split("\\.");
			
			dbTable.put("db", strs[0]);
			dbTable.put("table", strs[1]);
			
		}else{
			dbTable.put("table", str);
		}
		
		return dbTable;
		
	}

}
