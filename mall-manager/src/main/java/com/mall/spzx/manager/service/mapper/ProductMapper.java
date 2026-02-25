package com.mall.spzx.manager.service.mapper;

import com.mall.spzx.model.dto.product.ProductDto;
import com.mall.spzx.model.entity.product.Product;
import com.mall.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectList(ProductDto productDto);

    void insert(Product product);

    void update(Product product);
    Product selectById(Long id);

    void deleteById(Long id);
}
