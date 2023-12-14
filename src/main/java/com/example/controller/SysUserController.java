package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dto.CommonDto;
import com.example.dto.Result;
import com.example.entity.SysUser;
import com.example.service.SysUserService;
import com.example.vo.SysXiadanVo;
import com.example.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p >
 *
 * @since 2022-12-04
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Resource
    private SysUserService userService;

    @PostMapping("/login")
    public CommonDto login(@RequestBody UserVo userVo) {
        //将前端传来的密码进行MD5加密传输给后端
        //userVo.setPassword(DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes()));
        CommonDto<UserVo> commonDto = new CommonDto<>();
        UserVo login = userService.login(userVo, commonDto);
        commonDto.setContent(login);
        return commonDto;
    }

    @PostMapping("/save")
    public CommonDto save(@RequestBody UserVo userVo) {
        //将前端传来的密码进行MD5加密传输给后端
        //userVo.setPassword(DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes()));
        CommonDto<UserVo> commonDto = new CommonDto<>();
        userService.save(userVo, commonDto);
        return commonDto;
    }

    @PostMapping("/register")
    public Result register(@RequestBody SysUser user) {
        user.setRole("1"); // 只允许注册普通用户
        userService.save(user);
        return Result.success("注册成功");
    }

    @GetMapping("/list/custom")
    public Result listCustom() {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getRole, 1);
        List<SysUser> list = userService.list(queryWrapper);
        for (SysUser sysUser : list) {
            sysUser.setPassword("******");
        }
        return Result.success(list);
    }

    @GetMapping("/info/{username}")
    public Result queryInfo(@PathVariable String username) {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = userService.getOne(sysUserLambdaQueryWrapper);
        sysUser.setPassword("******");

        return Result.success(sysUser);
    }
}
