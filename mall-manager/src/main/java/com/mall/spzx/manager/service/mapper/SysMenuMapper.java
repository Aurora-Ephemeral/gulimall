package com.mall.spzx.manager.service.mapper;

import com.mall.spzx.model.dto.system.AssginMenuDto;
import com.mall.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    List<SysMenu> selectNodes();

    void add(SysMenu menu);

    void update(SysMenu menu);

    int countMenuByPid(@Param("parentId") Long parentId);

    void delete(@Param("id") Long id);

    List<Long> findByRoleId(@Param("roleId") Long roleId);

    void deleteByRoleId(@Param("roleId") Long roleId);

    void insertByRoleId(AssginMenuDto assginMenuDto);
}
