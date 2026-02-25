package com.mall.spzx.user.service.impl;

import com.mall.spzx.user.service.ISmsService;
import com.mall.spzx.utils.HttpClientUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements ISmsService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Value(" ${smsKey.appCode}")
    private String appCode;

    @Override
    public void getCode(String phone) {
        // 1. generate four digits code
        String code = RandomStringUtils.randomNumeric(4);
        // 2. save into redis and set expire time
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        // 3. send code via sms
        sendSms(phone, code);

    }

    private void sendSms(String phone, String code) {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String appcode = appCode;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+code);
        bodys.put("template_id", "CST_ptdie100");   //注意，模板wangweisms996 仅作调试使用，下发短信不稳定，请联系客服报备自己的专属签名模板，以保障业务稳定使用
        bodys.put("phone_number", phone);

//可以提交工单联系客服，或者钉钉联系，钉钉号：1ko_t720ssqc54
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从\r\n\t    \t* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java\r\n\t    \t* 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            String response = HttpClientUtil.postForm(host+path, String.class, querys, bodys, headers);
            System.out.println(response);
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
