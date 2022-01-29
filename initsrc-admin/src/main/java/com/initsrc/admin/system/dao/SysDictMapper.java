package com.initsrc.admin.system.dao;

import com.initsrc.admin.system.entity.dict.SysDict;
import com.initsrc.admin.system.entity.dict.vo.SysDictListVo;
import com.initsrc.admin.system.entity.dict.vo.SysDictDetailVo;
import com.initsrc.admin.system.entity.dict.dto.SysDictQueryDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
* <p>
    * 字典表-服务类
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-20 15:56:31
*/
public interface SysDictMapper extends BaseMapper<SysDict>{

    //查询字典表列表
    List<SysDictListVo> findList(SysDictQueryDto dto);

    //查询字典表详情
    SysDictDetailVo selectDetailById(String id);

}
