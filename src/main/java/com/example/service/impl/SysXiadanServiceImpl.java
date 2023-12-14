package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dto.PageDto;
import com.example.entity.SysXiadan;
import com.example.dao.SysXiadanMapper;
import com.example.service.SysShopService;
import com.example.service.SysXiadanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.CopyUtil;
import com.example.vo.SysXiadanVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-12-04
 */
@Service
public class SysXiadanServiceImpl extends ServiceImpl<SysXiadanMapper, SysXiadan> implements SysXiadanService {

    @Resource
    private SysXiadanMapper sysXiadanMapper;
    @Resource
    private SysShopService shopService;


    @Override
    public PageDto<SysXiadan> getList(SysXiadanVo sysXiadanVo) {
        QueryWrapper<SysXiadan> queryWrapper = new QueryWrapper<>();
        if(!org.springframework.util.ObjectUtils.isEmpty(sysXiadanVo.getXiadanname())){
            queryWrapper.lambda().eq(SysXiadan::getXiadanname,sysXiadanVo.getXiadanname());
        }
        if(!org.springframework.util.ObjectUtils.isEmpty(sysXiadanVo.getId())){
            queryWrapper.lambda().eq(SysXiadan::getId,sysXiadanVo.getId());
        }
        Page<SysXiadan> page = new Page<>(sysXiadanVo.getPage(), sysXiadanVo.getSize());
        IPage<SysXiadan> tProductIPage = sysXiadanMapper.selectPage(page, queryWrapper);
        PageDto<SysXiadan> pageResp = new PageDto<>();
        pageResp.setTotal(tProductIPage.getTotal());
        pageResp.setList(tProductIPage.getRecords());
        return pageResp;
    }

    @Override
    public void save(SysXiadanVo sysXiadanVo) {
        SysXiadan sysXiadan = CopyUtil.copy(sysXiadanVo, SysXiadan.class);
        if(ObjectUtils.isEmpty(sysXiadanVo.getId())){
            sysXiadanMapper.insert(sysXiadan);
            shopService.decreaseCount(sysXiadanVo.getShopid());
        }else {
            sysXiadanMapper.updateById(sysXiadan);
        }
    }

    @Override
    public void delete(Long id) {
        sysXiadanMapper.deleteById(id);
    }
}
