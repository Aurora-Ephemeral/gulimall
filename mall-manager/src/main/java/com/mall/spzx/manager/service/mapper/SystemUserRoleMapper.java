package com.mall.spzx.manager.service.mapper;

import com.mall.spzx.model.dto.system.AssignRoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemUserRoleMapper {

    void deleteByUserId(@Param("userId") Long userId);

    void addUserRoles(AssignRoleDto assignRoleDto);

    List<Long> findRoleIdByUserId(@Param("userId") Long userId);
}
