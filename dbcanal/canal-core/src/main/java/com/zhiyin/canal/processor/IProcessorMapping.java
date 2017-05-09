package com.zhiyin.canal.processor;

import com.zhiyin.canal.model.RowBinlog;

/**
 * Created by wangqinghui on 2016/12/13.
 */
public interface IProcessorMapping<T> {

    public T add(RowBinlog rowBinlog);
    public T delete(RowBinlog rowBinlog);
    public T update(RowBinlog rowBinlog);
    public T select(RowBinlog rowBinlog);

}
