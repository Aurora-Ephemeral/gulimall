package com.mall.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.mall.spzx.manager.service.SystemUserService;
import com.mall.spzx.model.dto.system.SysUserDto;
import com.mall.spzx.model.dto.system.AssignRoleDto;
import com.mall.spzx.model.entity.system.SysUser;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {

    @Autowired
    SystemUserService sysUserService;

    @GetMapping("/listByPage")
    public Result<PageInfo<SysUser>> getUserList(@RequestParam SysUserDto sysUserDto) {
      PageInfo<SysUser> pageInfo = sysUserService.getListByPage(sysUserDto);

      return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    };

    // add user
    @PostMapping("/add")
    public Result<SysUser> addUser(@RequestBody SysUser sysUser){
        sysUserService.addUser(sysUser);
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    };

    // update user
    @PostMapping("/update")
    public Result<SysUser> updateUser(@RequestBody SysUser sysUser){
      SysUser updatedSysUser = sysUserService.updateUser(sysUser);

      return Result.build(updatedSysUser, ResultCodeEnum.SUCCESS);
    };
    // delete user (logic)
    @PostMapping("/delete/{id}")
    public Result deleteUser(@PathVariable("id") Long id){
        sysUserService.deleteUser(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /*
     * assign roles
     *
     * @param assignRoleDto
     * @return
     *
     */
    @PostMapping("assignRoles")
    public Result assignRoles(@RequestBody AssignRoleDto assignRoleDto){
        sysUserService.assignRoles(assignRoleDto);

        return  Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
