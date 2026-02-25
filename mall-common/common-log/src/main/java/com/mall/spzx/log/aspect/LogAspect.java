package com.mall.spzx.log.aspect;

import com.alibaba.fastjson.JSON;
import com.mall.spzx.log.annotation.Log;
import com.mall.spzx.log.enums.BizType;
import com.mall.spzx.log.enums.OperatorType;
import com.mall.spzx.log.service.SysOperLogService;
import com.mall.spzx.model.entity.system.SysOperLog;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Autowired
    SysOperLogService sysOperLogService;

    @Around(value = "@annotation(com.mall.spzx.log.annotation.Log)")
    public Object advice(ProceedingJoinPoint joinPoint)throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Log log = method.getAnnotation(Log.class);
        SysOperLog sysOperLog = new SysOperLog();
        this.__formatSysLogDataPreHandler(sysOperLog, log, method);
        Object result = null;
        try {
            // execute target method
            result = joinPoint.proceed();
            this.__formatSysLogDataAfterHandler(sysOperLog, result, 0, null);
        } catch (Throwable e) {
            this.__formatSysLogDataAfterHandler(sysOperLog, result, 1, e.getMessage());
            throw new RuntimeException(e);
        } finally {
            // insert into db
            sysOperLogService.insert(sysOperLog);
        }

        return result;
    }

    private void __formatSysLogDataPreHandler(SysOperLog sysOperLog, Log sysLog, Method method) {
        String title = sysLog.title();
        OperatorType operatorType = sysLog.operatorType();
        BizType bizType = sysLog.bizType();
        // set annotation data
        sysOperLog.setTitle(title);
        sysOperLog.setOperatorType(operatorType.name());
        sysOperLog.setBusinessType(bizType.getValue());
        // set method data
        sysOperLog.setMethod(method.getDeclaringClass().getName());
        // set request data
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        sysOperLog.setRequestMethod(request.getMethod());
        sysOperLog.setOperUrl(request.getRequestURI());
        sysOperLog.setOperIp(request.getRemoteAddr());
    }

    private void __formatSysLogDataAfterHandler(SysOperLog sysOperLog, Object result, int status, String errorMsg) {
        sysOperLog.setJsonResult(JSON.toJSONString(result));
        sysOperLog.setStatus(status);
        sysOperLog.setErrorMsg(errorMsg);
    }
}
