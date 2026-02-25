package com.mall.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.spzx.model.entity.product.Brand;
import com.mall.spzx.product.service.IBrandService;
import com.mall.spzx.product.service.mapper.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {

    private final BrandMapper brandMapper;

    @Override
    public List<Brand> listAll() {
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        return brandMapper.selectList(wrapper);
    }
}
