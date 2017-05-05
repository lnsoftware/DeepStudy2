package com.gohouse.service.physicalview.mysqllog.parsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;
import com.gohouse.service.physicalview.mapping.PhysicalViewMappingFactory;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;
import com.gohouse.service.physicalview.mysqllog.DataUpdateInfo;

public class GeneralUpdateSqlParser implements MysqlSqlHandler {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	private String database;
	
	private String tableAliases="";
	
	public GeneralUpdateSqlParser(MysqlConnectionConfig mysqlConnectionConfig){
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
		
		Set<String> updateColumns = getUpdateColumn(sql);
		
		List<PhysicalViewMapping> mappings = getPhysicalViewMapping(dbTable.get("table"), updateColumns);
	
		if(mappings == null || mappings.size() == 0)
			return null;
		
		Set<String> foreignKeys = getForeignKey(mappings);
		
		List<Map<String, Object>> columnValues = getColumnValues(sql, updateColumns, foreignKeys);
		
		DataUpdateInfo cxt = new DataUpdateInfo();
		cxt.setDbName(database);
		cxt.setUpdateType(2);
		cxt.setUpdateColumns(updateColumns);
		cxt.setUpdataDatas(columnValues);
		cxt.setTableName(dbTable.get("table"));
		cxt.setMappings(mappings);
	
		return cxt;

	}
	
	private Set<String> getForeignKey(List<PhysicalViewMapping> mappings){
		
		Set<String> foreignKeys = new HashSet<String>();
		
		for(PhysicalViewMapping mapping : mappings){
			foreignKeys.add(mapping.getSource_table_relation_column());
		}
		
		return foreignKeys;
	}
	
	private List<PhysicalViewMapping> getPhysicalViewMapping(String tableName, Set<String> updateColumns){
		
		PhysicalViewMappingFactory physicalViewMappingFactory = 
				PhysicalViewMappingFactory.init(mysqlConnectionConfig);
		
		List<PhysicalViewMapping> mappings = physicalViewMappingFactory.getToUpdatePhysicalView(database, tableName, updateColumns);
		
		return mappings;
	}
	
	private Set<String> getUpdateColumn(String sql){
		
		Set<String> columns = new HashSet<String>();
 		
		String sql2 = sql;
		
		if(sql2.matches(".+\\s+set\\s+.+"))
			sql2 = sql2.replaceAll("\\s+set\\s+", "♣");
		if(sql2.matches(".+\\s+SET\\s+.+"))
			sql2 = sql2.replaceAll("\\s+SET\\s+", "♣");
	
		int endIndex = sql2.length();
		
		if(sql2.matches(".+\\s+where\\s+.+")){
			sql2 = sql2.replaceAll("\\s+where\\s+", "♣");
			endIndex = sql2.lastIndexOf("♣");
		}
		if(sql2.matches(".+\\s+WHERE\\s+.+")){
			sql2 = sql2.replaceAll("\\s+WHERE\\s+", "♣");
			endIndex = sql2.lastIndexOf("♣");
		}
		
		String str = sql2.substring(sql2.indexOf("♣")+1, endIndex).trim();
		
		String[] strs = str.split(",");
		
		for(String s : strs){
			
			String[] ss = s.split("=");
			
			String col = ss[0].replaceAll("`", "").trim().replaceAll("\\s+", "");
			
			if(col.contains(".")){
				
				String[] cols = col.split("\\.");
				
				tableAliases = cols[0]+".";
				col = cols[1];
			}
			
			columns.add(col);
		}
		
		return columns;
	}
	
	@Override
	public Map<String, String> getDatabaseTable(String sql){
		
		String str = sql.replaceAll("update\\s+", "").replaceAll("UPDATE\\s+", "");
		
		str = str.replaceAll("\\s+", "♣");
		
		str = str.substring(0, str.indexOf("♣"));
			
		str = str.replaceAll("`", "").trim();
		
		String[] ss = str.split("\\s+");
	
		str = ss[0];
				
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
	
	private List<Map<String, Object>> getColumnValues(String sql, Set<String> updateColumns, Set<String> foreignKeys){
		
		int startPos = "update".length();
		
		String sql2 = sql;
		
		if(sql2.matches(".+\\s+set\\s+.+"))
			sql2 = sql2.replaceAll("\\s+set\\s+", "♣");
		if(sql2.matches(".+\\s+SET\\s+.+"))
			sql2 = sql2.replaceAll("\\s+SET\\s+", "♣");
		
		String table = sql2.substring(startPos, sql2.indexOf("♣"));
		
		String where = "";

		if(sql2.matches(".+\\s+where\\s+.+")){
			
			sql2 = sql2.replaceAll("\\s+where\\s+", "♣");
			
			where = sql2.substring( sql2.lastIndexOf("♣")+1, sql2.length());
		}else if(sql2.matches(".+\\s+WHERE\\s+.+")){
			sql2 = sql2.replaceAll("\\s+WHERE\\s+", "♣");
			
			where = sql2.substring( sql2.lastIndexOf("♣")+1, sql2.length());
		}
		
		String querySql = buildSql(table, where, updateColumns, foreignKeys);
		
		return queryColumnValues(querySql, updateColumns, foreignKeys);
		
	}

	private List<Map<String, Object>> queryColumnValues(String sql, Set<String> updateColumns, Set<String> foreignKeys){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Map<String, Object>> columnValues = new ArrayList<Map<String, Object>>();
	
		try {
			
			updateColumns.addAll(foreignKeys);
			
			Class.forName(mysqlConnectionConfig.getDriver());
			
			String url = "jdbc:mysql://"+mysqlConnectionConfig.getHost()+"/"+database+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			conn = DriverManager.getConnection(url, mysqlConnectionConfig.getUsername(), mysqlConnectionConfig.getPassword());
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				Iterator<String> it = updateColumns.iterator();
				
				while(it.hasNext()){
					String col = it.next();
					
					map.put(col, rs.getObject(col));
				}
				
				columnValues.add(map);
			}
			
			return columnValues;
		} catch (SQLException e) {
			logger.error(sql, e);
		} catch (ClassNotFoundException e) {
			logger.error(sql, e);
		} finally{
			try {
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	private String buildSql(String table, String where, Set<String> updateColumns, Set<String> foreignKeys){
		
		String sql = "select ";
		
		updateColumns.addAll(foreignKeys);
		
		Iterator<String> it = updateColumns.iterator();
		
		while(it.hasNext()){
			sql += tableAliases + it.next();
			
			if(it.hasNext())
				sql += ", ";
		}
		
		if(sql.endsWith(","))
			sql = sql.substring(0, sql.length()-1);
		
		sql += " from " + table + " where " +where;
		
		return sql;
	}
	
	public static void main(String[] args){
		
		
	}
}
