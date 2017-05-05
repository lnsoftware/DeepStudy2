package com.gohouse.service.physicalview.handler;

import com.gohouse.service.physicalview.cache.CacheUpdateContext;
import com.gohouse.service.physicalview.exception.PhysicalViewException;
import com.gohouse.service.physicalview.task.DataUpdateContext;

/**
 * 更新视图处理器接口.
 * @author allen
 *
 */
public interface PhysicalViewHandler {
	
	public CacheUpdateContext insert(DataUpdateContext ctx) throws PhysicalViewException;
	
	public CacheUpdateContext update(DataUpdateContext ctx) throws PhysicalViewException;
	
	public CacheUpdateContext delete(DataUpdateContext ctx) throws PhysicalViewException;

}
