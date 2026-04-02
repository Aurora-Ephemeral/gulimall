package com.mall.spzx.manager.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestDataLoader {

    public static JSONObject load(String resourcePath) {
        try(InputStream is = TestDataLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if(is == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }
            String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return JSON.parseObject(content);
        } catch (IOException e) {
            throw new RuntimeException("Load test data failed" + resourcePath, e);
        }
    }

    public static <T> T loadAs(String resourcePath, Class<T> clazz, String key) {
        JSONObject root = load(resourcePath);
        return root.getObject(key, clazz);
    }
}
