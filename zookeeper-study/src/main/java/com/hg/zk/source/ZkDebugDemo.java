package com.hg.zk.source;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkDebugDemo {

    private static final Logger log = LoggerFactory.getLogger(ZkDebugDemo.class);
    private static CountDownLatch connectedSemaphone=new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper=new ZooKeeper("localhost:2181", 2000, new Watcher() {
            @Override public void process(WatchedEvent event) {
                if(Event.KeeperState.SyncConnected==event.getState()){
                    connectedSemaphone.countDown();
                }
            }
        });

        try {
            connectedSemaphone.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sesison = "0x" + Long.toHexString(zooKeeper.getSessionId());
        log.info("sessionId: "+sesison);

        String root = "/zktest";
        if (zooKeeper.exists(root, true) == null) {
            zooKeeper.create(root, "root".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        zooKeeper.create(root + "/EPHEMERAL", "EPHEMERAL".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        String subNode = root+"/PERSISTENT";
        if (zooKeeper.exists(subNode, true) == null) {
            zooKeeper.create(subNode, "zhiyin".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }else{
            zooKeeper.setData(subNode, UUID.randomUUID().toString().getBytes(),-1);
        }

        log.info("start sleep.");
        TimeUnit.SECONDS.sleep(10);
        System.out.println("end sleep.");
        String subNode2 = root+"/"+sesison;
        zooKeeper.create( subNode2 , "EPHEMERAL".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("ls / => " + zooKeeper.getChildren("/", true));
        zooKeeper.close();
    }

}