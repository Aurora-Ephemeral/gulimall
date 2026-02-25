package com.mall.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.mall.spzx.model.dto.system.SysRoleDto;
import com.mall.spzx.model.entity.system.SysRole;

import java.util.List;
import java.util.Map;

public interface SystemRoleService {
    PageInfo<SysRole> findByPage(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto);

    Map<String, List> findAll(Long userId);

    void addRole(SysRole sysRole);

    void updateRole(SysRole sysRole);

    void deleteRole(String roleId);
}
