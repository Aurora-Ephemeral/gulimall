package com.mall.spzx.utils;

import com.sun.jdi.InternalException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServeletUtils {

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            throw new InternalException("获取HttpServletRequest失败");
        }
    }
}
