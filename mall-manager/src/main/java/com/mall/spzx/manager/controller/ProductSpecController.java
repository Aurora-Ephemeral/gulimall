package com.mall.spzx.manager.controller;

import com.mall.spzx.manager.service.ProductSpecService;
import com.mall.spzx.model.entity.product.ProductSpec;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/productSpec")
@Slf4j
public class ProductSpecController {

    @Autowired
    private ProductSpecService productSpecService;

    @GetMapping("listByPage")
    public Result getListByPage() {
        return Result.build(productSpecService.getListByPage(), ResultCodeEnum.SUCCESS);
    }

    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ProductSpec productSpec) {
        log.info("productSpec controller:{}", productSpec);
        if(productSpec.getId() != null) {
            // 更新
            productSpecService.update(productSpec);
        } else {
            productSpecService.save(productSpec);
        }
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("delete/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        productSpecService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
