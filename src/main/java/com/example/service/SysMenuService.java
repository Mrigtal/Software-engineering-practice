package com.example.service;

import com.example.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.menu.MenuUtils;
import com.example.vo.MenuVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-12-04
 */
public interface SysMenuService extends IService<SysMenu> {
    List<MenuUtils> getList(MenuVo menuVo);
}
