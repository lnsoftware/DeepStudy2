package com.hg.db.mysql.binlog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hg.db.mysql.binlog.entity.BasicC2s;
import com.hg.db.mysql.binlog.entity.ResponseMetaS2c;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by wangqinghui on 2015/12/17.
 */
public class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    private final static OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json; charset=utf-8");

    public static void del() throws Exception {

        BasicC2s c = new BasicC2s();
        c.setId(4162);
        String re = JSON.toJSONString(c);


        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, re);

        Request request = new Request.Builder()
                .url( "contents/del")
                .post(requestBody)
                .build();


        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()){
            throw new IOException("Unexpected code " + response);
        }
        log.debug("response info:{}",response);

        String responseStr = response.body().string();
        JSONObject obj = JSON.parseObject(responseStr);

        String metaObjStr = obj.getString("meta");

        ResponseMetaS2c meta = JSON.parseObject(metaObjStr, ResponseMetaS2c.class);
        log.info("meta code:{}",meta.code);

    }


    public static void add() throws Exception {
//        Content c = new Content();
//        c.setId(4162);
//        c.setTitle("test http add");
//        c.setDescription("dfadfdsaa");
//
//        String re = JSON.toJSONString(c);
//
//
//        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, re);
//
//        Request request = new Request.Builder()
//                .url(SearcherHttp + "contents/add")
//                .post(requestBody)
//                .build();
//
//
//        Response response = client.newCall(request).execute();
//        if (!response.isSuccessful()){
//            throw new IOException("Unexpected code " + response);
//        }
//        log.debug("response info:{}",response);
//
//        String responseStr = response.body().string();
//        JSONObject obj = JSON.parseObject(responseStr);
//
//        String metaObjStr = obj.getString("meta");
//
//        Meta meta = JSON.parseObject(metaObjStr, Meta.class);
//        log.info("meta code:{}",meta.code);

    }
}
