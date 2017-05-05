package com.zhiyin.trace.zipkin;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by hg on 2017/3/9.
 */
public class TT {

    public static void main(String[] args) throws Exception {

        String ip = "localhost";
        int port = 9300;
        String index = "zipkin-2017-03-09";
        String type = "span";

        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));

        MatchAllQueryBuilder query = QueryBuilders.matchAllQuery();

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(query) // Query
                .setFrom(1).setSize(100).setExplain(true);

        SearchResponse response = searchRequestBuilder.execute().get();

        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {

            Map<String, HighlightField> result = hit.highlightFields();
            System.out.println(result.get("_id").fragments() );
        }
    }
}
