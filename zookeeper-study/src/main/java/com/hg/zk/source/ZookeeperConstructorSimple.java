package com.hg.zk.source;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperConstructorSimple implements Watcher {

    private static CountDownLatch connectedSemaphone=new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper=new ZooKeeper("127.0.0.1:2181",5000,new ZookeeperConstructorSimple());
        System.out.println(zooKeeper.getState());
        try {
            connectedSemaphone.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ZooKeeper session established");
        System.out.println("sessionId="+zooKeeper.getSessionId());
        System.out.println("password="+zooKeeper.getSessionPasswd());

        System.out.println(zooKeeper.getState().name());

        TimeUnit.SECONDS.sleep(10);
        System.out.println(zooKeeper.getState().name());

        System.out.println("ls / => " + zooKeeper.getChildren("/", true));
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