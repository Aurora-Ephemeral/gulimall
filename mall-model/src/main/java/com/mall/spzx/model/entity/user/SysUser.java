package com.mall.spzx.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.spzx.model.entity.base.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser extends BaseEntity {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String avatar;
    private String description;
    private int status;
}
