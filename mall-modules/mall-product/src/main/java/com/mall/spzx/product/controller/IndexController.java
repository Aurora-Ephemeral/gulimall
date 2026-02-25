package com.mall.spzx.product.controller;

import com.mall.spzx.model.entity.product.Category;
import com.mall.spzx.model.entity.product.ProductSku;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.model.vo.h5.IndexVo;
import com.mall.spzx.product.service.ICategoryService;
import com.mall.spzx.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mall/product/index")
public class IndexController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @GetMapping("/product")
    public Result product() {
        // get top level category list
        List<Category> categoryList = categoryService.select1stLevelList();
        // get top 10 best sale product sku
        List<ProductSku> top10Products = productService.getTop10BestSaleProductSku();
        // build data
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(top10Products);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
