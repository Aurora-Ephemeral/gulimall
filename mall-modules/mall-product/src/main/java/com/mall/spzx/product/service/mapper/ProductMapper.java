package com.mall.spzx.product.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.spzx.model.dto.h5.ProductSkuDto;
import com.mall.spzx.model.entity.product.Product;
import com.mall.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductSku> selectTop10ProductSku();

    List<ProductSku> selectSkuList(ProductSkuDto productSkuDto);
}
