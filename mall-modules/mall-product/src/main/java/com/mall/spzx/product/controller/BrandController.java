package com.mall.spzx.product.controller;


import com.mall.spzx.common.web.controller.BaseController;
import com.mall.spzx.model.entity.product.Brand;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.product.service.IBrandService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mall/product/brand")
@RequiredArgsConstructor
public class BrandController extends BaseController {

    private final IBrandService brandService;

    @GetMapping("/listAll")
    public Result listAll() {
        List<Brand> list = brandService.listAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
