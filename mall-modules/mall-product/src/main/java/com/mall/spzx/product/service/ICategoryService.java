package com.mall.spzx.product.service;

import com.mall.spzx.model.entity.product.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> select1stLevelList();

    List<Category> getTreeData();
}
