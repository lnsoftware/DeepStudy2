package com.hg.dbcanal.process;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
//import com.hg.dbcanal.DubboServiceFactory;
import com.zhiyin.dbs.factory.DubboServiceFactory;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.body.binlog.BinlogOpType;
import com.zhiyin.http.factory.HttpRequestFactory;
import com.zhiyin.http.factory.HttpUrlFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by hg on 2016/5/12.
 */
@Slf4j
public class BinlogProcessor {

    public static void process(BinlogEventBody body){



        // 热点内容组关联关系表
//        if( body.getTableName().equals("zhiyin_content_addr_cgroup")){
//            processSeg(body);
//        }

        if( body.getTableName().equals("zhiyin_addr_custom_address_location")){
            processSeg(body);
        }else if( body.getTableName().equals("zhiyin_content_basic_content")){
            procAddrContentByContent(body);
            procContentSearch(body);
        }else {
            log.info("skip process binglog event for table {}.",body.getTableName());
        }

    }

    /**
     * 重新分割热点区域
     * @param body
     */
    public static void processSeg(BinlogEventBody body){
        try {
            BinlogOpType op = body.getOpType();

            if (op.delete()) {
                DubboServiceFactory.segLocCenterService().deleteByAddrLoc(body.getDataId());
                log.info("del addrloc cache, {}", JSON.toJSONString(body));
            }
            if (op.updateOrInsert()) {
                DubboServiceFactory.segLocCenterService().reSegByAddrLoc(body.getDataId());
                log.info("update addrloc cache, {}", JSON.toJSONString(body));
            }
            log.info("re segment addr location:{}", body.getDataId());
        }catch (Exception e){
            log.error("process seg addrloc info:{}.",JSON.toJSONString(body),e);
        }
    }


    /**
     * 目前没有用
     * 热点-内容组发生变化
     * 建立热点-内容关联关系
     * @param body
     */
//    public static void procAddrContentByLink(BinlogEventBody body){
//
//        BinlogOpType op = body.getOpType();
//
//        if( op.delete() ){
//            log.info("process del addr-content link,info:{}", JSON.toJSONString(body));
//        }
//
//        if( op.updateOrInsert() ){
//            log.info("process update insert addr-content link,info:{}", JSON.toJSONString(body));
//        }
//
//    }

    /**
     * 内容发生变化，重新构建热点-内容关系
     * @param body
     */
    public static void procAddrContentByContent(BinlogEventBody body){

        try {
            BinlogOpType op = body.getOpType();

            // 内容删除后，需要删除热点-内容关系
            if (op.delete()) {
                log.info("process del addr-content link,info:{}", JSON.toJSONString(body));
                DubboServiceFactory.customaddressRoleContentService().deleteByContentId(body.getDataId());
            }

            if (op.updateOrInsert()) {
                log.info("process update insert addr-content link,info:{}", JSON.toJSONString(body));
                DubboServiceFactory.customaddressRoleContentService().insertByContentId(body.getDataId());
            }

        }catch (Exception e){
            log.error("process addr-content error, ",e);
        }

    }



    /**
     * 内容发生变化，重构搜索引擎索引
     * @param body
     */
    public static void procContentSearch(BinlogEventBody body){

        try {
            BinlogOpType op = body.getOpType();

            // 内容删除后，需要删除删除索引
            if (op.delete()) {
                Long id = body.getDataId();
                Map<String, Long> val = Maps.newHashMap();
                val.put("id", id);

                HttpRequestFactory.post(HttpUrlFactory.get("contents.search.delById"), val);
                log.info("process del content index,info:{}", JSON.toJSONString(body));
             }

            // 更新索引
            if (op.updateOrInsert()) {
                Long id = body.getDataId();
                Map<String, Long> val = Maps.newHashMap();
                val.put("id", id);

                log.info("process update content index,info:{}", JSON.toJSONString(body));
                HttpRequestFactory.post(HttpUrlFactory.get("contents.search.addOrUpdateById"), val);
            }

        }catch (Exception e){
            log.error("process content index error, ",e);
        }

    }




    public static void main(String[] args){

//        BinlogOpType.UPDATE

    }
}
