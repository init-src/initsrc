package com.initsrc.admin.system.service;

import com.initsrc.admin.base.entity.auth.vo.LoginUserInfoVo;
import com.initsrc.admin.system.entity.user.SysUser;
import com.initsrc.admin.system.entity.user.vo.SysUserListVo;
import com.initsrc.admin.system.entity.user.vo.SysUserDetailVo;
import com.initsrc.admin.system.entity.user.dto.SysUserQueryDto;
import com.initsrc.admin.system.entity.user.dto.SysUserSaveDto;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.admin.system.entity.user.vo.SysUserSelectVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.Result;
import java.util.List;

/**
* <p>
* 用户-服务类
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/
public interface SysUserService extends IService<SysUser>{

    //查询用户列表
    List<SysUserListVo> pageData(SysUserQueryDto dto);

    //查询用户左侧列表
    List<NodeEntity> leftData(BaseEntity dto);

    //查询用户下拉框列表
    SysUserSelectVo selectData(BaseEntity dto);

    //查询用户详情
    Result<SysUserDetailVo> detail(String id);

    //保存用户数据
    Result saveData(SysUserSaveDto dto);

    //更新用户数据
    Result updateData(SysUserSaveDto dto);

    //删除用户数据
    Result deleteData(String id);

    // 批量删除用户数据
    Result deletesData(List<String> ids);

    //根据用户名获取用户信息
    LoginUserInfoVo getLoginInfoByUsername(String userName);
}
