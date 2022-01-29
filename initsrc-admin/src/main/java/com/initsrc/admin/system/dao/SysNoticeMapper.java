package com.initsrc.admin.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.initsrc.admin.system.entity.notice.SysNotice;
import com.initsrc.admin.system.entity.notice.dto.SysNoticeQueryDto;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeDetailVo;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeListVo;

import java.util.List;

/**
* <p>
    * 通知公告-服务类
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-06-02 14:23:15
*/
public interface SysNoticeMapper extends BaseMapper<SysNotice>{

    //查询通知公告列表
    List<SysNoticeListVo> findList(SysNoticeQueryDto dto);

    //查询通知公告详情
    SysNoticeDetailVo selectDetailById(String id);

}
