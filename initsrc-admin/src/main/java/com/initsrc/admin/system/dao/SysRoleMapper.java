package com.initsrc.admin.system.dao;

import com.initsrc.admin.system.entity.role.SysRole;
import com.initsrc.admin.system.entity.role.vo.SysRoleListVo;
import com.initsrc.admin.system.entity.role.vo.SysRoleDetailVo;
import com.initsrc.admin.system.entity.role.dto.SysRoleQueryDto;
import com.initsrc.admin.system.entity.role.dto.SysRoleSaveDto;

import com.initsrc.admin.system.entity.role.vo.SysRoleSelectVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.initsrc.common.base.NodeEntity;

import java.util.List;

/**
 * <p>
 * 角色-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-28 14:57:28
 */
public interface SysRoleMapper extends BaseMapper<SysRole>{

    //查询角色列表
    List<SysRoleListVo> findList(SysRoleQueryDto dto);

    //查询角色详情
    SysRoleDetailVo selectDetailById(String id);

    // role_id字段关联查询列表
    List<NodeEntity> roleIdSelectData();

}
