package com.mall.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.spzx.manager.service.ProductSpecService;
import com.mall.spzx.manager.service.mapper.ProductSpecMapper;
import com.mall.spzx.model.entity.product.ProductSpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Override
    public List<ProductSpec> getListByPage() {
        return productSpecMapper.select();
    }

    @Override
    public void update(ProductSpec data) {
        productSpecMapper.update(data);
    }

    @Override
    public void save(ProductSpec data) {
        log.info("product spec data" + data);
        productSpecMapper.insert(data);
    }

    @Override
    public void delete(Long id) {
        productSpecMapper.delete(id);
    }
}
