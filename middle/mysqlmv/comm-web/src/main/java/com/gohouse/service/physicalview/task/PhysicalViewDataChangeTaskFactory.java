package com.gohouse.service.physicalview.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gohouse.service.physicalview.cache.CacheUpdateContext;
import com.gohouse.service.physicalview.cache.PhysicalViewCacheHandler;
import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.handler.DefaultPhysicalViewHandler;
import com.gohouse.service.physicalview.mapping.PhysicalViewMapping;
import com.gohouse.service.physicalview.mapping.PhysicalViewMappingFactory;
import com.gohouse.service.physicalview.monitor.MysqlConnectionConfig;
import com.gohouse.service.physicalview.mysqllog.DataUpdateInfo;
import com.gohouse.service.physicalview.mysqllog.MysqlSQLFormatFactory;

/**
 * 视图更新任务处理器.
 * @author allen
 *
 */
public class PhysicalViewDataChangeTaskFactory{

	private Logger logger = Logger.getLogger(getClass());
	
	public Map<String, DefaultPhysicalViewHandler> handlers;

	public PhysicalViewTaskStorage physicalViewTaskStorageHandler;
	
	public PhysicalViewCacheHandler physicalViewCacheHandler;
	
	public int taskProcessThreads = 1;
	
	private MysqlConnectionConfig mysqlConnectionConfig;
	
	public void init(MysqlConnectionConfig mysqlConnectionConfig){
		
		this.mysqlConnectionConfig = mysqlConnectionConfig;
		
		for(int i=0;i<taskProcessThreads;i++){
			new TaskProcess().start();
		}
		
	}

	public void addTask(String task){
		
		if(physicalViewTaskStorageHandler == null)
			physicalViewTaskStorageHandler = new DefaultTaskStorageHandler();
		
		try {
			physicalViewTaskStorageHandler.addTask(task);
		} catch (PhysicalViewException e) {
			logger.error("添加任务出错", e);
		}
	}
	
	public void addTaskList(List<String> tasks){
		if(physicalViewTaskStorageHandler == null)
			physicalViewTaskStorageHandler = new DefaultTaskStorageHandler();
		
		try {
			physicalViewTaskStorageHandler.addTaskList(tasks);
		} catch (PhysicalViewException e) {
			logger.error("添加多个任务出错", e);
		}
	}
	
	public String popTask(){
		
		if(physicalViewTaskStorageHandler == null)
			physicalViewTaskStorageHandler = new DefaultTaskStorageHandler();
		
		try {
			return physicalViewTaskStorageHandler.popTask();
		} catch (PhysicalViewException e) {
			logger.error("取任务出错", e);
		}
		
		return null;
	}
	
	public void execute(DataUpdateInfo info, PhysicalViewMapping mapping) throws PhysicalViewException{
		
		DefaultPhysicalViewHandler handler = null;
		
		if(handlers != null)
			handler = handlers.get(mapping.getTarget_table());
		
		if(handler == null)
			handler = new DefaultPhysicalViewHandler();
		
		DataUpdateContext ctx = new DataUpdateContext();
		
		ctx.setDbName(info.getDbName());
		ctx.setTableName(info.getTableName());
		ctx.setUpdataDatas(info.getUpdataDatas());
		ctx.setUpdateColumns(info.getUpdateColumns());
	
		ctx.setMysqlConnectionConfig(mysqlConnectionConfig);
		
		ctx.setTargetDb(mapping.getTarget_db());
		ctx.setTargetTable(mapping.getTarget_table());
		ctx.setSourceTable(mapping.getSource_table());
		ctx.setTargetTableColumn(mapping.getTarget_table_column());
		ctx.setSourceTableColumn(mapping.getSource_table_column());
		ctx.setTargetTableRelationColumn(mapping.getTarget_table_relation_column());
		ctx.setSourceTableRelationColumn(mapping.getSource_table_relation_column());
		
		int updateType = info.getUpdateType();
		
		List<PhysicalViewMapping> affiliatedMappings = info.getAffiliatedMappings();
		
		if(updateType == 1 && affiliatedMappings == null){
			PhysicalViewMappingFactory physicalViewMappingFactory = 
					PhysicalViewMappingFactory.init(mysqlConnectionConfig);
			
			affiliatedMappings = physicalViewMappingFactory.getToUpdateAffiliatedPhysicalView(mapping.getTarget_table(), null);
		}
		ctx.setMappings(affiliatedMappings);
		
		CacheUpdateContext cacheUpdateContext = null;
		
		switch (updateType) {
			case 1:
				cacheUpdateContext = handler.insert(ctx);
				break;
			case 2:
				cacheUpdateContext = handler.update(ctx);
				break;
			case 3:
				cacheUpdateContext = handler.delete(ctx);
				break;

			default:
				break;
			}
		
		
		if(cacheUpdateContext != null && physicalViewCacheHandler != null){
			try {
				physicalViewCacheHandler.update(cacheUpdateContext);
			} catch (PhysicalViewException e) {
				logger.error("更新缓存出错。", e);
			}
		}
	}
	
	class TaskProcess extends Thread{

		@Override
		public void run() {
			
			while(true){
				
				String task = popTask();
				
				if(task == null){
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						logger.error(e.getMessage(), e);
					}
				}else{
					process(task);
				}	
			}
		}
		
		private void process(String task){
			
			DataUpdateInfo info = MysqlSQLFormatFactory.init(mysqlConnectionConfig).parse(task);
			
			if(info == null)
				return;
			
			List<PhysicalViewMapping> mappings = info.getMappings();
				
			if(mappings != null){
				
				for(PhysicalViewMapping mapping : mappings){
					
					try {
						execute(info, mapping);
					} catch (PhysicalViewException e) {
						logger.error("处理任务【"+task+"】出错，返回任务仓库。", e);
						try {
							physicalViewTaskStorageHandler.addTask(task);
						} catch (PhysicalViewException e1) {
							logger.error("任务【"+task+"】返回任务仓库出错。", e);
						}
					}
						
				}
			}
				
			mappings = null;
		}
		
	}
	
	public Map<String, DefaultPhysicalViewHandler> getHandlers() {
		return handlers;
	}

	public void setHandlers(Map<String, DefaultPhysicalViewHandler> handlers) {
		this.handlers = handlers;
	}

	public PhysicalViewTaskStorage getPhysicalViewTaskStorageHandler() {
		return physicalViewTaskStorageHandler;
	}

	public void setPhysicalViewTaskStorageHandler(
			PhysicalViewTaskStorage physicalViewTaskStorageHandler) {
		this.physicalViewTaskStorageHandler = physicalViewTaskStorageHandler;
	}

	public int getTaskProcessThreads() {
		return taskProcessThreads;
	}

	public void setTaskProcessThreads(int taskProcessThreads) {
		this.taskProcessThreads = taskProcessThreads;
	}

	public PhysicalViewCacheHandler getPhysicalViewCacheHandler() {
		return physicalViewCacheHandler;
	}

	public void setPhysicalViewCacheHandler(
			PhysicalViewCacheHandler physicalViewCacheHandler) {
		this.physicalViewCacheHandler = physicalViewCacheHandler;
	}
	
}
