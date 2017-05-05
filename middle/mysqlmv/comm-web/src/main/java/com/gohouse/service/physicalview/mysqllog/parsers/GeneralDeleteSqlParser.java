package com.gohouse.service.physicalview.mysqllog.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;
import com.gohouse.service.physicalview.mapping.PhysicalViewMappingFactory;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;
import com.gohouse.service.physicalview.mysqllog.DataUpdateInfo;

public class GeneralDeleteSqlParser implements MysqlSqlHandler {
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	private String database;
		
	public GeneralDeleteSqlParser(MysqlConnectionConfig mysqlConnectionConfig){
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
		
		DataUpdateInfo cxt = new DataUpdateInfo();
		cxt.setDbName(database);
		cxt.setUpdateType(3);
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
	
	@Override
	public Map<String, String> getDatabaseTable(String sql){
	
		String str = sql.replaceAll("delete\\s+", "").replaceAll("DELETE\\s+", "").replaceAll("from\\s+", "").replaceAll("FROM\\s+", "");
			
		str = str.replaceAll("`", "").trim();
		
		str = str.replaceAll("\\s+", "♣");
		
		str = str.split("♣")[0];
		
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
