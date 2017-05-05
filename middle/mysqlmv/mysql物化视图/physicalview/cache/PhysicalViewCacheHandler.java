package com.gohouse.service.physicalview.cache;

import com.gohouse.service.physicalview.exception.PhysicalViewException;

/**
 * 更新缓存处理器接口.
 * @author allen
 *
 */
public interface PhysicalViewCacheHandler {
	
	/**
	 * 更新缓存.
	 * @param ctx
	 * @throws PhysicalViewException
	 */
	public void update(CacheUpdateContext ctx)throws PhysicalViewException;

}
