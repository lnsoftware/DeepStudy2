package com.gohouse.service.physicalview.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gohouse.service.physicalview.cache.CacheUpdateContext;
import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;
import com.gohouse.service.physicalview.mapping.PhysicalViewMappingFactory;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;
import com.gohouse.service.physicalview.task.DataUpdateContext;

/**
 * 默认的更新视图处理器.
 * @author allen
 *
 */
public class DefaultPhysicalViewHandler implements PhysicalViewHandler {

	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public CacheUpdateContext insert(DataUpdateContext ctx) throws PhysicalViewException {
		
		List<Map<String, Object>> updataDatas = ctx.getUpdataDatas();
		
		String viewDb = ctx.getTargetDb();
		String viewTable = ctx.getTargetTable();
		String viewColumn = ctx.getTargetTableColumn();
		String viewSourceColumn = ctx.getSourceTableColumn();
		MysqlConnectionConfig mysql = ctx.getMysqlConnectionConfig();
		
		List<PhysicalViewMapping> mappings = ctx.getMappings();
		
		String[] viewColumns = viewColumn.split(",");
		
		int viewColumnLength = viewColumns.length;
		
		String[] viewSourceColumns = viewSourceColumn.split(",");
		
		for(int i=0;i<updataDatas.size();i++){
			
			Map<String, Object> updataData = updataDatas.get(i);
			
			if(isExsit(updataData, ctx, mysql))
				continue;
			
			String sql = "insert into "+viewTable +"(";
			
			String values = " values (";
			
			for(int j=0;j<viewColumnLength;j++){
				
				Object newValue = updataData.get(viewSourceColumns[j]);
				
				sql += viewColumns[j]+",";
				
				values += newValue+",";
			}
			
			if(mappings != null && mappings.size() > 0){
				for(PhysicalViewMapping mapping : mappings){
					
					String SubjectRelation = mapping.getTarget_table_relation_column();
					
					String affiliatedRelation = mapping.getSource_table_relation_column();
					
					Object SubjectRelationValue = updataData.get(SubjectRelation);
					if(SubjectRelationValue == null)
						continue;
					
					String sourceTable = mapping.getSource_table();
					
					if(!sourceTable.contains(".")){
						logger.error("未指定数据库！"+sourceTable);
						continue;
					}
					
					String[] sourceTables = sourceTable.split("\\.");
					
					String db = sourceTables[0];
					String table = sourceTables[1];
					
					String[] sourceTableColumns = mapping.getSource_table_column().split(",");
					
					String[] tableColumns = mapping.getTarget_table_column().split(",");
					
					int sourceTableColumnLength = sourceTableColumns.length;
					
					int tableColumnLength = tableColumns.length;
					
					String querySql = "select ";
					
					for(int k=0;k<sourceTableColumnLength;k++){
						
						querySql += sourceTableColumns[k];
						
						if(k+1 < sourceTableColumnLength)
							querySql += ", ";
					}
					
					querySql += " from "+table+" where "+affiliatedRelation+"=";
					
				
					querySql += SubjectRelationValue;
				
					
					List<Object> results = excuteQuerySql(db,  mysql, querySql, sourceTableColumnLength);
					
					for(int m=0;m<tableColumnLength;m++){
						
						sql += tableColumns[m]+",";
						
						Object value = results.get(m);
						
						if(value instanceof String)
							values += "'";
						
						values += value;
						
						if(value instanceof String)
							values += "'";
						
						values += ",";
						
					}
				}
			}
			
			if(values.endsWith(","))
				values = values.substring(0, values.length()-1);
			
			if(sql.endsWith(","))
				sql = sql.substring(0, sql.length()-1);
			
			values += ")"; 
			
			sql += ")" + values;
			
			excuteInsertSql(viewDb, mysql, sql);
			
		}
		
		return null;
	
	}
	
