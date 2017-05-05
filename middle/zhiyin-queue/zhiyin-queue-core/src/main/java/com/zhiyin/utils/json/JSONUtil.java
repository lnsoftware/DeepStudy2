package com.zhiyin.utils.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqinghui on 2016/4/8.
 */
@SuppressWarnings("unchecked")
public final class JSONUtil {

    private JSONUtil() {

    }

    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static final <T> T parseJson(String json, Class<?> clss) {
        try {
            return (T) mapper.readValue(json, clss);
        } catch (JsonProcessingException e) {
            logger.error("解析JSON串失败:{}", json);
        } catch (IOException e) {
            logger.error("解析JSON串失败:{}", json);
        }
        return null;
    }

    public static final <T> List<T> parseJsonList(String json, Class<?> clss) {
        JavaType javaType = getCollectionType(ArrayList.class, clss);
        try {
            return mapper.readValue(json, javaType);
        } catch (JsonParseException e) {
            logger.error("解析JSON串失败:{}", json);
        } catch (JsonMappingException e) {
            logger.error("解析JSON串失败:{}", json);
        } catch (IOException e) {
            logger.error("解析JSON串失败:{}", json);
        }
        return null;
    }

    public static final String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("JSON序列化失败:{}", obj);
        }
        return null;
    }

}