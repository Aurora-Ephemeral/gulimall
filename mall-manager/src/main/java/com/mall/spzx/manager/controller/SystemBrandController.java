package com.mall.spzx.manager.controller;

import com.mall.spzx.manager.service.SystemBrandService;
import com.mall.spzx.model.entity.product.Brand;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/brand")
public class SystemBrandController {
    @Autowired
    SystemBrandService systemBrandService;

    @GetMapping("/listByPage")
    public Result getListByPage() {
        List<Brand> brandList = systemBrandService.getListByPage();

        return Result.build(brandList, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Brand brand) {
        Long id = systemBrandService.saveOrUpdate(brand);

        return Result.build(id, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        systemBrandService.deleteById(id);

        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
