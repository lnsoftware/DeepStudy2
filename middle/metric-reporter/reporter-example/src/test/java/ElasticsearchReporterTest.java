//import com.codahale.metrics.Counter;
//import com.codahale.metrics.Gauge;
//import com.codahale.metrics.Histogram;
//import com.codahale.metrics.Meter;
//import com.codahale.metrics.Metric;
//import com.codahale.metrics.MetricFilter;
//import com.codahale.metrics.MetricRegistry;
//import com.codahale.metrics.Timer;
//import org.elasticsearch.ElasticsearchException;
//import org.elasticsearch.action.admin.cluster.node.stats.NodeStats;
//import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsResponse;
//import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
//import org.elasticsearch.action.admin.indices.template.get.GetIndexTemplatesResponse;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.cluster.metadata.IndexMetaData;
//import org.elasticsearch.cluster.metadata.IndexTemplateMetaData;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.http.HttpServerTransport;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.metrics.percolation.Notifier;
//import org.elasticsearch.node.Node;
//import org.elasticsearch.test.ESIntegTestCase;
//import org.joda.time.format.ISODateTimeFormat;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import static com.codahale.metrics.MetricRegistry.name;
//import static org.elasticsearch.common.settings.Settings.settingsBuilder;
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.Matchers.hasKey;
//import static org.hamcrest.Matchers.hasSize;
//
//public class ElasticsearchReporterTest extends ESIntegTestCase {
//
//    private ElasticsearchReporter elasticsearchReporter;
//    private MetricRegistry registry = new MetricRegistry();
//    private String index = randomAsciiOfLength(12).toLowerCase();
//    private String indexWithDate = String.format("%s-%s-%02d", index, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1);
//    private String prefix = randomAsciiOfLength(12).toLowerCase();
//
//    @Override
//    protected Settings nodeSettings(int nodeOrdinal) {
//        return settingsBuilder()
//                .put(super.nodeSettings(nodeOrdinal))
//                .put(Node.HTTP_ENABLED, true)
//                .build();
//    }
//
//    @Before
//    public void setup() throws IOException {
//        elasticsearchReporter = createElasticsearchReporterBuilder().build();
//    }
//
//    @Test
//    public void testThatTemplateIsAdded() throws Exception {
//        GetIndexTemplatesResponse response = client().admin().indices().prepareGetTemplates("metrics_template").get();
//
//        assertThat(response.getIndexTemplates(), hasSize(1));
//        IndexTemplateMetaData templateData = response.getIndexTemplates().get(0);
//        assertThat(templateData.order(), is(0));
//        assertThat(templateData.getMappings().get("_default_"), is(notNullValue()));
//    }
//
//    @Test
//    public void testThatMappingFromTemplateIsApplied() throws Exception {
//        registry.counter(name("test", "cache-evictions")).inc();
//        reportAndRefresh();
//
//        // somehow the cluster state is not immediately updated... need to check
//        Thread.sleep(200);
//        ClusterStateResponse clusterStateResponse = client().admin().cluster().prepareState().setRoutingTable(false)
//                .setLocal(false)
//                .setNodes(true)
//                .setIndices(indexWithDate)
//                .execute().actionGet();
//
//        assertThat(clusterStateResponse.getState().getMetaData().getIndices().containsKey(indexWithDate), is(true));
//        IndexMetaData indexMetaData = clusterStateResponse.getState().getMetaData().getIndices().get(indexWithDate);
//        assertThat(indexMetaData.getMappings().containsKey("counter"), is(true));
//        Map<String, Object> properties = getAsMap(indexMetaData.mapping("counter").sourceAsMap(), "properties");
//        Map<String, Object> mapping = getAsMap(properties, "name");
//        assertThat(mapping, hasKey("index"));
//        assertThat(mapping.get("index").toString(), is("not_analyzed"));
//    }
//}