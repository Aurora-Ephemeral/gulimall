package com.mall.spzx.utils;

import org.springframework.util.StringUtils;

public class Convert {

    public static Integer toInt(Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        final String valueStr = value.toString();
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(valueStr.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
