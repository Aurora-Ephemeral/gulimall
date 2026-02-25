package com.mall.spzx.manager.service;

import com.mall.spzx.model.dto.system.AssginMenuDto;
import com.mall.spzx.model.entity.system.SysMenu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> findNodes();

    void add(SysMenu menu);

    void update(SysMenu menu);

    void delete(Long id);

    List<Long> findByRoleId(Long roleId);

    void assignMenu(AssginMenuDto assginMenuDto);
}
