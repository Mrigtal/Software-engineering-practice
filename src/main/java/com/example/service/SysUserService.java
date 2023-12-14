package com.example.service;

import com.example.dto.CommonDto;
import com.example.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-12-04
 */
public interface SysUserService extends IService<SysUser> {

    UserVo login(UserVo userVo, CommonDto<UserVo> commonDto);

    void save(UserVo userVo, CommonDto<UserVo> commonDto);
}
