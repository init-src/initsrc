package com.initsrc.admin.system.service;

import com.initsrc.admin.system.entity.perm.SysPerm;
import com.initsrc.admin.system.entity.perm.vo.SysPermListVo;
import com.initsrc.admin.system.entity.perm.vo.SysPermDetailVo;
import com.initsrc.admin.system.entity.perm.dto.SysPermQueryDto;
import com.initsrc.admin.system.entity.perm.dto.SysPermSaveDto;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.admin.system.entity.perm.vo.SysPermSelectVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.Result;
import java.util.List;

/**
 * <p>
 * 菜单-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */
public interface SysPermService extends IService<SysPerm>{

    //查询菜单列表
    List<SysPermListVo> pageData(SysPermQueryDto dto);

    //查询菜单左侧列表
    List<NodeEntity> leftData(BaseEntity dto);

    //查询菜单下拉框列表
    SysPermSelectVo selectData(BaseEntity dto);

    //查询菜单详情
    Result<SysPermDetailVo> detail(String id);

    //保存菜单数据
    Result saveData(SysPermSaveDto dto);

    //更新菜单数据
    Result updateData(SysPermSaveDto dto);

    //删除菜单数据
    Result deleteData(String id);


}
