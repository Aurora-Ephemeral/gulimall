package com.mall.spzx.user.service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.spzx.model.entity.user.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
