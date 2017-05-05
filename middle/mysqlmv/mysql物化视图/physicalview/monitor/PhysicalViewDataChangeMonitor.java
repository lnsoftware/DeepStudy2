package com.gohouse.service.physicalview.monitor;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.gohouse.service.physicalview.discovery.PhysicalViewDiscoveryFactory;
import com.gohouse.service.physicalview.mysqllog.MysqlBinaryLogReaderFactory;
import com.gohouse.service.physicalview.task.PhysicalViewDataChangeTaskFactory;

/**
 * 数据修改监听器(定时任务)
 * @author allen
 *
 */
public class PhysicalViewDataChangeMonitor {
	
	private Logger logger = Logger.getLogger(getClass());
	
	public MysqlBinaryLogReaderFactory mysqlBinaryLogReaderFactory;
	
	public PhysicalViewDataChangeTaskFactory physicalViewDataChangeTaskFactory;
	
	public long delay = 0; 
	
	public long period = 3000;
	
	public String mysqlDriver;
	
	public String mysqlHost;
	
	public String mysqlUsername;
	
	public String mysqlPassword;
	
	public String physicalViewMappingDB;
	
	public String physicalViewMappingTable;
	
	public boolean autoDiscoveryView = true;
	
	public long autoDiscoveryViewDelay = 0;
	
	public long autoDiscoveryViewPeriod = 300000;

	public void init(){
		
		boolean isMysqlLogBinEnable = isMysqlLogBinEnable();
		
		if(!isMysqlLogBinEnable){
			logger.info("mysql日志文件未启用！");
			return;
		}
		
		MysqlConnectionConfig mysqlConnectionConfig = initMysqlConnectionConfig();
		
		mysqlBinaryLogReaderFactory.init(mysqlConnectionConfig);
		
		physicalViewDataChangeTaskFactory.init(mysqlConnectionConfig);
		
		if(autoDiscoveryView){
			new PhysicalViewDiscoveryFactory(mysqlConnectionConfig, physicalViewDataChangeTaskFactory,
					autoDiscoveryViewDelay, autoDiscoveryViewPeriod).start();
		}
	
		new Timer().schedule(new dataChangeMonitorTask(), delay, period);
	}
	
	private MysqlConnectionConfig initMysqlConnectionConfig(){
		MysqlConnectionConfig mysqlConnectionConfig = new MysqlConnectionConfig();
		mysqlConnectionConfig.setDriver(mysqlDriver);
		mysqlConnectionConfig.setHost(mysqlHost);
		mysqlConnectionConfig.setUsername(mysqlUsername);
		mysqlConnectionConfig.setPassword(mysqlPassword);
		mysqlConnectionConfig.setPhysicalViewMappingDB(physicalViewMappingDB);
		mysqlConnectionConfig.setPhysicalViewMappingTable(physicalViewMappingTable);
		
		return mysqlConnectionConfig;
	}
	
	private boolean isMysqlLogBinEnable(){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		boolean isMysqlLogBinEnable = false;
	
		try {
			
			Class.forName(mysqlDriver);
			
			String url = "jdbc:mysql://"+mysqlHost+"/"+physicalViewMappingDB+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			conn = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery("show variables like 'log_bin';");
			
			while(rs.next()){
				String name = rs.getString("Variable_name");
				
				if(!name.equalsIgnoreCase("log_bin")){
					continue;	
				}
				
				String value = rs.getString("Value");
				
				if(value.equalsIgnoreCase("on")){
					isMysqlLogBinEnable = true;
					break;
				}
			}
				
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
		return isMysqlLogBinEnable;
	}
	
	class dataChangeMonitorTask extends TimerTask{

		@Override
		public void run() {
	
			try {
				List<String> sqls = mysqlBinaryLogReaderFactory.parseUpdateDataSql();
				
				if(sqls == null)
					return;
				
				physicalViewDataChangeTaskFactory.addTaskList(sqls);
			
				sqls = null;
				
			} catch (UnsupportedEncodingException e) {
				logger.error("读取mysql二进制日志文件出错", e);
			}
			
		}
		
	}

	public MysqlBinaryLogReaderFactory getMysqlBinaryLogReaderFactory() {
		return mysqlBinaryLogReaderFactory;
	}

	public void setMysqlBinaryLogReaderFactory(
			MysqlBinaryLogReaderFactory mysqlBinaryLogReaderFactory) {
		this.mysqlBinaryLogReaderFactory = mysqlBinaryLogReaderFactory;
	}

	public PhysicalViewDataChangeTaskFactory getPhysicalViewDataChangeTaskFactory() {
		return physicalViewDataChangeTaskFactory;
	}

	public void setPhysicalViewDataChangeTaskFactory(
			PhysicalViewDataChangeTaskFactory physicalViewDataChangeTaskFactory) {
		this.physicalViewDataChangeTaskFactory = physicalViewDataChangeTaskFactory;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public String getMysqlHost() {
		return mysqlHost;
	}

	public void setMysqlHost(String mysqlHost) {
		this.mysqlHost = mysqlHost;
	}

	public String getMysqlUsername() {
		return mysqlUsername;
	}

	public void setMysqlUsername(String mysqlUsername) {
		this.mysqlUsername = mysqlUsername;
	}

	public String getMysqlPassword() {
		return mysqlPassword;
	}

	public void setMysqlPassword(String mysqlPassword) {
		this.mysqlPassword = mysqlPassword;
	}

	public String getMysqlDriver() {
		return mysqlDriver;
	}

	public void setMysqlDriver(String mysqlDriver) {
		this.mysqlDriver = mysqlDriver;
	}

	public String getPhysicalViewMappingDB() {
		return physicalViewMappingDB;
	}

	public void setPhysicalViewMappingDB(String physicalViewMappingDB) {
		this.physicalViewMappingDB = physicalViewMappingDB;
	}

	public String getPhysicalViewMappingTable() {
		return physicalViewMappingTable;
	}

	public void setPhysicalViewMappingTable(String physicalViewMappingTable) {
		this.physicalViewMappingTable = physicalViewMappingTable;
	}

	public boolean isAutoDiscoveryView() {
		return autoDiscoveryView;
	}

	public void setAutoDiscoveryView(boolean autoDiscoveryView) {
		this.autoDiscoveryView = autoDiscoveryView;
	}

	public long getAutoDiscoveryViewDelay() {
		return autoDiscoveryViewDelay;
	}

	public void setAutoDiscoveryViewDelay(long autoDiscoveryViewDelay) {
		this.autoDiscoveryViewDelay = autoDiscoveryViewDelay;
	}

	public long getAutoDiscoveryViewPeriod() {
		return autoDiscoveryViewPeriod;
	}

	public void setAutoDiscoveryViewPeriod(long autoDiscoveryViewPeriod) {
		this.autoDiscoveryViewPeriod = autoDiscoveryViewPeriod;
	}

	
}
