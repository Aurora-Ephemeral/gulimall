package com.mall.spzx.manager.controller;

import com.mall.spzx.manager.service.ProductUnitService;
import com.mall.spzx.model.entity.base.ProductUnit;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/productUnit")
public class ProductUnitController {
    @Autowired
    private ProductUnitService productUnitService;

    @GetMapping("listAll")
    Result<ProductUnit> getListAll(){
        List<ProductUnit> list = productUnitService.list();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }


}
