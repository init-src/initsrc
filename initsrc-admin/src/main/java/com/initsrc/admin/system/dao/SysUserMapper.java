package com.initsrc.admin.system.dao;

import com.initsrc.admin.base.entity.auth.vo.AuthInfoVo;
import com.initsrc.admin.base.entity.auth.vo.LoginUserInfoVo;
import com.initsrc.admin.system.entity.user.SysUser;
import com.initsrc.admin.system.entity.user.dto.SysUserSaveDto;
import com.initsrc.admin.system.entity.user.vo.SysUserListVo;
import com.initsrc.admin.system.entity.user.vo.SysUserDetailVo;
import com.initsrc.admin.system.entity.user.dto.SysUserQueryDto;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.admin.system.entity.user.vo.SysUserSelectVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
* <p>
    * 用户-服务类
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/
public interface SysUserMapper extends BaseMapper<SysUser>{

    //查询用户列表
    List<SysUserListVo> findList(SysUserQueryDto dto);

    //查询用户详情
    SysUserDetailVo selectDetailById(String id);

    List<NodeEntity> leftData(BaseEntity dto);

    //验证用户名字段是否存在
    int checkerUserNameOnly(SysUserSaveDto dto);


    //根据用户名获取用户信息
    LoginUserInfoVo getLoginInfoByUsername(String userName);

    //根据用户ID获取登录信息
    AuthInfoVo getLoginPermByUserId(String authId);

    //根据角色ID获取用户
    List<SysUserListVo> findUserByRoleId(String id);
}
