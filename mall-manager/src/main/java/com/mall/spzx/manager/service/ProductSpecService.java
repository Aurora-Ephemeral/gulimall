package com.mall.spzx.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.spzx.model.entity.product.ProductSpec;

import java.util.List;

public interface ProductSpecService  {

    List<ProductSpec> getListByPage();

    void update(ProductSpec data);

    void save(ProductSpec data);

    void delete(Long id);
}
