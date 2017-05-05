package com.gohouse.service.physicalview.mysqllog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gohouse.service.physicalview.mapping.PhysicalViewMappingFactory;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;


/**
 * mysql二进制日志文件读取
 * @author allen
 *
 */
public class MysqlBinaryLogReaderFactory {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private String mysqlBinaryLogPath = null;
	
	private String mysqlBinaryLogFile = "mysql.000001";
	
	private int mysqllogReadPos = 4;
	
	private String useDatabase;
	
	private Properties prop;
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	public void init(MysqlConnectionConfig mysqlConnectionConfig){
		
		mysqlBinaryLogPath = System.getenv("MYSQL_BINARY_LOG_PATH");
		
		if(mysqlBinaryLogPath == null || "".equals(mysqlBinaryLogPath)){
			logger.info("没有指定mysql二进制日志的路径，需要配置环境变量[MYSQL_BINARY_LOG_PATH]");
			return;
		}
		
		this.mysqlConnectionConfig = mysqlConnectionConfig;
		
		loadPhysicalViewProperties();
		
		String currentMysqlBinLogFileName = getCurrentMysqlBinLogFileName();
		
		if(mysqlBinaryLogFile == null || "".equals(mysqlBinaryLogFile)){
			mysqlBinaryLogFile = currentMysqlBinLogFileName;
		}
	}
	
	public List<String> parseUpdateDataSql() throws UnsupportedEncodingException{
		
		String logText = binaryToText(mysqlBinaryLogFile, mysqllogReadPos);
	
		//System.out.println(logText);
		
		String[] logs = logText.split("\\/\\*!\\*\\/;");
		
		List<String> sqls = new ArrayList<String>();
		
		for(int i=0;i<logs.length;i++){
			
			String log = logs[i];
			
			Integer pos = getLogPos(log);
			if(pos != null){
				mysqllogReadPos = pos;
				
				String database = getChangeUseDatabase(log);
				if(database != null){
					useDatabase = database;
				}

				savePhysicalViewProperties();
				continue;
			}
			
			if(MysqlSQLFormatFactory.init(mysqlConnectionConfig).isChangeSql(log)){
				
				String updateSql = "["+useDatabase+"]"+log;
				
				String databaseTable = MysqlSQLFormatFactory.init(mysqlConnectionConfig).getDatabaseTable(updateSql);
				
				if(databaseTable == null)
					continue;
				
				databaseTable = "'"+databaseTable+"'";
				
				boolean isNewTask = PhysicalViewMappingFactory.init(mysqlConnectionConfig).getIsHasUpdatePhysicalView(databaseTable);
				
				if(!isNewTask)
					continue;
		
				sqls.add(updateSql);
			
			}
		
		}
		
		changeMysqlLogFile();
		
		return sqls;
	}
	
	private Integer getLogPos(String log){
		
		if(!log.contains("# at"))
			return null;
	
		String regx = "[0-9]{1}";
		
		String pos = "";
		
		boolean isMustNum = false;
		
		for(int i=0;i<log.length();i++){
			
			String s = log.substring(i, i+1);
			
			if(s.matches(regx)){
				pos += s;
				isMustNum = true;
			}else{
				if(isMustNum){
					break;
				}
			}
			
		}
		
		return Integer.valueOf(pos);
	}
	
	private String getChangeUseDatabase(String log){
		
		if(!log.contains("# at"))
			return null;
		
		if(!log.contains("use "))
			return null;
		
		int index = log.indexOf("use ") + 4;
		
		return log.substring(index, log.length());
	}
	
	private void changeMysqlLogFile(){
		
		String[] fileNum = mysqlBinaryLogFile.split("\\.");
		
		int nextFileNum = Integer.parseInt(fileNum[1]) + 1;
		
		String nextFile = fileNum[0] + ".00000" + nextFileNum;
		
		String logText = binaryToText(nextFile, 4);
		
		if(!logText.contains("File '"+nextFile+"' not found")){
			mysqlBinaryLogFile = nextFile;
			mysqllogReadPos = 4;
			savePhysicalViewProperties();
		}
			
	}
	
	private String binaryToText(String fileName, int position){
		
		logger.info("mysql日志文件："+fileName);
		logger.info("mysql日志文件游标："+position);
		
		try {
			List<String> cmd = new ArrayList<String>();
			cmd.add("mysqlbinlog");
			cmd.add("--start-position="+position);
			cmd.add(fileName);
		
			ProcessBuilder pb = new ProcessBuilder();
			
			pb.directory(new File(mysqlBinaryLogPath));
			
			pb.command(cmd);
			pb.redirectErrorStream(true);
			Process process = pb.start();
			
			StringBuilder result = new StringBuilder();  
			final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));  
			try {  
			       String line;  
			       while ((line = reader.readLine()) != null) {  
			    	  
			          result.append(line); 
			       }  
			} catch (IOException e) {  
				logger.error(e.getMessage(), e);
			} finally {  
				reader.close();
			}  
			process.waitFor();  
			
			return result.toString();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
    	
    	return null;
	}
	
	private void savePhysicalViewProperties(){
		
		OutputStream out = null;
		try {
			prop.setProperty("cur.mysqllog.read.pos", mysqllogReadPos+"");
			prop.setProperty("cur.use.database", useDatabase);
			prop.setProperty("cur.mysqllog.file", mysqlBinaryLogFile);
			
			String path = Thread.currentThread().getContextClassLoader().getResource("physical_view.properties").getPath();
			
			out = new FileOutputStream(path);
			
			prop.store(out, "Update...");
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
	private void loadPhysicalViewProperties(){
		
		prop = new Properties(); 
		
		InputStream in = null;
    	try {   
	    	String path = Thread.currentThread().getContextClassLoader().getResource("physical_view.properties").getPath();
		
			in = new FileInputStream(path);
			
            prop.load(in);
            
            mysqlBinaryLogFile = prop.getProperty("cur.mysqllog.file");
            
            mysqllogReadPos = Integer.parseInt(prop.getProperty("cur.mysqllog.read.pos"));
            
            useDatabase = prop.getProperty("cur.use.database");
        }catch(Exception e){
        	logger.error(e.getMessage(), e);
        }finally{
        	if(in != null){
        		try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
        	}
        }
	}
	
	private String getCurrentMysqlBinLogFileName(){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			
			Class.forName(mysqlConnectionConfig.getDriver());
			
			String url = "jdbc:mysql://"+mysqlConnectionConfig.getHost()+"/"+mysqlConnectionConfig.getPhysicalViewMappingDB()+"?useUnicode=true&amp;characterEncoding=UTF-8";
			
			conn = DriverManager.getConnection(url, mysqlConnectionConfig.getUsername(), mysqlConnectionConfig.getPassword());
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery("show master status;");
			
			while(rs.next()){
				
				String fileName = rs.getString("File");
				
				return fileName;
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
		return null;
	}
	

	public static void main(String[] args) throws IOException {
		MysqlBinaryLogReaderFactory bin=new MysqlBinaryLogReaderFactory();
		//bin.init();
		//bin.writeBinaryStream();
		bin.parseUpdateDataSql();		
		//System.out.println(getChangeUseDatabase("# at 3109 #150617 17:24:52 server id 1  end_log_pos 4221 	Query	thread_id=1	exec_time=1	error_code=0 use test1"));
	}
}