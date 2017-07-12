


package com.hg.zk.debug;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperConstructorSimple implements Watcher {

    private static CountDownLatch connectedSemaphone=new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper=new ZooKeeper("127.0.0.1:2181",5000,new ZookeeperConstructorSimple());
        System.out.println(zooKeeper.getState());
        try {
            connectedSemaphone.await();
        } catch (Exception e) {}
        System.out.println("ZooKeeper session established");
        System.out.println("sessionId="+zooKeeper.getSessionId());
        System.out.println("password="+zooKeeper.getSessionPasswd());


//        zooKeeper.create()
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("my ZookeeperConstructorSimple watcher Receive watched event:"+event);
        if(Event.KeeperState.SyncConnected==event.getState()){
            connectedSemaphone.countDown();
        }
    }

}