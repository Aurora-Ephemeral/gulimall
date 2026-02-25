package com.mall.spzx.manager.service;

import com.mall.spzx.model.dto.product.CategoryBrandDto;
import com.mall.spzx.model.entity.product.Brand;
import com.mall.spzx.model.entity.product.CategoryBrand;

import java.util.List;

public interface BrandCategoryService {
    List<CategoryBrand> getListByPage(CategoryBrandDto params);

    Long save(CategoryBrand categoryBrand);

    void update(CategoryBrand categoryBrand);

    void delete(Long id);

    List<Brand> getBrandListByCategoryId(Long categoryId);
}
