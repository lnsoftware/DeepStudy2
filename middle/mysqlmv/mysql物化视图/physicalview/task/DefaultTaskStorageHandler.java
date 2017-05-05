package com.gohouse.service.physicalview.task;

import java.util.ArrayList;
import java.util.List;

import com.gohouse.service.physicalview.exception.PhysicalViewException;

/**
 * 默认的视图更新任务存储处理器.
 * @author allen
 *
 */
public class DefaultTaskStorageHandler implements PhysicalViewTaskStorage {
	
	public List<String> taskPool = new ArrayList<String>();

	@Override
	public void addTask(String task) throws PhysicalViewException {
		synchronized (taskPool) {
			taskPool.add(task);
		}
	}

	@Override
	public void addTaskList(List<String> tasks) throws PhysicalViewException {
		synchronized (taskPool) {
			taskPool.addAll(tasks);
		}
	}

	@Override
	public String popTask() throws PhysicalViewException {
		synchronized (taskPool) {
			
			if(taskPool.isEmpty())
				return null;
			
			String task = taskPool.get(0);
			
			taskPool.remove(0);
			
			return task;
		}
	}

}
