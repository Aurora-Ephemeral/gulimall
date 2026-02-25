package com.mall.spzx.manager.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper extends BaseMapper<ProductSku> {

    void batchInsert(List<ProductSku> productSkuList);
}
