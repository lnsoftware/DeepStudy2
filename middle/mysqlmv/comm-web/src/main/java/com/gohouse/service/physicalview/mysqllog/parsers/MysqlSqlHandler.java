package com.gohouse.service.physicalview.mysqllog.parsers;

import java.util.Map;

import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.mysqllog.DataUpdateInfo;

public interface MysqlSqlHandler {
	
	public DataUpdateInfo execute(String sql)throws PhysicalViewException;
	
	public Map<String, String> getDatabaseTable(String sql)throws PhysicalViewException;

}
