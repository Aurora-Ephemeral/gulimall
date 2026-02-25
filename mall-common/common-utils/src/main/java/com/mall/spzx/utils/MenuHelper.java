package com.mall.spzx.utils;

import com.mall.spzx.model.entity.system.SysMenu;
import com.mall.spzx.model.vo.system.SysMenuVo;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    public static List<SysMenu> buildTree(List<SysMenu> plainList, Long pid) {
        List<SysMenu> result = new ArrayList<>();
        for(SysMenu node : plainList) {

            if(node.getParentId() == pid) {
                List<SysMenu>children = buildTree(plainList, node.getId());
                if(!children.isEmpty()) {
                    node.setChildren(children);
                }
                result.add(node);
            }

        }

        return result;
    }

    public static SysMenu findChildren(SysMenu parentNode, List<SysMenu> plainList) {
        parentNode.setChildren(new ArrayList<>());
        for(SysMenu node : plainList) {
            if(node.getParentId().longValue() == parentNode.getId().longValue()) {
                parentNode.getChildren().add(findChildren(node, plainList));
            }
        }
        return parentNode;
    }

    public static List<SysMenuVo> buildUserMenu(List<SysMenu> menuTreeList) {
        List<SysMenuVo> menuVos = new ArrayList<>();

        for(SysMenu menu: menuTreeList) {
            SysMenuVo menuVo = new SysMenuVo();
            menuVo.setTitle(menu.getTitle());
            menuVo.setName(menu.getComponent());
            if(!menu.getChildren().isEmpty()) {
                menuVo.setChildren(buildUserMenu(menu.getChildren()));
            }
        }

        return menuVos;
    }
}
