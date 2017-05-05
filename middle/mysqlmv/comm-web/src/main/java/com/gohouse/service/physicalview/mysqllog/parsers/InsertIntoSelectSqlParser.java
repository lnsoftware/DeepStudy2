package com.gohouse.service.physicalview.mysqllog.parsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;
import com.gohouse.service.physicalview.mapping.PhysicalViewMappingFactory;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;
import com.gohouse.service.physicalview.mysqllog.DataUpdateInfo;
import com.mysql.jdbc.ResultSetMetaData;

public class InsertIntoSelectSqlParser implements MysqlSqlHandler {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private Set<String> updateColumns = null;
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	private String database;
	
	public InsertIntoSelectSqlParser(MysqlConnectionConfig mysqlConnectionConfig){
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
		
		List<Map<String, Object>> columnValues = getColumnValues(database, sql);
		
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
	
	private List<Map<String, Object>> getColumnValues(String database, String sql){
		
	
		String columnStr = sql.replaceAll("\\(*\\s*select\\s+", "♣");
		
		columnStr = columnStr.substring(0, columnStr.indexOf("♣"));
		
		String[] columns = null;
		
		if(columnStr.contains("(")){
			columnStr = columnStr.substring(columnStr.indexOf("(")+1, columnStr.indexOf(")"));
		
			columns = columnStr.split(",");
		}
		
		sql = sql.replaceAll("\\(*\\s*select\\s+", "♣");
		
		sql = "select " + sql.substring(sql.indexOf("♣")+1, sql.length());
		
		return queryInsertResult(database, sql, columns);
	}
	
	private List<Map<String, Object>> queryInsertResult(String database, String sql, String[] columns){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		try {
			
			Class.forName(mysqlConnectionConfig.getDriver());
			
			String url = "jdbc:mysql://"+mysqlConnectionConfig.getHost()+"/"+database+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			conn = DriverManager.getConnection(url, mysqlConnectionConfig.getUsername(), mysqlConnectionConfig.getPassword());
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			
			if(columns == null)
				columns = getColumns(rs);
				
			int length = columns.length;
				
			while(rs.next()){
				
				Map<String, Object> result = new HashMap<String, Object>();
				
				for(int i=0;i<length;i++){
					
					String col = columns[i];
					
					Object value = rs.getObject(col);
					
					result.put(col, value);
					
					updateColumns.add(col);
				}
				
				results.add(result);
			}
		
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
		
		return results;
		
	}
	
	private String[] getColumns(ResultSet rs) throws SQLException{
		
		ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
		
		int col = meta.getColumnCount();
		
		String[] columns = new String[col];
		
		for(int i=0;i<col;i++){
			columns[i] = meta.getColumnName(i+1);
		}
		
		return columns;
	}
	
	@Override
	public Map<String, String> getDatabaseTable(String sql){
		
		String str = sql.replaceAll("insert into ", "").replaceAll("INSERT INTO ", "");
		
		if(str.contains("("))
			str = str.substring(0, str.indexOf("("));
		
		str = str.replaceAll("\\s+", "♣");
		
		str = str.substring(0, str.indexOf("♣"));
			
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
