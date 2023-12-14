package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.SysMenu;
import com.example.dao.SysMenuMapper;
import com.example.menu.MenuTree;
import com.example.menu.MenuUtils;
import com.example.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.CopyUtil;
import com.example.vo.MenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2022-12-04
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public List<MenuUtils> getList(MenuVo menuVo) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysMenu::getRole, menuVo.getRole());
        List<SysMenu> sysMenuList = menuMapper.selectList(queryWrapper);
        List<MenuUtils> menuList = CopyUtil.copyList(sysMenuList, MenuUtils.class);
        MenuTree menuTree = new MenuTree(menuList);
        menuList = menuTree.builTree();
        return menuList;
    }
}
