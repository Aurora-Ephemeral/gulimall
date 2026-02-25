package com.mall.spzx.product.controller;

import com.mall.spzx.model.entity.product.Category;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.product.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mall/product/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/getTreeData")
    public Result getTreeData() {
        List<Category> result = categoryService.getTreeData();

        return Result.build(result, ResultCodeEnum.SUCCESS);
    }
}
