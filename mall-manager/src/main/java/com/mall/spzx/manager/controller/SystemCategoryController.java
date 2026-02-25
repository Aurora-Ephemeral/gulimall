package com.mall.spzx.manager.controller;

import com.google.j2objc.annotations.AutoreleasePool;
import com.mall.spzx.manager.service.SystemCategoryService;
import com.mall.spzx.model.entity.product.Category;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/system/category")
public class SystemCategoryController {

    @Autowired
    SystemCategoryService systemCategoryService;

    @GetMapping("/getByPid")
    public Result<List<Category>> getListAll(@RequestParam("pid") Long pid) {
        List<Category> categories = systemCategoryService.getListAll(pid);

        return Result.build(categories, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/export")
    public void export(@RequestParam(value = "name", required = false) String name, HttpServletResponse response) {
        systemCategoryService.export(name, response);
    }

    @PostMapping("/import")
    public Result importData (MultipartFile file) {
        systemCategoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
