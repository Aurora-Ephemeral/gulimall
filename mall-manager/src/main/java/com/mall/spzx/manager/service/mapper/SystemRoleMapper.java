package com.mall.spzx.manager.service.mapper;

import com.mall.spzx.model.dto.system.SysRoleDto;
import com.mall.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemRoleMapper {
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void addRole(SysRole sysRole);

    void updateRole(SysRole sysRole);

    void deleteRole(String roleId);

    List<SysRole> findAll();
}
