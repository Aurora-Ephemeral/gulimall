package com.mall.spzx.manager.service.mapper;

import com.mall.spzx.model.dto.product.CategoryBrandDto;
import com.mall.spzx.model.entity.product.Brand;
import com.mall.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface BrandCategoryMapper {

    List<CategoryBrand> selectList(CategoryBrandDto params);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insert(CategoryBrand categoryBrand);

    void update(CategoryBrand categoryBrand);

    void delete(Long id);

    List<Brand> selectBrandListByCategoryId(Long categoryId);
}
