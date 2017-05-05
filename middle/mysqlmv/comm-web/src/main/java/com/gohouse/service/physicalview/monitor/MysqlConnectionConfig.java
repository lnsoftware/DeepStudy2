package com.gohouse.service.physicalview.monitor;

public class MysqlConnectionConfig {
	
	public String driver;
	
	public String host;
	
	public String username;
	
	public String password;
	
	public String physicalViewMappingDB;
	
	public String physicalViewMappingTable;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	

}
