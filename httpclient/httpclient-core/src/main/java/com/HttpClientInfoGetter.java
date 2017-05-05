package com;


import lombok.Data;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

import java.lang.reflect.Field;

/**
 * 获取httpclient内部信息
 * Created by hg on 2017/1/12.
 */
public class HttpClientInfoGetter {

    private HttpClient httpClient;

    public HttpClientInfoGetter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static Object getFieldValue(Object obj, String fieldName) throws Exception {
        Class<?> clazz = obj.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    public HttpClientInfo get() {

        HttpClientInfo info = new HttpClientInfo();

        MultiThreadedHttpConnectionManager manager = (MultiThreadedHttpConnectionManager) httpClient.getHttpConnectionManager();

        try {
            Object connectionPoolValue = getFieldValue(manager, "connectionPool");

            // linklist
            Object freeConnectionsValue = getFieldValue(connectionPoolValue, "freeConnections");
            Object freeConnectionsSize = getFieldValue(freeConnectionsValue, "size");

            info.setFreeConnections((Integer) freeConnectionsSize);
            info.setMaxTotalConnections(manager.getParams().getMaxTotalConnections());
            info.setDefaultMaxConnectionsPerHost(manager.getParams().getDefaultMaxConnectionsPerHost());
            info.setConnectionsInPool(manager.getConnectionsInPool());

            info.setConnectionTimeout(manager.getParams().getConnectionTimeout());
            info.setSoTimeout(manager.getParams().getSoTimeout());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }


}

@Data
class HttpClientInfo {


    private Integer connectionTimeout;
    private Integer soTimeout;

    private Integer connectionsInPool;
    private Integer freeConnections;

    private Integer defaultMaxConnectionsPerHost;

    private Integer maxTotalConnections;

}