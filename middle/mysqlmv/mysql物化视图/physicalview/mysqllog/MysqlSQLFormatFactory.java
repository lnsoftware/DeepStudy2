package com.gohouse.service.physicalview.mysqllog;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;
import com.gohouse.service.physicalview.mysqllog.parsers.GeneralDeleteSqlParser;
import com.gohouse.service.physicalview.mysqllog.parsers.GeneralInsertIntoSqlParser;
import com.gohouse.service.physicalview.mysqllog.parsers.GeneralUpdateSqlParser;
import com.gohouse.service.physicalview.mysqllog.parsers.InsertIntoSelectSqlParser;

/**
 * mysql sql解析器
 * @author allen
 *
 */
public class MysqlSQLFormatFactory {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private Map<Integer, String> sqlRegx;
	
	private static MysqlSQLFormatFactory msf = null;
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	public static MysqlSQLFormatFactory init(MysqlConnectionConfig mysqlConnectionConfig){

		if(msf == null){
			msf = new MysqlSQLFormatFactory();
			msf.setMysqlConnectionConfig(mysqlConnectionConfig);
		}
		
		return msf;
	}
	
	private MysqlSQLFormatFactory(){
		loadSqlRegx();
	}
	
	public String getDatabaseTable(String sql){
		
		String database = sql.substring(sql.indexOf("[")+1, sql.indexOf("]"));
		
		sql = sql.substring(sql.indexOf("]")+1, sql.length());
		
		int type = getSqlType(sql);
		
		Map<String, String> map = null;
		
		switch (type) {
		case 1:
			map = new GeneralInsertIntoSqlParser(mysqlConnectionConfig).getDatabaseTable(sql);
			break;
		case 2:
			map = new InsertIntoSelectSqlParser(mysqlConnectionConfig).getDatabaseTable(sql);
			break;
		case 3:
			map = new GeneralUpdateSqlParser(mysqlConnectionConfig).getDatabaseTable(sql);
			break;
		case 4:
			map = new GeneralDeleteSqlParser(mysqlConnectionConfig).getDatabaseTable(sql);
			break;

		default:
			break;
		}
		
		if(map != null && !map.isEmpty()){
			String db = map.get("db");
			if(db != null && !"".equals(db)){
				database = db;
			}
			
			return database+"."+map.get("table");
		}
		
		return null;
	}

	public DataUpdateInfo parse(String sql){
		
		String sql2 = sql.substring(sql.indexOf("]")+1, sql.length());
		
		int type = getSqlType(sql2);
		
		DataUpdateInfo info = null;
		
		try {
			switch (type) {
			case 1:
				info = new GeneralInsertIntoSqlParser(mysqlConnectionConfig).execute(sql);
				break;
			case 2:
				info = new InsertIntoSelectSqlParser(mysqlConnectionConfig).execute(sql);
				break;
			case 3:
				info = new GeneralUpdateSqlParser(mysqlConnectionConfig).execute(sql);
				break;
			case 4:
				info = new GeneralDeleteSqlParser(mysqlConnectionConfig).execute(sql);
				break;

			default:
				break;
			}
		} catch (PhysicalViewException e) {
			logger.error("解析sql出错", e);
		}
	
		return info;
		
	}
	
	public boolean isChangeSql(String log){
		
		int type = getSqlType(log);
		
		return type != 0;
	}
	
	private int getSqlType(String sql){
		
		if(sqlRegx == null || sqlRegx.isEmpty())
			return 0;
		
		Iterator<Integer> it = sqlRegx.keySet().iterator();
		
		while(it.hasNext()){
			int type = it.next();
			
			String reg = sqlRegx.get(type);
			
			if(sql.matches(reg))
				return type;
		}
		
		return 0;
	}
	
	private void loadSqlRegx(){
		
		sqlRegx = new HashMap<Integer, String>();
		sqlRegx.put(1, "^(insert into|INSERT INTO).+\\s+(values|VALUES){1}\\s+.+");
		sqlRegx.put(2, "^(insert into|INSERT INTO).+\\s+(select|SELECT){1}\\s+.+\\s+(from|FROM){1}\\s+.+");
		sqlRegx.put(3, "^(update|UPDATE).+\\s+(set|SET){1}\\s+.+");
		sqlRegx.put(4, "^(delete|DELETE)\\s+(from|FROM){1}\\s+.+");
	}
	
	public MysqlConnectionConfig getMysqlConnectionConfig() {
		return mysqlConnectionConfig;
	}

	public void setMysqlConnectionConfig(MysqlConnectionConfig mysqlConnectionConfig) {
		this.mysqlConnectionConfig = mysqlConnectionConfig;
	}

	public static void main(String[] args){
		
		//int type = MysqlSQLFormatFactory.init(null).getSqlType("delete from INTO target_table(column1,column2) select column1,5 from source_table;");
		
		String sql = "	ste  s fdg gh".trim();
		
		System.out.println(sql);
		
		String[] ss = sql.split("\\s+");
		if(ss.length <= 2)
			System.out.println("a");
		
		String reg = "\\s+.+\\s+";
		System.out.println(sql.matches(reg));
		
		String reg2 = "\\s+.+\\s+.+\\s+";
		System.out.println(sql.matches(reg2));
		
		//System.out.println(type);
	}

}
