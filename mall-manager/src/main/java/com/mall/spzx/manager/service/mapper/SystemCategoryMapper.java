package com.mall.spzx.manager.service.mapper;

import com.mall.spzx.model.entity.product.Category;
import com.mall.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemCategoryMapper {
    List<Category> getListByPid(@Param("pid")Long pid);

    int countChildrenRows(Long id);

    List<CategoryExcelVo> selectByName(@Param("name") String name);

    void batchInsert(@Param("cacheList")List<CategoryExcelVo> cacheList);
}
