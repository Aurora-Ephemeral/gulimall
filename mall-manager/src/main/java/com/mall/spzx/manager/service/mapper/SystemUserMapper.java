package com.mall.spzx.manager.service.mapper;

import com.mall.spzx.model.dto.system.AssignRoleDto;
import com.mall.spzx.model.dto.system.SysUserDto;
import com.mall.spzx.model.entity.system.SysMenu;
import com.mall.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemUserMapper {
    abstract SysUser selectUserInfoByUserName(String userName);

    List<SysUser> getListByPage(SysUserDto sysUserDto);

    void addUser(SysUser sysUser);

    void updateUser(SysUser sysUser);

    void deleteUser(@Param("userId") Long userId);

    List<SysMenu> getMenuByUserId(@Param("userId")Long userID);
}
