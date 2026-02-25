package com.mall.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.spzx.model.entity.product.Category;
import com.mall.spzx.product.service.ICategoryService;
import com.mall.spzx.product.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Cacheable(value="category", key="'firstLevelList'")
    public List<Category> select1stLevelList() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, 0)
                .eq(Category::getIsDeleted, 0)
                .eq(Category::getStatus, 1);

        return categoryMapper.selectList(wrapper);
    }

    @Override
    public List<Category> getTreeData() {
        // get all category list
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Category::getIsDeleted, 0);

        List<Category> categoryList = categoryMapper.selectList(wrapper);
        // build tree data by parent id
        List<Category> firstLevelData = categoryList.stream().filter(item -> item.getParentId() == 0).toList();
        for(Category category : firstLevelData) {
            __buildTreeData(category, categoryList);
        }
        return firstLevelData;
    }

    private void __buildTreeData(Category parentNode, List<Category> listData) {
        Long parentId = parentNode.getId();
        List<Category> children = listData.stream().filter(item -> Objects.equals(item.getParentId(), parentId)).toList();
        if (!children.isEmpty()) {
            for (Category child : children) {
                __buildTreeData(child, listData);
            }
            parentNode.setChildren(children);
            parentNode.setHasChildren(true);
        } else {
            parentNode.setHasChildren(false);
        }
    }
}
