package com.zhiyin.event.core.body.binlog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hg on 2016/5/15.
 */
@Slf4j
public class BinlogOpTypeTest {

    @Test
    public void testDelete() throws Exception {

        Assert.assertTrue( BinlogOpType.DELETE.delete() );
        Assert.assertFalse( BinlogOpType.DELETE.updateOrInsert() );
        Assert.assertTrue( BinlogOpType.UPDATE.updateOrInsert() );

    }
}