package com.mall.spzx.manager.service;

import com.mall.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SystemCategoryService {

    List<Category> getListAll(Long pid);

    void export(String name, HttpServletResponse response);

    void importData(MultipartFile file);
}
