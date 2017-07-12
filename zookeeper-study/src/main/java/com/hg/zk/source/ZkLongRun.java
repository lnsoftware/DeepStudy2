package com.hg.zk.source;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZkLongRun   {

    private static CountDownLatch connectedSemaphone=new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper=new ZooKeeper("127.0.0.1:2181", 20000, new Watcher() {
            @Override public void process(WatchedEvent event) {
                    if(Event.KeeperState.SyncConnected==event.getState()){
                        System.out.println("zk connected.");
                        connectedSemaphone.countDown();
                    }
            }
        });

        try {
            connectedSemaphone.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ZooKeeper session established");

        // 十六进制
        System.out.println("sessionId: 0x"+Long.toHexString(zooKeeper.getSessionId()));
        System.out.println("password="+zooKeeper.getSessionPasswd());

        System.out.println(zooKeeper.getState().name());


//        zooKeeper.create( "/zhiyin", "zhiyin".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);


        TimeUnit.SECONDS.sleep(15);
        System.out.println(zooKeeper.getState().name());

        System.out.println("ls / => " + zooKeeper.getChildren("/", true));
//        zooKeeper.create()
    }



}