package com.mall.spzx.manager.service;

import com.mall.spzx.model.dto.product.ProductDto;
import com.mall.spzx.model.entity.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> list(ProductDto productDto);

    void update(Product product);

    void save(Product product);

    Product getDetailById(Long id);

    void deleteById(Long id);
}
