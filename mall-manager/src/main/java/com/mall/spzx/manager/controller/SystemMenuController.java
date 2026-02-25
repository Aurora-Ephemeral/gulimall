package com.mall.spzx.manager.controller;


import com.mall.spzx.manager.service.SysMenuService;
import com.mall.spzx.model.dto.system.AssginMenuDto;
import com.mall.spzx.model.entity.system.SysMenu;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/menu")
@Slf4j
public class SystemMenuController {

    @Autowired
    SysMenuService sysMenuService;
    @GetMapping("list")
    public Result getTreeList() {

        List<SysMenu> nodes = sysMenuService.findNodes();
        return Result.build(nodes, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("add")
    public Result add(SysMenu menu) {
        sysMenuService.add(menu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("update")
    public Result update(@RequestBody SysMenu menu) {
        sysMenuService.update(menu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        sysMenuService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("findByRoleId/{id}")
    public Result findByRoleId(@PathVariable("id") Long id) {
        List<Long> menuIds = sysMenuService.findByRoleId(id);

        return Result.build(menuIds, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("assignMenu")
    public Result assignMenu(@RequestBody AssginMenuDto assginMenuDto) {

        sysMenuService.assignMenu(assginMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
