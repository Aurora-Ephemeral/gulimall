package com.mall.spzx.manager.service.mapper;

import com.mall.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemBrandMapper {
    List<Brand> selectList();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insert(Brand brand);

    void updateById(Brand brand);

    void deleteById(@Param("id") Long id);
}
