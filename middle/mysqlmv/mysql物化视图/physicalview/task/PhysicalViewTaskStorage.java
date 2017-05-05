package com.gohouse.service.physicalview.task;

import java.util.List;

import com.gohouse.service.physicalview.exception.PhysicalViewException;

/**
 * 视图更新任务存储接口
 * @author allen
 *
 */
public interface PhysicalViewTaskStorage {
	
	/**
	 * 添加一个任务.
	 * @param task
	 * @throws PhysicalViewException
	 */
	public void addTask(String task) throws PhysicalViewException;
	
	/**
	 * 添加多个任务.
	 * @param tasks
	 * @throws PhysicalViewException
	 */
	public void addTaskList(List<String> tasks) throws PhysicalViewException;
	
	/**
	 * 取一个任务,并从仓库中移除.
	 * @return
	 * @throws PhysicalViewException
	 */
	public String popTask()throws PhysicalViewException;

}
