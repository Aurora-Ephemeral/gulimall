package com.mall.spzx.product.service;

import com.mall.spzx.model.dto.h5.ProductSkuDto;
import com.mall.spzx.model.entity.product.ProductSku;
import com.mall.spzx.model.vo.h5.ProductItemVo;

import java.util.List;

public interface IProductService {
    List<ProductSku> getTop10BestSaleProductSku();

    List<ProductSku> listByPage(ProductSkuDto productSkuDto);

    ProductItemVo listDetail(Long skuId);
}
