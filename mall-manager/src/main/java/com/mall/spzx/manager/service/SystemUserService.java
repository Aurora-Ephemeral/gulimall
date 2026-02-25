package com.mall.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.mall.spzx.model.dto.system.AssignRoleDto;
import com.mall.spzx.model.dto.system.LoginDto;
import com.mall.spzx.model.dto.system.SysUserDto;
import com.mall.spzx.model.entity.system.SysUser;
import com.mall.spzx.model.vo.system.LoginVo;
import com.mall.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SystemUserService {

    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> getListByPage(SysUserDto sysUserDto);

    void addUser(SysUser sysUser);

    SysUser updateUser(SysUser sysUser);

    void deleteUser(Long userId);

    void assignRoles(AssignRoleDto assignRoleDto);

    List<SysMenuVo> getMenuInfo();
}
