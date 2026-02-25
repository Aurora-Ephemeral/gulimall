package com.mall.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.mall.spzx.common.exception.LoginException;
import com.mall.spzx.manager.listener.CategoryExcelListener;
import com.mall.spzx.manager.service.SystemCategoryService;
import com.mall.spzx.manager.service.mapper.SystemCategoryMapper;
import com.mall.spzx.model.entity.product.Category;
import com.mall.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.List;

@Service
public class SystemCategoryServiceImpl implements SystemCategoryService {

    @Autowired
    SystemCategoryMapper systemCategoryMapper;
    @Override
    public List<Category> getListAll(Long pid) {
        // find all row according to parent id
        List<Category> categories = systemCategoryMapper.getListByPid(pid);
        // check if each item has children
        for(Category category : categories) {
            int count = systemCategoryMapper.countChildrenRows(category.getId());
            category.setHasChildren(count > 0);
        }
        return categories;
    }

    @Override
    public void export(String name, HttpServletResponse response) {
        // get data by category name
        List<CategoryExcelVo> categoryExcels = systemCategoryMapper.selectByName(name);
        // export to excel
        try{
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("分类信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(),CategoryExcelVo.class)
                    .sheet("分类信息")
                    .doWrite(categoryExcels);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException(500, "导出失败");
        }
    }

    @Override
    public void importData(MultipartFile file) {
        // create a read listener
        CategoryExcelListener listener = new CategoryExcelListener(systemCategoryMapper);
        try {
            // read excel file
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, listener).sheet("分类信息").doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException(500, "导入失败");
        }
    }
}
