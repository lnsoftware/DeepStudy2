package com.gohouse.service.physicalview.discovery;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;
import com.gohouse.service.physicalview.mapping.PhysicalViewMappingFactory;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;
import com.gohouse.service.physicalview.mysqllog.DataUpdateInfo;
import com.gohouse.service.physicalview.task.PhysicalViewDataChangeTaskFactory;

public class PhysicalViewDiscoveryFactory {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	private PhysicalViewDataChangeTaskFactory physicalViewDataChangeTaskFactory;
	
	private long delay = 0;
	
	private long period = 300000;
	
	public PhysicalViewDiscoveryFactory(MysqlConnectionConfig mysqlConnectionConfig, 
			PhysicalViewDataChangeTaskFactory physicalViewDataChangeTaskFactory,
			long delay, long period){
		this.mysqlConnectionConfig = mysqlConnectionConfig;
		this.physicalViewDataChangeTaskFactory = physicalViewDataChangeTaskFactory;
		this.delay = delay;
		this.period = period;
	}
	
	public void start(){
		
		new Timer().schedule(new DiscoveryTask(), delay, period);
	}
	
	class DiscoveryTask extends TimerTask{

		@Override
		public void run() {
			
			List<PhysicalViewMapping>  mappings = PhysicalViewMappingFactory.init(mysqlConnectionConfig).getIsNewPhysicalViewMapping();
			
			if(mappings == null || mappings.size() == 0)
				return;
			
			for(PhysicalViewMapping mapping : mappings){
				
				String databaseTable = mapping.getSource_table();
				
				if(!databaseTable.contains(".")){
					logger.error("未指定数据库");
					continue;
				}
				
				String[] dbTable = databaseTable.split("\\.");
				
				String database = dbTable[0];
				String table = dbTable[1];
					
				int totalCount = getSourceTableLineCount(database, table);
				
				if(totalCount == 0)
					continue;
				
				process(database, table, totalCount, mapping);
				
				PhysicalViewMappingFactory.init(mysqlConnectionConfig).updatePhysicalViewStatus(mapping.getId());
			}
		}
		
		private void process(String database, String table, int totalCount, PhysicalViewMapping mapping){
			
			int pageNum = 1000;
			
			int page = totalCount / pageNum;
			
			if(totalCount % pageNum > 0)
				page += 1;
			
			String sourceColumn = mapping.getSource_table_column();
			
			if(sourceColumn == null || "".equals(sourceColumn))
				return;
			
			String sql = "select ";
			
			String[] sourceColumns = sourceColumn.split(",");
			
			String orderByColumn = null;
			
			for(String column : sourceColumns){
				sql += column+",";
				
				if(orderByColumn == null)
					orderByColumn = column;
			}
			
			PhysicalViewMappingFactory physicalViewMappingFactory = 
					PhysicalViewMappingFactory.init(mysqlConnectionConfig);
			
			List<PhysicalViewMapping> affiliatedMappings = physicalViewMappingFactory.getToUpdateAffiliatedPhysicalView(mapping.getTarget_table(), null);
			
			String[] subjectRelations = null;
			
			if(affiliatedMappings != null && affiliatedMappings.size() > 0){
				
				subjectRelations = new String[affiliatedMappings.size()];
				
				for(int i=0;i<affiliatedMappings.size();i++){
					subjectRelations[i] = affiliatedMappings.get(i).getTarget_table_relation_column();
				}
			}
			
			if(subjectRelations != null){
				
				for(String s : subjectRelations){
					sql += s+",";
				}
			}
			
			if(sql.endsWith(","))
				sql = sql.substring(0, sql.length()-1);
			
			sql += " from " + table +" order by "+orderByColumn +" asc";
			
			for(int i=1;i<=page;i++){
				String limit = " limit "+(i*pageNum-pageNum)+","+pageNum;
				
				List<Map<String, Object>> updataDatas = queryList(database, sql+limit, sourceColumns, subjectRelations);
				
				DataUpdateInfo info = new DataUpdateInfo();
				info.setDbName(database);
				info.setTableName(table);
				info.setUpdataDatas(updataDatas);
				info.setAffiliatedMappings(affiliatedMappings);
				info.setUpdateType(1);
				
				try {
					physicalViewDataChangeTaskFactory.execute(info, mapping);
				} catch (PhysicalViewException e1) {
					logger.error(e1.getMessage(), e1);
					continue;
				}
				
				updataDatas = null;
				affiliatedMappings = null;
			}
			
		}
		
		private int getSourceTableLineCount(String database, String tableName){
			
			String sql = "select count(*) from "+tableName;
			
			return queryCount(database, sql);
			
		}
		
		private List<Map<String, Object>> queryList(String database, String sql, String[] sourceColumns, String[] subjectRelations){
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
			
			try {
				
				Class.forName(mysqlConnectionConfig.getDriver());
				
				String url = "jdbc:mysql://"+mysqlConnectionConfig.getHost()+"/"+database+"?useUnicode=true&amp;characterEncoding=UTF-8";
				
				conn = DriverManager.getConnection(url, mysqlConnectionConfig.getUsername(), mysqlConnectionConfig.getPassword());
				
				stmt = conn.createStatement();

				rs = stmt.executeQuery(sql);
				
				int sourceColumnLength = sourceColumns.length;
				
				while(rs.next()){
					
					Map<String, Object> value = new HashMap<String, Object>();
					
					for(int i=0;i<sourceColumnLength;i++){
						
						String column = sourceColumns[i];
						
						Object obj = rs.getObject(column);
					
						if(obj instanceof String)
							value.put(column, "'"+obj+"'");
						else
							value.put(column, obj);
					}
					
					if(subjectRelations != null){
						
						for(int i=0;i<subjectRelations.length;i++){
							
							String column = subjectRelations[i];
							
							Object obj = rs.getObject(column);
						
							if(obj instanceof String)
								value.put(column, "'"+obj+"'");
							else
								value.put(column, obj);
						}
					}
					
					values.add(value);
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
		
		private int queryCount(String database, String sql){
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				
				Class.forName(mysqlConnectionConfig.getDriver());
				
				String url = "jdbc:mysql://"+mysqlConnectionConfig.getHost()+"/"+database+"?useUnicode=true&amp;characterEncoding=UTF-8";
				
				conn = DriverManager.getConnection(url, mysqlConnectionConfig.getUsername(), mysqlConnectionConfig.getPassword());
			
				stmt = conn.prepareStatement(sql);
				
				rs = stmt.executeQuery();
				
				if(!rs.next())
					return 0;
				
				return rs.getInt(1);
				
			} catch (SQLException e) {
				logger.error(sql, e);
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
			} finally{
				try {
					if(rs!=null){rs.close();}
					if(stmt!=null){stmt.close();}
					if(conn != null){conn.close();}
					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
					}
				}
			return 0;
		}
	}
}
