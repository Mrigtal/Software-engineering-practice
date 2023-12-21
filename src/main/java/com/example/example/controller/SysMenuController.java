package com.example.controller;


import com.example.dto.CommonDto;
import com.example.menu.MenuUtils;
import com.example.service.SysMenuService;
import com.example.vo.MenuVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-12-04
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Resource
    private SysMenuService MenuService;

    @GetMapping("/getMenulist")
    public CommonDto list(MenuVo menuVo){
        CommonDto<List<MenuUtils>> commonDto = new CommonDto<>();
        List<MenuUtils> list = MenuService.getList(menuVo);
        commonDto.setContent(list);
        return commonDto;
    }

}

