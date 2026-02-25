package com.mall.spzx.manager.controller;

import com.mall.spzx.manager.service.BrandCategoryService;
import com.mall.spzx.model.dto.product.CategoryBrandDto;
import com.mall.spzx.model.entity.product.Brand;
import com.mall.spzx.model.entity.product.CategoryBrand;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/brand_category")
public class BrandCategoryController {
    @Autowired
    private BrandCategoryService brandCategoryService;

    @GetMapping("/listByPage")
    public Result getListByPage(@RequestParam(required = false) CategoryBrandDto params) {

        List<CategoryBrand> categoryBrandList = brandCategoryService.getListByPage(params);

        return Result.build(categoryBrandList, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody CategoryBrand categoryBrand) {
        if(categoryBrand.getId() != null) {
            // update data
            brandCategoryService.update(categoryBrand);

            return Result.build(null, ResultCodeEnum.SUCCESS);
        } else {
            Long savedId = brandCategoryService.save(categoryBrand);
            return Result.build(savedId, ResultCodeEnum.SUCCESS);
        }
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        brandCategoryService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getBrandListByCategoryId")
    public Result getBrandListByCategoryId(Long categoryId) {
        List<Brand> brandList = brandCategoryService.getBrandListByCategoryId(categoryId);
        return Result.build(brandList, ResultCodeEnum.SUCCESS);
    }
}
