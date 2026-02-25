package com.mall.spzx.manager.service;

import com.mall.spzx.model.entity.product.Brand;

import java.util.List;

public interface SystemBrandService {
    List<Brand> getListByPage();

    Long saveOrUpdate(Brand brand);

    void deleteById(Long id);
}
