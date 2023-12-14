package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.SysCategoryShopMapper;
import com.example.dao.SysShopMapper;
import com.example.dto.PageDto;
import com.example.entity.SysCategoryShop;
import com.example.entity.SysShop;
import com.example.service.ISysCategoryShopService;
import com.example.service.SysShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.CopyUtil;
import com.example.vo.SysShopVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
public class SysShopServiceImpl extends ServiceImpl<SysShopMapper, SysShop> implements SysShopService {

    @Resource
    private SysShopMapper sysShopMapper;

    @Override
    public List<SysShopVo> getList(SysShopVo sysShopVo) {
//        QueryWrapper<SysShop> queryWrapper = new QueryWrapper<>();
//        if (!org.springframework.util.ObjectUtils.isEmpty(sysShopVo.getShopname())) {
//            queryWrapper.lambda().eq(SysShop::getShopname, sysShopVo.getShopname());
//        }
//        if (!org.springframework.util.ObjectUtils.isEmpty(sysShopVo.getId())) {
//            queryWrapper.lambda().eq(SysShop::getId, sysShopVo.getId());
//        }
//        if (!org.springframework.util.ObjectUtils.isEmpty(sysShopVo.getStatus())) {
//            queryWrapper.lambda().eq(SysShop::getStatus, sysShopVo.getStatus());
//        }
//        Page<SysShop> page = new Page<>(sysShopVo.getPage(), sysShopVo.getSize());
//        IPage<SysShop> tProductIPage = sysShopMapper.selectPage(page, queryWrapper);
//        PageDto<SysShop> pageResp = new PageDto<>();
//        pageResp.setTotal(tProductIPage.getTotal());
//        pageResp.setList(tProductIPage.getRecords());
//        return pageResp;
        return sysShopMapper.getList();
    }


    @Override
    public PageDto<SysShop> getList1(SysShopVo sysShopVo) {
        QueryWrapper<SysShop> queryWrapper = new QueryWrapper<>();

        if (!org.springframework.util.ObjectUtils.isEmpty(sysShopVo.getStatus())) {
            queryWrapper.lambda().ne(SysShop::getStatus, sysShopVo.getStatus());
        }
        Page<SysShop> page = new Page<>(sysShopVo.getPage(), sysShopVo.getSize());
        IPage<SysShop> tProductIPage = sysShopMapper.selectPage(page, queryWrapper);
        PageDto<SysShop> pageResp = new PageDto<>();
        pageResp.setTotal(tProductIPage.getTotal());
        pageResp.setList(tProductIPage.getRecords());
        return pageResp;
    }


    @Resource
    private ISysCategoryShopService categoryShopService;
    @Resource
    private SysCategoryShopMapper categoryShopMapper;

    @Override
    public void save(SysShopVo sysShopVo) {
        SysShop sysShop = CopyUtil.copy(sysShopVo, SysShop.class);
        if (ObjectUtils.isEmpty(sysShopVo.getId())) {
            sysShopMapper.insert(sysShop);
        } else {
            sysShopMapper.updateById(sysShop);
            if (sysShopVo.getCategoryId() != null) {
                // 如果需要修改商品的分类
                // 1.删除原有的商品分类
                LambdaQueryWrapper<SysCategoryShop> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(SysCategoryShop::getShopId, sysShopVo.getId());
                categoryShopMapper.delete(queryWrapper);
                // 2.添加新的商品分类
                SysCategoryShop categoryShop = new SysCategoryShop();
                categoryShop.setShopId(sysShop.getId());
                categoryShop.setCategoryId(sysShopVo.getCategoryId());
                categoryShopService.save(categoryShop);
            }
        }
    }

    @Override
    public void delete(Long id) {
        sysShopMapper.deleteById(id);
    }

    @Override
    public void decreaseCount(String shopIdStr) {
        long shopId = Long.parseLong(shopIdStr);
        LambdaUpdateWrapper<SysShop> shopLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        shopLambdaUpdateWrapper.eq(SysShop::getId, shopId);
        shopLambdaUpdateWrapper.setSql("count = count - 1");
        update(shopLambdaUpdateWrapper);

        // 获取更新后的 SysShop 对象
        SysShop updatedShop = getById(shopId);
        // 判断 count 是否为 0，然后修改 status
        if (updatedShop != null && updatedShop.getCount() == 0) {
            LambdaUpdateWrapper<SysShop> statusUpdateWrapper = new LambdaUpdateWrapper<>();
            statusUpdateWrapper.eq(SysShop::getId, shopId);
            statusUpdateWrapper.setSql("status = 2");
            // 执行更新
            update(statusUpdateWrapper);
        }
    }

    @Override
    public List<SysShopVo> queryByCategoryId(Long categoryId) {
        return sysShopMapper.queryByCategoryId(categoryId);
    }

}
