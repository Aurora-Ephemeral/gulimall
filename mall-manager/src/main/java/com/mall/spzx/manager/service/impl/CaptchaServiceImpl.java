package com.mall.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.mall.spzx.manager.service.CaptchaService;
import com.mall.spzx.model.vo.system.CaptchaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public CaptchaVo getCaptcha() {
        // 1. 生成图片验证码： hutool
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(480, 160, 4, 4);
        String code = circleCaptcha.getCode();
        String imgBase64 = circleCaptcha.getImageBase64();
        // 2. 把验证码存储到redis里面，设置redis的key： uuid， redis的value： 验证码的值
        String key = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("captcha"+key, code, 5, TimeUnit.MINUTES);
        // 3. 返回CaptchaVo对象
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setCodeKey(key);
        captchaVo.setCodeValue(imgBase64);
        return captchaVo;
    };
}
