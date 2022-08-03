package com.dpwgc.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String toJson(Object object) {
        ObjectMapper jsonMapper = new ObjectMapper();

        try {
            //将对象转为json字符串
            return jsonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LogUtil.error("JsonUtil.toJson error",e.toString());
            return null;
        }
    }

    public static <T> T fromJson(String json,Class<T> tClass) {
        ObjectMapper jsonMapper = new ObjectMapper();

        try {
            return jsonMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            LogUtil.error("JsonUtil.fromJson error",e.toString());
            return null;
        }
    }
}
