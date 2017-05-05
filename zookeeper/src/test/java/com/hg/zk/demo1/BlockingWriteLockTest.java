package com.hg.zk.demo1;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


import com.hg.zk.config.ConnectionHelper;
import com.hg.zk.util.EmbeddedZooKeeperServer;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BlockingWriteLockTest {

    private static EmbeddedZooKeeperServer _embeddedServer;
    private ZooKeeper _zooKeeper;
    private String _testLockPath;
//    private BlockingWriteLock _writeLock;

    private static final int ZK_PORT = 53181;
    private static final String ZK_CONNECTION_STRING = "localhost:" + ZK_PORT;

    @BeforeClass
    public static void beforeAll() throws IOException, InterruptedException {
        _embeddedServer = new EmbeddedZooKeeperServer(ZK_PORT);
        _embeddedServer.start();
    }

    @AfterClass
    public static void afterAll() {
        _embeddedServer.shutdown();
    }

    @Before
    public void setUp() throws IOException, InterruptedException {
        _zooKeeper = new ConnectionHelper().connect(ZK_CONNECTION_STRING);
        _testLockPath = "/test-writeLock-" + System.currentTimeMillis();
//        _writeLock = new BlockingWriteLock("Test Lock", _zooKeeper, _testLockPath);
    }

    @After
    public void tearDown() throws InterruptedException, KeeperException {
        List<String> children = _zooKeeper.getChildren(_testLockPath, false);
        for (String child : children) {
            _zooKeeper.delete(_testLockPath + "/" + child, -1);
        }
        _zooKeeper.delete(_testLockPath, -1);
    }


}