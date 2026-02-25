package com.mall.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.mall.spzx.manager.service.mapper.SystemCategoryMapper;
import com.mall.spzx.model.vo.product.CategoryExcelVo;

import java.util.List;

public class CategoryExcelListener implements ReadListener<CategoryExcelVo> {
    private static final int BATCH_COUNT = 100;

    private List<CategoryExcelVo> cacheList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public SystemCategoryMapper mapper;

    public CategoryExcelListener(SystemCategoryMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public void invoke(CategoryExcelVo categoryExcelVo, AnalysisContext analysisContext) {
        if(cacheList.size() >= BATCH_COUNT) {
            mapper.batchInsert(cacheList);
            cacheList.clear();
        } else {
            cacheList.add(categoryExcelVo);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        mapper.batchInsert(cacheList);
    }
}
