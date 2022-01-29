package com.initsrc.admin.system.dao;

import com.initsrc.admin.system.entity.dept.SysDept;
import com.initsrc.admin.system.entity.dept.vo.SysDeptListVo;
import com.initsrc.admin.system.entity.dept.vo.SysDeptDetailVo;
import com.initsrc.admin.system.entity.dept.dto.SysDeptQueryDto;
import com.initsrc.admin.system.entity.dept.dto.SysDeptSaveDto;

import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.admin.system.entity.dept.vo.SysDeptSelectVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 部门-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-28 21:44:02
 */
public interface SysDeptMapper extends BaseMapper<SysDept>{

    //查询部门列表
    List<SysDeptListVo> findList(SysDeptQueryDto dto);

    //查询部门详情
    SysDeptDetailVo selectDetailById(String id);

    List<NodeEntity> leftData(BaseEntity dto);

    //验证部门编号字段是否存在
    int checkerCodeOnly(SysDeptSaveDto dto);

    //根据父类ID获取子类数量
    int selectByParendId(String id);

    // parent_id字段关联查询列表
    List<NodeEntity> parentIdSelectData(BaseEntity dto);

}
