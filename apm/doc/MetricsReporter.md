
为展示metrics数据，需要自定义需要的reporter，目前有的reporter有

1. NewRelic

https://github.com/palominolabs/metrics-new-relic


```
NewRelicReporter reporter = NewRelicReporter.forRegistry(registry)
                .name("new relic reporter")
                .filter(MetricFilter.ALL)
                .attributeFilter(new AllEnabledMetricAttributeFilter())
                .rateUnit(TimeUnit.SECONDS)
                .durationUnit(TimeUnit.MILLISECONDS)
                .metricNamePrefix("foo/")
                .build();

reporter.start(1, TimeUnit.MINUTES);
```

设置仓库

```
    <repositories>

        <repository>
            <id>pentaho-repo</id>
            <name>pentaho-repo</name>
            <url>http://repository.pentaho.org/content/groups/omni/</url>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>pentaho-repo</id>
            <name>pentaho-repo</name>
            <url>http://repository.pentaho.org/content/groups/omni/</url>
        </pluginRepository>
    </pluginRepositories>

```


2. EleasticSearch

https://github.com/elastic/elasticsearch-metrics-reporter-java

```

 <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>metrics-elasticsearch-reporter</artifactId>
            <version>2.2.0</version>
        </dependency>

```

```
ElasticsearchReporter reporter = ElasticsearchReporter
    .forRegistry(registry())
    .hosts("elasticsearch-node1:9200", "elasticsearch-node2:9200")
    .index("metrics")
    .indexDateFormat(null) //no date suffix
    .build();
reporter.start(10, TimeUnit.SECONDS);
```


3. influxdb

https://github.com/davidB/metrics-influxdb

```
final ScheduledReporter reporter = InfluxdbReporter.forRegistry(registry)
    .protocol(new HttpInfluxdbProtocol("http", "influxdb-server", 8086, "admin", "53CR3TP455W0RD", "metrics"))
    .convertRatesTo(TimeUnit.SECONDS)
    .convertDurationsTo(TimeUnit.MILLISECONDS)
    .filter(MetricFilter.ALL)
    .skipIdleMetrics(false)
    .tag("cluster", "CL01")
    .tag("client", "OurImportantClient")
    .tag("server", serverIP)
    .transformer(new CategoriesMetricMeasurementTransformer("module", "artifact"))
    .build();
reporter.start(10, TimeUnit.SECONDS);
```

4. graphite


   <dependency>
            <groupId>io.dropwizard.metrics</groupId>
            <artifactId>metrics-graphite</artifactId>
            <version>3.1.2</version>
        </dependency>