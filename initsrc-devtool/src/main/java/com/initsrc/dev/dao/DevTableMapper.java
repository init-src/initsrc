package com.initsrc.dev.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.dev.entity.table.DevTable;
import com.initsrc.dev.entity.table.dto.DevTableQueryDto;
import com.initsrc.dev.entity.table.vo.DevTableDetailVo;
import com.initsrc.dev.entity.table.vo.DevTableListVo;

import java.util.List;

/**
 * <p>
 * 数据库表-服务类
 * </p>
 *
 * @author INITSRC
 * @since  2021-01-27 11:03:46
 */
public interface DevTableMapper extends BaseMapper<DevTable> {

    //查询数据库表列表
    List<DevTableListVo> findList(DevTableQueryDto dto);

    //查询数据库表详情
    DevTableDetailVo selectDetailById(String id);

    //根据DB获取数据
    DevTableDetailVo selectDetailByDb(String tableName);

    //获取所有数据库表
    List<DevTableDetailVo> findDetailList();

    //获取所有数据库表
    List<NodeEntity> getPermList();
}
