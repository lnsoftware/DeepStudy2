package com.zhiyin.canal.processor;

import com.zhiyin.canal.model.RowBinlog;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wangqinghui on 2016/12/13.
 */
@Slf4j
public class ProcessorMappingDefault<T> implements IProcessorMapping<T> {
    @Override
    public T add(RowBinlog rowBinlog) {
//        log.info("process {}",);
        return null;
    }

    @Override
    public T delete(RowBinlog rowBinlog) {
        return null;
    }

    @Override
    public T update(RowBinlog rowBinlog) {
        return null;
    }

    @Override
    public T select(RowBinlog rowBinlog) {
        return null;
    }
}
