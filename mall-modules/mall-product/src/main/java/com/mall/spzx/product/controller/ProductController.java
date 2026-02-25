package com.mall.spzx.product.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.spzx.common.web.controller.BaseController;
import com.mall.spzx.model.dto.h5.ProductSkuDto;
import com.mall.spzx.model.entity.product.ProductSku;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.model.vo.h5.ProductItemVo;
import com.mall.spzx.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mall/product/product")
public class ProductController extends BaseController {

    @Autowired
    IProductService productService;

    @GetMapping("/listByPage")
    public Result listByPage(ProductSkuDto productSkuDto) {
        startPage();
        List<ProductSku> list = productService.listByPage(productSkuDto);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
    // get product detail info by sku id
    @GetMapping("/detail/{skuId}")
    public Result detail(@PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.listDetail(skuId);

        return Result.build(productItemVo, ResultCodeEnum.SUCCESS);
    }
}
