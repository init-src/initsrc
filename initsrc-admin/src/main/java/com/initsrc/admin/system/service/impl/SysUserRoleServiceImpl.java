package com.initsrc.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.initsrc.admin.system.dao.SysUserRoleMapper;
import com.initsrc.admin.system.entity.user.SysUserRole;
import com.initsrc.admin.system.service.SysUserRoleService;

import org.springframework.stereotype.Service;
/**
 * <p>
 * 用户角色关联表-服务实现类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-28 18:39:33
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
