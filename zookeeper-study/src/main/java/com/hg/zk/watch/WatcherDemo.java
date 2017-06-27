
package com.hg.zk.watch;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.AsyncCallback.ChildrenCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

public class WatcherDemo {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        final String path = "/workers";
        final ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 2000, new DefualtWatcher());

        final ChildrenCallback callback = new ChildrenCallback() {

            @Override
            public void processResult(int rc, String path, Object ctx, List<String> children) {
//                System.out.println(children);
            }

        };

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
//                System.out.println("rec event:"+event);
                if (event.getType() == EventType.NodeChildrenChanged) {
                    System.out.println("node change event:" + event);
                    try {
                        System.out.println("重新获得Children，并注册监听：" + zookeeper.getChildren(event.getPath(),false));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 需要重新注册监听
                    zookeeper.getChildren(path , this, callback, null);
                }
            }
        };

        zookeeper.getChildren(path, watcher,callback,null);

//        zookeeper.getData(path,false ,null);

        Thread.sleep(200000);
        System.out.println("finish");
    }

}



class DefualtWatcher implements Watcher{

    @Override public void process(WatchedEvent event) {

        System.out.println("default watcher, event:" + event);
    }

}
