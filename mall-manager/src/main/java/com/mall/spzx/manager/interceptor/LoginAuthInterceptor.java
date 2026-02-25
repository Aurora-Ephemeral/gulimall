package com.mall.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.mall.spzx.model.entity.system.SysUser;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // if type of method is option, allow request
        String methodType = request.getMethod();
        if("OPTIONS".equals(methodType)) {
            return true;
        }
        // if token is empty, return error message
        String token = request.getHeader("Authorization");
        if(StrUtil.isEmpty(token)) {
            responseN0LoginInfo(response);
            return false;
        }
        // get user info from redis
        String userInfoJSON = redisTemplate.opsForValue().get("user:login"+token);
        // if user info is empty return error message
        if(StrUtil.isEmpty(userInfoJSON)) {
            responseN0LoginInfo(response);
            return false;
        }
        // set user info into ThreadLocal
        SysUser sysUser = JSON.parseObject(userInfoJSON, SysUser.class);
        AuthContextUtil.set(sysUser);

        // update redis expire time
        redisTemplate.expire("user:login"+token, 30, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.remove();
    }

    private void responseN0LoginInfo(HttpServletResponse response) {
        Result result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try{
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
