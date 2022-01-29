package com.initsrc.admin.system.service;

import com.initsrc.admin.system.entity.dict.SysDict;
import com.initsrc.admin.system.entity.dict.vo.SysDictListVo;
import com.initsrc.admin.system.entity.dict.vo.SysDictDetailVo;
import com.initsrc.admin.system.entity.dict.dto.SysDictQueryDto;
import com.initsrc.admin.system.entity.dict.dto.SysDictSaveDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.Result;
import java.util.List;
import java.util.Map;

/**
* <p>
* 字典表-服务类
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-20 15:56:31
*/
public interface SysDictService extends IService<SysDict>{

    //查询字典表列表
    List<SysDictListVo> pageData(SysDictQueryDto dto);

    //查询字典表详情
    Result<SysDictDetailVo> detail(String id);

    //保存字典表数据
    Result saveData(SysDictSaveDto dto);

    //更新字典表数据
    Result updateData(SysDictSaveDto dto);

    //删除字典表数据
    Result deleteData(String id);

    //批量删除字典表数据
    Result deletesData(List<String> ids);

    //获取字典
    Map<String, Object> getDictMap();
}
