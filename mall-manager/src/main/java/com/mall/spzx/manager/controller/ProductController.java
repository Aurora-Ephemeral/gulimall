package com.mall.spzx.manager.controller;

import com.github.pagehelper.PageHelper;
import com.mall.spzx.manager.service.ProductService;
import com.mall.spzx.model.dto.product.ProductDto;
import com.mall.spzx.model.entity.product.Product;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public Result list(ProductDto productDto){
        return Result.build(productService.list(productDto), ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/detailById")
    public Result detailById(Long id){

        Product product = productService.getDetailById(id);
        return Result.build(product, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Product product) {
        if(product.getId() == null) {
            productService.save(product);
        } else {
            productService.update(product);
        }

        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
