package com.hg.zk.demo1;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangqinghui on 2016/3/2.
 */
public class ZKConnectionTest {

    @Test
    public void testConnect() throws Exception {

        ZKConnection con = new ZKConnection();
        con.connect();
        con.zk.create("/EPHEMERAL", "EPHEMERAL".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);


    }
}