	@Override
	public CacheUpdateContext update(DataUpdateContext ctx) throws PhysicalViewException {
		
		List<Map<String, Object>> updataDatas = ctx.getUpdataDatas();
		
		String viewDb = ctx.getTargetDb();
		String viewTable = ctx.getTargetTable();
		String viewColumn = ctx.getTargetTableColumn();
		String viewSourceColumn = ctx.getSourceTableColumn();
		String viewRelationColumn = ctx.getTargetTableRelationColumn();
		String viewSourceRelationColumn = ctx.getSourceTableRelationColumn();
		
		MysqlConnectionConfig mysql = ctx.getMysqlConnectionConfig();
		
		String[] viewColumns = viewColumn.split(",");
		
		int viewColumnLength = viewColumns.length;
		
		String[] viewSourceColumns = viewSourceColumn.split(",");
		
		Set<String> updateColumns = ctx.getUpdateColumns();
		
		List<String> targetTableUpdateColumns = getTargetTableUpdateColumn(updateColumns, viewSourceColumns, viewColumns);
		
		List<PhysicalViewMapping> updateAffiliatedPhysicalViews = getUpdateAffiliatedPhysicalView(viewTable, targetTableUpdateColumns, mysql);
		
		for(int i=0;i<updataDatas.size();i++){
			
			Map<String, Object> updataData = updataDatas.get(i);
			
			String updateSql = "update "+viewTable+" set ";
			
			for(int j=0;j<viewColumnLength;j++){
				
				Object newValue = updataData.get(viewSourceColumns[j]);
				
				if(newValue == null)
					continue;
				
				updateSql += viewColumns[j] +"="+newValue;
				
				if(j+1 < viewColumnLength)
					updateSql += ",";
			}
			
			if(updateAffiliatedPhysicalViews != null && updateAffiliatedPhysicalViews.size() >  0){
				for(PhysicalViewMapping pvm : updateAffiliatedPhysicalViews){
					
					String sourceTableUpdateColumn = getSourceTableUpdateColumn(pvm.getTarget_table_relation_column(), viewSourceColumns, viewColumns);
				
					if(sourceTableUpdateColumn == null)
						continue;
					
					String col = pvm.getTarget_table_column();
					String source = pvm.getSource_table_column();
					String sourceTable = pvm.getSource_table();
					
					String[] cols = col.split(",");
					String[] sources = source.split(",");
					
					String querySql = "select ";
					
					for(int k=0;k<sources.length;k++){
						querySql += sources[k];
						
						if(k+1 < sources.length)
							querySql += ",";
					}
					
					if(querySql.endsWith(","))
						querySql = querySql.substring(0, querySql.length()-1);
					
					String[] dataTable = sourceTable.split("\\.");
					
					Object newValue = updataData.get(sourceTableUpdateColumn);
					
					querySql += " from "+dataTable[1]+" where "+pvm.getSource_table_relation_column()+"="+newValue;
					
					List<Object> values = excuteQuerySql(dataTable[0], mysql, querySql, sources.length);
					
					for(int m=0;m<values.size();m++){
						
						updateSql += cols[m] +"=";
						
						Object v = values.get(m);
						
						if(v instanceof String)
							updateSql += "'";
						
						updateSql += v ;
						
						if(v instanceof String)
							updateSql += "'";
						
						if(m+1 < values.size())
							updateSql += ",";
					}
				}
				
			}
			
			if(updateSql.endsWith(","))
				updateSql = updateSql.substring(0, updateSql.length()-1);
			
			Object whereValue = updataData.get(viewSourceRelationColumn);
			
			if(whereValue != null)
				updateSql += " where " + viewRelationColumn +"="+whereValue;
			
			excuteUpdateSql(viewDb, mysql, updateSql);
			
			
		}
	
		return null;
	}
	
	private List<PhysicalViewMapping> getUpdateAffiliatedPhysicalView(String tableName, List<String> updateColumns,
			MysqlConnectionConfig mysql){
		
		return PhysicalViewMappingFactory.init(mysql).getToUpdateAffiliatedPhysicalView(tableName, updateColumns);
	}
	
	private String getSourceTableUpdateColumn(String column, String[] viewSourceColumns, String[] viewColumns){
		
		for(int i=0;i<viewColumns.length;i++){
			if(column.equalsIgnoreCase(viewColumns[i]))
				return viewSourceColumns[i];
		}
		
		return null;
	}

