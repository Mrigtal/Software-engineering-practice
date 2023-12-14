package com.example.controller;


import com.example.dto.CommonDto;
import com.example.dto.PageDto;
import com.example.dto.Result;
import com.example.entity.SysShop;
import com.example.service.SysShopService;
import com.example.vo.SysShopVo;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/sysShop")
public class SysShopController {


    @Resource
    private SysShopService sysShopService;


    @GetMapping("/getlist")
    public CommonDto list(SysShopVo sysShopVo){
        CommonDto<List<SysShopVo>> commonDto = new CommonDto<>();
        List<SysShopVo> list = sysShopService.getList(sysShopVo);
        commonDto.setContent(list);
        return commonDto;
    }
    @GetMapping("/getList1")
    public CommonDto getList1(SysShopVo sysShopVo){
        CommonDto<PageDto<SysShop>> commonDto = new CommonDto<>();
        PageDto<SysShop> list = sysShopService.getList1(sysShopVo);
        commonDto.setContent(list);
        return commonDto;
    }
    @GetMapping("/category/{categoryId}")
    public Result queryByCategoryId(@PathVariable Long categoryId){
        List<SysShopVo> data = sysShopService.queryByCategoryId(categoryId);
        return Result.success(data);
    }



    @PostMapping("/save")
    public CommonDto save(@RequestBody SysShopVo sysShopVo){
        CommonDto<SysShopVo> commonDto = new CommonDto<>();
        sysShopService.save(sysShopVo);
        return commonDto;
    }



    @DeleteMapping("/delete/{id}")
    public CommonDto delete(@PathVariable Long id){
        CommonDto<SysShop> commonDto = new CommonDto<>();
        sysShopService.delete(id);
        return commonDto;
    }

}

