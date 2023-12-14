package com.example.service;

import com.example.dto.PageDto;
import com.example.entity.SysXiadan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.SysXiadanVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-12-04
 */
public interface SysXiadanService extends IService<SysXiadan> {

    PageDto<SysXiadan> getList(SysXiadanVo sysXiadanVo);

    void save(SysXiadanVo sysXiadanVo);

    void delete(Long id);
}
