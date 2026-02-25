package com.mall.spzx.manager.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductSpecMapper  {
    List<ProductSpec> select();

    void update(ProductSpec data);

    void insert(ProductSpec data);

    void delete(@Param("id") Long id);
}
