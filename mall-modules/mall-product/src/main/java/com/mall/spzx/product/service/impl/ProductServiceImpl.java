package com.mall.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.google.j2objc.annotations.AutoreleasePool;
import com.mall.spzx.model.dto.h5.ProductSkuDto;
import com.mall.spzx.model.entity.product.Product;
import com.mall.spzx.model.entity.product.ProductDetails;
import com.mall.spzx.model.entity.product.ProductSku;
import com.mall.spzx.model.vo.h5.ProductItemVo;
import com.mall.spzx.product.service.IProductService;
import com.mall.spzx.product.service.mapper.ProductDetailsMapper;
import com.mall.spzx.product.service.mapper.ProductMapper;
import com.mall.spzx.product.service.mapper.ProductSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public List<ProductSku> getTop10BestSaleProductSku() {
        List<ProductSku> top10List = productMapper.selectTop10ProductSku();
        return top10List;
    }

    @Override
    public List<ProductSku> listByPage(ProductSkuDto productSkuDto) {
        return productMapper.selectSkuList(productSkuDto);
    }

    @Override
    public ProductItemVo listDetail(Long skuId) {
        ProductItemVo result = new ProductItemVo();
        // get sku info
        LambdaQueryWrapper<ProductSku> queryWrapperSku = new LambdaQueryWrapper<>();
        queryWrapperSku.eq(ProductSku::getId, skuId).eq(ProductSku::getIsDeleted, 0);
        ProductSku productSku = productSkuMapper.selectOne(queryWrapperSku);
        // get product info
        LambdaQueryWrapper<Product> queryWrapperProduct = new LambdaQueryWrapper<>();
        queryWrapperProduct.eq(Product::getId, productSku.getProductId()).eq(Product::getIsDeleted, 0);
        Product product = productMapper.selectOne(queryWrapperProduct);
        // get image list
        LambdaQueryWrapper<ProductDetails> queryWrapperDetails = new LambdaQueryWrapper<>();
        queryWrapperDetails.eq(ProductDetails::getProductId, product.getId()).eq(ProductDetails::getIsDeleted, 0);
        ProductDetails productDetails = productDetailsMapper.selectOne(queryWrapperDetails);
        // get sku list by product id
        LambdaQueryWrapper<ProductSku> queryWrapperSkuList = new LambdaQueryWrapper<>();
        queryWrapperSkuList.eq(ProductSku::getProductId, product.getId()).eq(ProductSku::getIsDeleted, 0);
        List<ProductSku> productSkuList = productSkuMapper.selectList(queryWrapperSku);
        // build data
        result.setProductSku(productSku);
        result.setProduct(product);
        result.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        result.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        result.setSpecValueList(JSON.parseArray(product.getSpecValue()));

        Map<String, Object> skuSpecValueMap = new HashMap<>();
        for (ProductSku sku : productSkuList) {
            skuSpecValueMap.put(sku.getSkuSpec(), sku.getId());
        }
        return result;
    }
}
