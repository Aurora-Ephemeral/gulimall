package com.mall.spzx.manager.service.impl;

import com.mall.spzx.common.exception.LoginException;
import com.mall.spzx.manager.service.SysMenuService;
import com.mall.spzx.manager.service.mapper.SysMenuMapper;
import com.mall.spzx.model.dto.system.AssginMenuDto;
import com.mall.spzx.model.entity.system.SysMenu;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.utils.MenuHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService
{
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> menus = sysMenuMapper.selectNodes();
        return MenuHelper.buildTree(menus, 0L);
    }

    @Override
    public void add(SysMenu menu) {
        sysMenuMapper.add(menu);
    }

    @Override
    public void update(SysMenu menu) {
        sysMenuMapper.update(menu);
    }

    @Override
    public void delete(Long id) {
        // find if children nodes exists
        int count = sysMenuMapper.countMenuByPid(id);
        if(count > 0) {
            throw new LoginException(ResultCodeEnum.NODE_ERROR);
        }
        // otherwise, delete the node
        sysMenuMapper.delete(id);
    }

    @Override
    public List<Long> findByRoleId(Long roleId) {

        return sysMenuMapper.findByRoleId(roleId);
    }

    @Override
    public void assignMenu(AssginMenuDto assginMenuDto) {
        // delete existing records
        sysMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        // insert new menu id
        if(assginMenuDto.getMenuIdList() != null && assginMenuDto.getMenuIdList().size() > 0) {
            sysMenuMapper.insertByRoleId(assginMenuDto);
        }
    }
    //...{
}
