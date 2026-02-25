package com.mall.spzx.manager.service.impl;

import com.mall.spzx.log.service.SysOperLogService;
import com.mall.spzx.manager.service.mapper.SysOperLogMapper;
import com.mall.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOperLogServiceImpl implements SysOperLogService {

    @Autowired
    SysOperLogMapper sysOperLogMapper;

    @Override
    public void insert(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
