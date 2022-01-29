package com.initsrc.admin.system.dao;

import com.initsrc.admin.system.entity.perm.SysPerm;
import com.initsrc.admin.system.entity.perm.vo.SysPermListVo;
import com.initsrc.admin.system.entity.perm.vo.SysPermDetailVo;
import com.initsrc.admin.system.entity.perm.dto.SysPermQueryDto;
import com.initsrc.common.base.LoginPermVo;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.admin.system.entity.perm.vo.SysPermSelectVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 权限表-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-21 12:26:24
 */
public interface SysPermMapper extends BaseMapper<SysPerm>{

    //查询权限表列表
    List<SysPermListVo> findList(SysPermQueryDto dto);

    //查询权限表详情
    SysPermDetailVo selectDetailById(String id);

    List<NodeEntity> leftData(BaseEntity dto);

    //根据父类ID获取子类数量
    int selectByParendId(String id);

    // parent_id字段关联查询列表
    List<NodeEntity> parentIdSelectData();

    //获取所有权限
    List<LoginPermVo> getAllPermList();
    //根据角色获取权限
    List<LoginPermVo> getPermListByRoleId(String roleId);
}
