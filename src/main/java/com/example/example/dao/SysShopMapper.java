package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.PageDto;
import com.example.entity.SysShop;
import com.example.vo.SysShopVo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-12-04
 */
public interface SysShopMapper extends BaseMapper<SysShop> {

    List<SysShopVo> queryByCategoryId(Long categoryId);

    List<SysShopVo> getList();
}
