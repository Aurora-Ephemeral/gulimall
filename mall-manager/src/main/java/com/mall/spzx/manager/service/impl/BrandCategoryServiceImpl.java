package com.mall.spzx.manager.service.impl;

import com.mall.spzx.manager.service.BrandCategoryService;
import com.mall.spzx.manager.service.mapper.BrandCategoryMapper;
import com.mall.spzx.model.dto.product.CategoryBrandDto;
import com.mall.spzx.model.entity.product.Brand;
import com.mall.spzx.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandCategoryServiceImpl implements BrandCategoryService {

    @Autowired
    private BrandCategoryMapper brandCategoryMapper;

    @Override
    public List<CategoryBrand> getListByPage(CategoryBrandDto params) {

        return brandCategoryMapper.selectList(params);
    }

    @Override
    public Long save(CategoryBrand categoryBrand) {

        brandCategoryMapper.insert(categoryBrand);

        return categoryBrand.getId();
    }

    @Override
    public void update(CategoryBrand categoryBrand) {
        brandCategoryMapper.update(categoryBrand);
    }

    @Override
    public void delete(Long id) {
        brandCategoryMapper.delete(id);
    }

    @Override
    public List<Brand> getBrandListByCategoryId(Long categoryId) {
        return brandCategoryMapper.selectBrandListByCategoryId(categoryId);
    }
}
