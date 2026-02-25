package com.mall.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.spzx.manager.service.SystemRoleService;
import com.mall.spzx.manager.service.mapper.SystemRoleMapper;
import com.mall.spzx.manager.service.mapper.SystemUserRoleMapper;
import com.mall.spzx.model.dto.system.SysRoleDto;
import com.mall.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemRoleImpl implements SystemRoleService {

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Override
    public PageInfo<SysRole> findByPage(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto) {
        // set pagination
        PageHelper.startPage(pageNum, pageSize);
        // get whole data
        List<SysRole> list = systemRoleMapper.findByPage(sysRoleDto);
        // get paginated data
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Map<String, List> findAll(Long userId) {

        Map<String, List> resultMap = new HashMap<>();
        // 1. select all role list
        List<SysRole> roleList = systemRoleMapper.findAll();
        resultMap.put("roleList", roleList);
        // 2. find selected role based on user id
        List<Long> userRoleList = systemUserRoleMapper.findRoleIdByUserId(userId);
        resultMap.put("userRoleList", userRoleList);
        return resultMap;
    }


    @Override
    public void addRole(SysRole sysRole) {
        systemRoleMapper.addRole(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole) {
        systemRoleMapper.updateRole(sysRole);
    }

    @Override
    public void deleteRole(String roleId) {
        systemRoleMapper.deleteRole(roleId);
    }
}