	private List<String> getTargetTableUpdateColumn(Set<String> updateColumns, String[] viewSourceColumns, String[] viewColumns){
		
		List<String> indexs = new ArrayList<String>();
		
		for(int i=0;i<viewSourceColumns.length;i++){
			
			if(updateColumns.contains(viewSourceColumns[i]))
				indexs.add(viewColumns[i]);
		}
		
		return indexs;
	}
	
	@Override
	public CacheUpdateContext delete(DataUpdateContext ctx) throws PhysicalViewException {
		
		String viewDb = ctx.getTargetDb();
		String viewTable = ctx.getTargetTable();
		String ViewSourceTable = ctx.getSourceTable();
		String viewRelationColumn = ctx.getTargetTableRelationColumn();
		String viewSourceRelationColumn = ctx.getSourceTableRelationColumn();
		
		String[] dbTable = ViewSourceTable.split("\\.");
		
		String deleteSql = "delete from "+viewDb+"."+viewTable+" where "+viewRelationColumn+" not in (select "+viewSourceRelationColumn+" from "+dbTable[0]+"."+dbTable[1]+")";
		
		
	
		excuteUpdateSql(viewDb, ctx.getMysqlConnectionConfig(), deleteSql);
		
		return null;
	
	}

	private List<Object> excuteQuerySql(String database, MysqlConnectionConfig mysql, String sql, int columns){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Object> values = new ArrayList<Object>();
		
		try {
			
			String url = "jdbc:mysql://"+mysql.getHost()+"/"+database+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			Class.forName(mysql.getDriver());
			
			conn = DriverManager.getConnection(url, mysql.getUsername(), mysql.getPassword());
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				for(int i=1;i<=columns;i++){
					values.add(rs.getObject(i));
				}
				
				break;
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
		
		return values;
	}
	
	private void excuteUpdateSql(String database, MysqlConnectionConfig mysql, String sql){
		
		Connection conn = null;
		Statement stmt = null;

		try {
			
			String url = "jdbc:mysql://"+mysql.getHost()+"/"+database+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			Class.forName(mysql.getDriver());
			
			conn = DriverManager.getConnection(url, mysql.getUsername(), mysql.getPassword());
			
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
				
		} catch (SQLException e) {
			logger.error(sql, e);
		} catch (ClassNotFoundException e) {
			logger.error(sql, e);
		} finally{

			try {
	
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}

		}
	}
	
	private void excuteInsertSql(String database, MysqlConnectionConfig mysql, String sql){
		
		Connection conn = null;
		Statement stmt = null;

		try {
			
			String url = "jdbc:mysql://"+mysql.getHost()+"/"+database+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			Class.forName(mysql.getDriver());
			
			conn = DriverManager.getConnection(url, mysql.getUsername(), mysql.getPassword());
			
			stmt = conn.createStatement();

			stmt.execute(sql);
				
		} catch (SQLException e) {
			logger.error(sql, e);
		} catch (ClassNotFoundException e) {
			logger.error(sql, e);
		} finally{

			try {
	
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}

		}
	}

	private boolean isExsit(Map<String, Object> updataData, DataUpdateContext ctx, MysqlConnectionConfig mysql){
		
		Object viewSourceRelationColumnValue = updataData.get(ctx.getSourceTableRelationColumn());
		
		String querySql = "select "+ctx.getTargetTableRelationColumn()+" from "+ctx.getTargetTable()+" where "+ctx.getTargetTableRelationColumn()+"="+viewSourceRelationColumnValue;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(mysql.getDriver());
			
			String url = "jdbc:mysql://"+mysql.getHost()+"/"+ctx.getTargetDb()+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			conn = DriverManager.getConnection(url, mysql.getUsername(), mysql.getPassword());
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery(querySql);
			
			if(rs == null)
				return false;
			
			return rs.next();
				
		} catch (SQLException e) {
			logger.error(querySql, e);
		} catch (ClassNotFoundException e) {
			logger.error(querySql, e);
		} finally{

			try {
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}

		}
		
		return false;
		
	}

}
