package com.initsrc.admin.system.service;

import com.initsrc.admin.system.entity.dept.SysDept;
import com.initsrc.admin.system.entity.dept.vo.SysDeptListVo;
import com.initsrc.admin.system.entity.dept.vo.SysDeptDetailVo;
import com.initsrc.admin.system.entity.dept.dto.SysDeptQueryDto;
import com.initsrc.admin.system.entity.dept.dto.SysDeptSaveDto;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.admin.system.entity.dept.vo.SysDeptSelectVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.Result;
import java.util.List;

/**
* <p>
* 部门-服务类
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 21:34:09
*/
public interface SysDeptService extends IService<SysDept>{

    //查询部门列表
    List<SysDeptListVo> pageData(SysDeptQueryDto dto);

    //查询部门左侧列表
    List<NodeEntity> leftData(BaseEntity dto);

    //查询部门下拉框列表
    SysDeptSelectVo selectData(BaseEntity dto);

    //查询部门详情
    Result<SysDeptDetailVo> detail(String id);

    //保存部门数据
    Result saveData(SysDeptSaveDto dto);

    //更新部门数据
    Result updateData(SysDeptSaveDto dto);

    //删除部门数据
    Result deleteData(String id);

    //获取部门权限列表
    List<NodeEntity> getPowerTreeNode(BaseEntity dto);
}
