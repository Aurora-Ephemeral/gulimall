package com.mall.spzx.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mall.spzx.manager.service.ProductService;
import com.mall.spzx.manager.service.mapper.ProductDetailsMapper;
import com.mall.spzx.manager.service.mapper.ProductMapper;
import com.mall.spzx.manager.service.mapper.ProductSkuMapper;
import com.mall.spzx.model.dto.product.ProductDto;
import com.mall.spzx.model.entity.product.Product;
import com.mall.spzx.model.entity.product.ProductDetails;
import com.mall.spzx.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductDetailsMapper productDetailsMapper;

    @Autowired
    ProductSkuMapper productSkuMapper;

    @Override
    public List<Product> list(ProductDto productDto) {
        return productMapper.selectList(productDto);
    }

    @Override
    @Transactional
    public void update(Product product) {
        // update basic data
        productMapper.update(product);
        // update sku list
        List<ProductSku> skuList = product.getProductSkuList();
        if(skuList != null && !skuList.isEmpty()) {
            List<String> existingSkuCode = skuList.stream().map(ProductSku::getSkuCode).collect(Collectors.toList());
            Collections.sort(existingSkuCode);
            String lastSkuCode = existingSkuCode.get(existingSkuCode.size() - 1);
            int lastCode = Integer.parseInt(lastSkuCode.substring(lastSkuCode.lastIndexOf("_") + 1));
            int idx = lastCode + 1;
            for(ProductSku item : skuList) {
                if(item.getId() == null) {
                    // create
                    item.setProductId(product.getId());
                    item.setSkuCode(product.getId() + "_" + idx);
                    item.setSkuName(product.getName() + "_" + item.getSkuSpec());
                    item.setStatus(0);
                    productSkuMapper.insert(item);
                } else {
                    // update
                    productSkuMapper.updateById(item);
                }
            }
        }
        // update image data
        UpdateWrapper<ProductDetails> wrapper = new UpdateWrapper<>();
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        wrapper.eq("product_id", product.getId());
        productDetailsMapper.update(productDetails, wrapper);
    }

    @Override
    @Transactional
    public void save(Product product) {
        // save product basic info
        // set status as initial
        product.setStatus(0);
        // set initial audit status
        product.setAuditStatus(0);
        productMapper.insert(product);
        // save product detail
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.insert(productDetails);
        // save product sku
        List<ProductSku> productSkuList = new ArrayList<>();
        int idx = 0;
        for (ProductSku productSku : product.getProductSkuList()) {
            productSku.setSkuCode(product.getId() + "_" + idx);
            productSku.setProductId(product.getId());
            productSku.setSkuName(product.getName() + "_" + productSku.getSkuSpec());
            productSku.setStatus(0); // 0 初始值
            productSkuList.add(productSku);
            idx++;
        }
        productSkuMapper.batchInsert(productSkuList);

    }

    @Override
    public Product getDetailById(Long id) {
        // get basic product info
        Product product = productMapper.selectById(id);
        // get sku list
        List<ProductSku> productSkuList = productSkuMapper.selectList(new QueryWrapper<ProductSku>().eq("product_id", id));
        product.setProductSkuList(productSkuList);
        // get image data
        ProductDetails productDetails = productDetailsMapper.selectOne(new QueryWrapper<ProductDetails>().eq("product_id", id));
        product.setDetailsImageUrls(productDetails.getImageUrls());

        return product;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // delete product
        productMapper.deleteById(id);
        // delete detail
//        UpdateWrapper<ProductSku> wrapperDetail = new UpdateWrapper<>();
//        wrapperDetail.eq("product_id", id);
//        wrapperDetail.set("is_deleted", 1);
//        productSkuMapper.update(wrapperDetail);
//        // delete sku list
//        UpdateWrapper<ProductSku> wrapperSku = new UpdateWrapper<>();
//        wrapperSku.eq("product_id", id);
//        wrapperSku.set("is_deleted", 1);
//        productSkuMapper.update(wrapperSku);
    }
}
