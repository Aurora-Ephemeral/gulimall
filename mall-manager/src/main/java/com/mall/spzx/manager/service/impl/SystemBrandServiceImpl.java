package com.mall.spzx.manager.service.impl;

import com.mall.spzx.manager.service.SystemBrandService;
import com.mall.spzx.manager.service.mapper.SystemBrandMapper;
import com.mall.spzx.model.entity.product.Brand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SystemBrandServiceImpl implements SystemBrandService {

    @Autowired
    SystemBrandMapper systemBrandMapper;
    @Override
    public List<Brand> getListByPage() {
        List<Brand> brandList = systemBrandMapper.selectList();

        return brandList;
    }

    @Override
    public Long saveOrUpdate(Brand brand) {

        // if id exists, it's update operation
        if(brand.getId() != null) {
            systemBrandMapper.updateById(brand);
        } else {
            // otherwise, execute insert operation
            Long id = systemBrandMapper.insert(brand);
            log.info("new brand" + id);
        }
        return brand.getId();
    }

    @Override
    public void deleteById(Long id) {

        systemBrandMapper.deleteById(id);
    }
}
