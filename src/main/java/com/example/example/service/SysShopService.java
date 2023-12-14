package com.example.service;

import com.example.dto.PageDto;
import com.example.entity.SysShop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.SysShopVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2022-12-04
 */
public interface SysShopService extends IService<SysShop> {

    List<SysShopVo> getList(SysShopVo sysShopVo);

    PageDto<SysShop> getList1(SysShopVo sysShopVo);
    void save(SysShopVo sysShopVo);

    void delete(Long id);

    void decreaseCount(String shopid);

    List<SysShopVo> queryByCategoryId(Long categoryId);

}
