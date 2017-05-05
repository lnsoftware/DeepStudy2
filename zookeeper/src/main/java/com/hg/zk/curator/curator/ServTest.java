package com.hg.zk.curator.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.utils.CloseableUtils;

public class ServTest {
    public  static  final String CONNECT_STR="127.0.0.1:2181";
    public static CuratorFramework client;
    public  static  void main(String args[]) throws Exception {
        RetryPolicy retryPolicy=new RetryOneTime(1000);
        client= CuratorFrameworkFactory.newClient(CONNECT_STR,retryPolicy);
        if(client==null) return;
        client.start();
        XServCenter xServCenter=new XServCenter();
        xServCenter.init(client);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run() {
                CloseableUtils.closeQuietly(client);
            }
        });

//        for(int i=0;i<10;i++) {
//            for(int j=0;j<10;j++) {
//                XServInstance xServInstance = new XServInstance();
//                xServInstance.setId("serv_id_" + i+"_"+j);
//                xServInstance.setName("serv_name_" + i);
//                xServCenter.regist(xServInstance);
//            }
//        }
        System.out.println("#####list######");
        xServCenter.list();

        for(int i=0;i<1000;i++) {
            xServCenter.list();
//            xServCenter.getServiceByName("serv_name_1");
            Thread.sleep(2000);
        }

        Thread.sleep(600000);


    }
}
