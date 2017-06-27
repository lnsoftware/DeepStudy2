package com.hg.zk.watch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZkDemo {

 public static void main(String[] args) throws Exception {
     final String path = "/workers";

     // 创建一个与服务器的连接
     ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 60000, new Watcher() {
         // 监控所有被触发的事件
         public void process(WatchedEvent event) {
             System.out.println("EVENT:" + event.getType());
         }
     });
     // 查看根节点
     System.out.println("ls / => " + zk.getChildren("/", true));

     // 创建一个目录节点
     if (zk.exists(path, false) == null) {
         zk.create(path , "conan".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
         System.out.println("get /node => " + new String(zk.getData(path, false, null)));
     }

     if (zk.exists(path +"/sub1", false) == null) {
         zk.create(path +"/sub1", "sub1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
         System.out.println("create /node/sub1 sub1");
     }

     // 修改节点数据
     if (zk.exists(path, true) != null) {
         zk.setData(path, "changed".getBytes(), -1);
         // 查看/node节点数据
         System.out.println("get /node => " + new String(zk.getData(path, false, null)));
     }

     // 删除节点
     if (zk.exists(path + "/sub1", true) != null) {
         zk.delete(path + "/sub1", -1);
//         zk.delete(path, -1);
         // 查看根节点
         System.out.println("ls / => " + zk.getChildren("/", true));
     }
//     zk.create("/EPHEMERAL", "EPHEMERAL".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
     // 关闭连接
     zk.close();
 }
}