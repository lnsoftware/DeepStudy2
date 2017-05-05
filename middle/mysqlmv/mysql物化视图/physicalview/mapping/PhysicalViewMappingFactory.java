package com.gohouse.service.physicalview.mapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;


/**
 * 视图mapping处理器.
 * @author allen
 *
 */
public class PhysicalViewMappingFactory {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private PhysicalViewMappingFactory(){}
	
	private static PhysicalViewMappingFactory entity  = null;
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	public static PhysicalViewMappingFactory init(MysqlConnectionConfig mysqlConnectionConfig){
		
		if(entity != null)
			return entity;
		
		entity = new PhysicalViewMappingFactory();
		
		entity.setMysqlConnectionConfig(mysqlConnectionConfig);
		
		return entity;
	}
	
	public List<PhysicalViewMapping> getIsNewPhysicalViewMapping(){
		
		String sql = "select * from "+mysqlConnectionConfig.getPhysicalViewMappingTable()+" where is_new_view = true and is_subject = true and (is_deleted is null or is_deleted = false)";
		
		return queryPhysicalViewMappings(sql);
	}
	
	public boolean getIsHasUpdatePhysicalView(String databaseTable){
		
		String sql = "select * from "+mysqlConnectionConfig.getPhysicalViewMappingTable()+" where source_table = "+databaseTable+" and (is_deleted is null or is_deleted = false)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(mysqlConnectionConfig.getDriver());
			
			String url = "jdbc:mysql://"+mysqlConnectionConfig.getHost()+"/"+mysqlConnectionConfig.getPhysicalViewMappingDB()+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			conn = DriverManager.getConnection(url, mysqlConnectionConfig.getUsername(), mysqlConnectionConfig.getPassword());
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs == null)
				return false;
			
			return rs.next();
			
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
		return false;
	}
	
	public void updatePhysicalViewStatus(long id){
		String sql = "update "+mysqlConnectionConfig.getPhysicalViewMappingTable()+" set is_new_view = false where id = "+id;
	
		Connection conn = null;
		Statement stmt = null;

		try {
			
			Class.forName(mysqlConnectionConfig.getDriver());
			
			String url = "jdbc:mysql://"+mysqlConnectionConfig.getHost()+"/"+mysqlConnectionConfig.getPhysicalViewMappingDB()+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			conn = DriverManager.getConnection(url, mysqlConnectionConfig.getUsername(), mysqlConnectionConfig.getPassword());
			
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
	
	public List<PhysicalViewMapping> getToUpdateSubjectPhysicalView(String database, String tableName){
		
		String sql = "select * from "+mysqlConnectionConfig.getPhysicalViewMappingTable()+" where source_table = '"+database+"."+tableName+"'"+" and is_subject = true and (is_deleted is null or is_deleted = false)";
		
		return queryPhysicalViewMappings(sql);
	}
	
	public List<PhysicalViewMapping> getToUpdateAffiliatedPhysicalView(String tableName, List<String> updateColumns){
		
		String sql = "select * from "+mysqlConnectionConfig.getPhysicalViewMappingTable()+" where target_table = '"+tableName+"'"+" and (is_subject is null or is_subject = false)  and (is_deleted is null or is_deleted = false)";
		
		if(updateColumns != null && !updateColumns.isEmpty()){
			
			sql += " and (";
			
			int size = updateColumns.size();
			
			for(int i=0;i<size;i++){
				
				sql += "target_table_relation_column = '"+updateColumns.get(i)+"'";
				
				if(i+1 < size)
					sql += " or ";
			}
			
			sql += ")";
		}
		
		return queryPhysicalViewMappings(sql);
	}
	
	public List<PhysicalViewMapping> getToUpdatePhysicalView(String database, String tableName, Set<String> updateColumns){
		
		String sql = "select * from "+mysqlConnectionConfig.getPhysicalViewMappingTable()+" where source_table = '"+database+"."+tableName+"'";
			
		if(updateColumns != null && !updateColumns.isEmpty()){
			sql += " and (";
			
			Iterator<String> it = updateColumns.iterator();
			
			while(it.hasNext()){
				
				sql += "FIND_IN_SET(CONCAT('"+it.next()+"'), source_table_column)";
				
				if(it.hasNext())
					sql += " or ";
			}
			
			sql += ")";
		}

		sql += " and (is_deleted is null or is_deleted = false)";
		
		return queryPhysicalViewMappings(sql);
	}

	private List<PhysicalViewMapping> queryPhysicalViewMappings(String sql){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<PhysicalViewMapping> mappings = new ArrayList<PhysicalViewMapping>();
	
		try {
			
			Class.forName(mysqlConnectionConfig.getDriver());
			
			String url = "jdbc:mysql://"+mysqlConnectionConfig.getHost()+"/"+mysqlConnectionConfig.getPhysicalViewMappingDB()+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			conn = DriverManager.getConnection(url, mysqlConnectionConfig.getUsername(), mysqlConnectionConfig.getPassword());
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				PhysicalViewMapping mapping = new PhysicalViewMapping();
				mapping.setId(rs.getLong("id"));
				mapping.setTarget_db(rs.getString("target_db"));
				mapping.setTarget_table(rs.getString("target_table"));
				mapping.setSource_table(rs.getString("source_table"));
				mapping.setTarget_table_column(rs.getString("target_table_column"));
				mapping.setSource_table_column(rs.getString("source_table_column"));
				mapping.setTarget_table_relation_column(rs.getString("target_table_relation_column"));
				mapping.setSource_table_relation_column(rs.getString("source_table_relation_column"));
				mapping.setIs_subject(rs.getBoolean("is_subject"));
				mapping.setIs_new_view(rs.getBoolean("is_new_view"));
				mapping.setCreation_date(rs.getLong("creation_date"));
				mapping.setModification_date(rs.getLong("modification_date"));
				mapping.setIs_deleted(rs.getBoolean("is_deleted"));
	
				mappings.add(mapping);
			}
			
			return mappings;
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

	public MysqlConnectionConfig getMysqlConnectionConfig() {
		return mysqlConnectionConfig;
	}

	public void setMysqlConnectionConfig(MysqlConnectionConfig mysqlConnectionConfig) {
		this.mysqlConnectionConfig = mysqlConnectionConfig;
	}
}
