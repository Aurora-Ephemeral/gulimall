package com.mall.spzx.manager.controller;

import com.mall.spzx.manager.service.FileUploadService;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public Result<String> fileUpload(MultipartFile file) {

        String fileUrl = fileUploadService.uploadFile(file);
        return Result.build(fileUrl, ResultCodeEnum.SUCCESS);
    };
}
