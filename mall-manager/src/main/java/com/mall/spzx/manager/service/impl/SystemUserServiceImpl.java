package com.mall.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.spzx.common.exception.LoginException;
import com.mall.spzx.manager.service.mapper.SystemUserMapper;
import com.mall.spzx.manager.service.SystemUserService;
import com.mall.spzx.manager.service.mapper.SystemUserRoleMapper;
import com.mall.spzx.model.dto.system.AssignRoleDto;
import com.mall.spzx.model.dto.system.SysUserDto;
import com.mall.spzx.model.entity.system.SysMenu;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.model.vo.system.SysMenuVo;
import com.mall.spzx.utils.AuthContextUtil;
import com.mall.spzx.utils.MenuHelper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.mall.spzx.model.vo.system.LoginVo;
import com.mall.spzx.model.dto.system.LoginDto;
import com.mall.spzx.model.entity.system.SysUser;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // validate captcha
        String codeKey = loginDto.getCodeKey();
        String captcha = loginDto.getCaptcha();
        String redisKey = "captcha"+codeKey;
        String redisCaptcha = redisTemplate.opsForValue().get(redisKey);
        // not validate -> throw error
        if(StrUtil.isEmpty(redisCaptcha) || !StrUtil.equalsIgnoreCase(redisCaptcha, captcha)) {
            throw new LoginException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        // validate -> delete captcha from redis
        redisTemplate.delete(redisKey);
        // fetch username
        String userName = loginDto.getUserName();
        // find user info based on username
        SysUser sysUser = systemUserMapper.selectUserInfoByUserName(userName);
        // if user info not exists, throw error
        if(sysUser == null) {
            throw new LoginException(ResultCodeEnum.LOGIN_ERROR);
        }
        // check password
        String database_password = sysUser.getPassword();
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if(!input_password.equals(database_password)) {
            throw new LoginException(ResultCodeEnum.LOGIN_ERROR);
        }
        // generate token and storage in redis
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(
                "user:login:" + token,
                JSON.toJSONString(sysUser),
                7,
                TimeUnit.DAYS
        );

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userInfoJSON = redisTemplate.opsForValue().get("user:login"+token);
        return JSON.parseObject(userInfoJSON, SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login"+token);

    }

    @Override
    public PageInfo<SysUser> getListByPage(SysUserDto sysUserDto) {
        PageHelper.startPage(sysUserDto.getPageNum(), sysUserDto.getPageSize());
        List<SysUser> sysUserList = systemUserMapper.getListByPage(sysUserDto);
        return new PageInfo<>(sysUserList);
    }

    @Override
    public void addUser(SysUser sysUser) {
        // check if username is unique
        SysUser dbSysUser = systemUserMapper.selectUserInfoByUserName(sysUser.getUserName());
        if(dbSysUser != null) {
            throw new LoginException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        // encrypt password
        String originPwd = sysUser.getPassword();
        String  encryptedPwd = DigestUtils.md5DigestAsHex(originPwd.getBytes());
        sysUser.setPassword(encryptedPwd);

        systemUserMapper.addUser(sysUser);

    }

    @Override
    public SysUser updateUser(SysUser sysUser) {
        systemUserMapper.updateUser(sysUser);

        return systemUserMapper.selectUserInfoByUserName(sysUser.getUserName());
    }

    @Override
    public void deleteUser(Long userId) {
        systemUserMapper.deleteUser(userId);
    }

    @Override
    public void assignRoles(AssignRoleDto assignRoleDto) {

        // 1. delete existed roles from role_user table
        systemUserRoleMapper.deleteByUserId(assignRoleDto.getUserId());
        // 2. insert new roles to role_user table
        systemUserRoleMapper.addUserRoles(assignRoleDto);

    }

    @Override
    public List<SysMenuVo> getMenuInfo() {
        SysUser sysUser = AuthContextUtil.get();
        List<SysMenu> menuList = systemUserMapper.getMenuByUserId(sysUser.getId());

        List<SysMenu> menuTreeList = MenuHelper.buildTree(menuList, 0L);

        return MenuHelper.buildUserMenu(menuTreeList);
    }
}
