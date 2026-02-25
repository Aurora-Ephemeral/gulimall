package com.mall.spzx.common.exception;

import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.spzx.common.exception.LoginException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error("系统异常： " + e.getMessage());
        return Result.build(null, 500, e.getMessage());
    }

    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Result error(LoginException e) {
        return Result.build(null, e.getResultCodeEnum());
    }
}
