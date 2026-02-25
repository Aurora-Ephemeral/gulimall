package com.mall.spzx.manager.controller;

import com.mall.spzx.manager.service.CaptchaService;
import com.mall.spzx.manager.service.SystemUserService;
import com.mall.spzx.model.entity.system.SysMenu;
import com.mall.spzx.model.entity.system.SysUser;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.model.vo.system.SysMenuVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mall.spzx.model.dto.system.LoginDto;
import com.mall.spzx.model.vo.system.LoginVo;
import com.mall.spzx.model.vo.system.CaptchaVo;

import java.util.List;

@Tag(name="用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private CaptchaService captchaService;

    @PostMapping("login")
    @Operation(summary = "login ")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = systemUserService.login(loginDto);

        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("captcha")
    @Operation(summary = "captcha")
    public Result<CaptchaVo> getCaptcha() {
        CaptchaVo captcha = captchaService.getCaptcha();

        return Result.build(captcha, ResultCodeEnum.SUCCESS);
    };

    @GetMapping("getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader(name="Authorization") String token) {
        // get user info based on token
        SysUser sysUser = systemUserService.getUserInfo(token);

        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("getMenuInfo")
    public Result<List<SysMenuVo>> getMenuInfo() {
        List<SysMenuVo> menuList = systemUserService.getMenuInfo();

        return Result.build(menuList, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("logout")
    public Result logout (@RequestHeader(name="Authorization") String token) {
        systemUserService.logout(token);

        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
