package com.zhiyin.trace.zipkin;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by hg on 2017/3/9.
 */
@Slf4j
public class EsServer {

    public static void startup() {
        try {
            final Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "tmp_data");

            Settings settings = Settings.builder()
                    .put("cluster.name", "elasticsearch")
                    .put("http.enabled", "true") // 可以通过 http://localhost:9200/_search 访问ES
                    .put("path.data", tmpDir.toAbsolutePath().toString()) // 2
                    .put("path.home", "/tmp") // 3
                    .build();

            log.info("es data path:" + tmpDir.toAbsolutePath().toString());

            String clusterName = UUID.randomUUID().toString();
            clusterName = "elasticsearch";
            NodeClient nodeClient = (NodeClient) nodeBuilder().settings(settings).clusterName(clusterName).local(true).node()
                    .client();
        } catch (final IOException ioex) {
            log.error("Cannot create temp dir", ioex);
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        startup();
        Thread.sleep(100000000L);
    }
}
