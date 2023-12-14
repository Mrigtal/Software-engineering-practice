package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.CommonDto;
import com.example.entity.SysUser;
import com.example.dao.SysUserMapper;
import com.example.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.CopyUtil;
import com.example.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-12-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper userMapper;

    @Override
    public UserVo login(UserVo userVo, CommonDto<UserVo> commonDto) {
        //通过前端传来的用户名去查询数据
        SysUser userDb = selectByUserName(userVo.getUsername());
        //查看是否为空,如果为空就提示用户名不存在或密码不正确
        if(ObjectUtils.isEmpty(userDb)){
            commonDto.setMessage("用户不存在或密码不正确");
            commonDto.setSuccess(false);
        }else {
            //密码一致 登录成功
            if(userDb.getPassword().equals(userVo.getPassword())){
                UserVo userVo1 = CopyUtil.copy(userDb, UserVo.class);
                return userVo1;
            }else {
                //密码不对
                commonDto.setMessage("用户不存在或密码不正确");
                commonDto.setSuccess(false);
            }
        }
        return userVo;
    }

    @Override
    public void save(UserVo userVo, CommonDto<UserVo> commonDto) {
        SysUser user = CopyUtil.copy(userVo, SysUser.class);
        if(!ObjectUtils.isEmpty(userVo.getId())){
            userMapper.updateById(user);
        }
    }

    /**
     * 查询用户名是否存在
     */
    public SysUser selectByUserName(String userName){
        //构造条件
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        //根据传来的用户名去数据库查询
        wrapper.lambda().eq(SysUser::getUsername,userName);
        //查询成功返回数据
        List<SysUser> userList = userMapper.selectList(wrapper);
        //如果为空就说明没有这个用户名,就可以注册
        if(CollectionUtils.isEmpty(userList)){
            return null;
            //如果有就返回所有信息
        }else {
            return userList.get(0);
        }
    }

}
