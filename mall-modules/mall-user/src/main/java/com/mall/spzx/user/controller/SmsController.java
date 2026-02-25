package com.mall.spzx.user.controller;

import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.user.service.ISmsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mall/user/sms")
public class SmsController {

    @Resource
    ISmsService smsService;

    @GetMapping("code/{phone}")
    public Result getSmsCode(@PathVariable("phone") String phone) {
        smsService.getCode(phone);

        return Result.build("发送成功", ResultCodeEnum.SUCCESS);
    }

}
