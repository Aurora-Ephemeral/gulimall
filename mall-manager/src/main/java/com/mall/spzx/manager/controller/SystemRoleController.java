package com.mall.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.mall.spzx.manager.service.SystemRoleService;
import com.mall.spzx.model.dto.system.SysRoleDto;
import com.mall.spzx.model.entity.system.SysRole;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/role")
public class SystemRoleController {
    @Autowired
    private SystemRoleService systemRoleService;

    @GetMapping("/getByPage")
    public Result getRoleListByPage(@RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = systemRoleService.findByPage(pageNum, pageSize, sysRoleDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getAll")
    public Result<Map<String, List>> getAllRole(@RequestParam("userId") Long userId) {
        Map<String, List> map = systemRoleService.findAll(userId);

        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/add")
    public Result addRole(@RequestBody SysRole sysRole) {
        systemRoleService.addRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * update role
     * @param sysRole
     * @return
     */
    @PostMapping("/update")
    public Result updateRole(SysRole sysRole) {
        systemRoleService.updateRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("delete/{roleId}")
    public Result deleteRole(@PathVariable("roleId") String roleId) {
        systemRoleService.deleteRole(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


}
