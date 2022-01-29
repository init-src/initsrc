package com.initsrc.admin.system.dao;

import com.initsrc.admin.base.entity.home.dto.HomeInfoDto;
import com.initsrc.admin.system.entity.log.SysLog;
import com.initsrc.admin.system.entity.log.vo.SysLogListVo;
import com.initsrc.admin.system.entity.log.vo.SysLogDetailVo;
import com.initsrc.admin.system.entity.log.dto.SysLogQueryDto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 操作日志-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-31 16:52:03
 */
public interface SysLogMapper extends BaseMapper<SysLog>{

    //查询操作日志列表
    List<SysLogListVo> findList(SysLogQueryDto dto);

    //查询操作日志详情
    SysLogDetailVo selectDetailById(String id);

    //获取首页近七日登录日志信息
    List<SysLogListVo> getLogByHomeInfo(HomeInfoDto dto);

    //根据操作类型获取数量
    int getCountByOprType(int operateCode);
}
