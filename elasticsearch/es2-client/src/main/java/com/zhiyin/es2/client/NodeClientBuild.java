package com.zhiyin.es2.client;

import java.util.concurrent.TimeUnit;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

/**
 * Created by hg on 2017/7/14.
 */
public class NodeClientBuild {

    protected static Node node;
    protected static Client client;

    public NodeClientBuild() {
        node = NodeBuilder
            .nodeBuilder()
            .clusterName("elasticsearch")
            .settings(Settings
                .builder()
                .put("path.home", "./target/es")
                .put("node.name", "test_node"))
            .client(true) // 不作为数据节点
            .node();

        client = node.client();
    }

    public static void main(String[] args) throws InterruptedException {
        NodeClientBuild build = new NodeClientBuild();

        TimeUnit.SECONDS.sleep(100000);
    }

}
