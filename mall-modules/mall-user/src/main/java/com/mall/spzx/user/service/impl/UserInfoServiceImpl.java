package com.mall.spzx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.spzx.model.dto.h5.UserRegisterDto;
import com.mall.spzx.model.entity.user.SysUser;
import com.mall.spzx.user.service.IUserInfoService;
import com.mall.spzx.user.service.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void register(UserRegisterDto userRegisterDto) {

        String phone = userRegisterDto.getUsername();
        String code = userRegisterDto.getCode();
        // 1. check validation code
        String cachedCode = redisTemplate.opsForValue().get(phone);
        if(cachedCode == null || !cachedCode.equals(code)){
            throw new RuntimeException("validation code is wrong");
        }
        // 2. check whether phone number is unique
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        List<SysUser> sysUser = userMapper.selectList(wrapper);
        if(!sysUser.isEmpty()){
            throw new RuntimeException("phone number is already registered");
        }
        // 3. save user info
        SysUser sysUser1 = new SysUser();
        sysUser1.setUsername(userRegisterDto.getUsername());
        sysUser1.setPassword(DigestUtils.md5DigestAsHex(userRegisterDto.getPassword().getBytes()));
        sysUser1.setPhone(userRegisterDto.getUsername());
        sysUser1.setStatus(1);
        sysUser1.setAvatar("");
        userMapper.insert(sysUser1);

        // 4. delete validation code
        redisTemplate.delete(phone);
    }
}
