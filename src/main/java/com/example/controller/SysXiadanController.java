package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dto.CommonDto;
import com.example.dto.OrderShopDto;
import com.example.dto.PageDto;
import com.example.dto.Result;
import com.example.entity.SysShop;
import com.example.entity.SysXiadan;
import com.example.service.SysShopService;
import com.example.service.SysXiadanService;
import com.example.vo.SysXiadanVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2022-12-04
 */
@RestController
@RequestMapping("/sysXiadan")
public class SysXiadanController {
    @Resource
    private SysXiadanService sysXiadanService;
    @Resource
    private SysShopService shopService;

    /**
     * 查询历史下单记录，通过用户名查询
     */
    @GetMapping("/query/{username}")
    public Result queryByUsername(@PathVariable String username){
        LambdaQueryWrapper<SysXiadan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysXiadan::getUsername, username);
        List<SysXiadan> list = sysXiadanService.list(queryWrapper);
        List<OrderShopDto> orderShopDtos = list.stream().map(order -> {
            String shopId = order.getShopid();
            LambdaQueryWrapper<SysShop> shopQueryWrapper = new LambdaQueryWrapper<>();
            shopQueryWrapper.eq(SysShop::getId, shopId);
            SysShop shop = shopService.getOne(shopQueryWrapper);
            return OrderShopDto.from(order, shop);
        }).collect(Collectors.toList());
        return Result.success(orderShopDtos);
    }

    @GetMapping("/getlist")
    public CommonDto list(SysXiadanVo sysXiadanVo){
        CommonDto<PageDto<SysXiadan>> commonDto = new CommonDto<>();
        PageDto<SysXiadan> list = sysXiadanService.getList(sysXiadanVo);
        commonDto.setContent(list);
        return commonDto;
    }



    @PostMapping("/save")
    public CommonDto save(@RequestBody SysXiadanVo sysXiadanVo){
        CommonDto<SysXiadanVo> commonDto = new CommonDto<>();
        sysXiadanService.save(sysXiadanVo);
        return commonDto;
    }



    @DeleteMapping("/delete/{id}")
    public CommonDto delete(@PathVariable Long id){
        CommonDto<SysXiadan> commonDto = new CommonDto<>();
        sysXiadanService.delete(id);
        return commonDto;
    }

}

