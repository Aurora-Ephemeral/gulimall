package com.mall.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.spzx.manager.service.ProductUnitService;
import com.mall.spzx.manager.service.mapper.ProductUnitMapper;
import com.mall.spzx.model.entity.base.ProductUnit;
import org.springframework.stereotype.Service;

@Service
public class ProductUnitImpl  extends ServiceImpl<ProductUnitMapper, ProductUnit> implements ProductUnitService{
}
