package com.mall.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mall.spzx.manager.properties.MinioProperties;
import com.mall.spzx.manager.service.FileUploadService;
import com.mall.spzx.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioUtils minioUtils;

    @Autowired
    private MinioProperties minioProperties;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = dateDir + "/" +  uuid + "_" + file.getOriginalFilename();
            minioUtils.uploadFile(minioProperties.getBucketName(), fileName, file.getInputStream());

            return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + fileName;

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    // empty
}
