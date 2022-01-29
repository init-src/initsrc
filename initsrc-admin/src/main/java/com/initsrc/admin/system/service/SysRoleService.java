package com.initsrc.admin.system.service;

import com.initsrc.admin.system.entity.role.SysRole;
import com.initsrc.admin.system.entity.role.vo.SysRoleListVo;
import com.initsrc.admin.system.entity.role.vo.SysRoleDetailVo;
import com.initsrc.admin.system.entity.role.dto.SysRoleQueryDto;
import com.initsrc.admin.system.entity.role.dto.SysRoleSaveDto;
import com.initsrc.admin.system.entity.role.vo.SysRoleSelectVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.common.base.Result;
import java.util.List;

/**
 * <p>
 * 角色-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-28 14:57:28
 */
public interface SysRoleService extends IService<SysRole>{

    //查询角色列表
    List<SysRoleListVo> pageData(SysRoleQueryDto dto);

    //查询角色下拉框列表
    SysRoleSelectVo selectData(BaseEntity dto);

    //查询角色详情
    Result<SysRoleDetailVo> detail(String id);

    //保存角色数据
    Result saveData(SysRoleSaveDto dto);

    //更新角色数据
    Result updateData(SysRoleSaveDto dto);

    //删除角色数据
    Result deleteData(String id);

    // 批量删除角色数据
    Result deletesData(List<String> ids);

}
