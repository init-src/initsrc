package com.initsrc.admin.system.service;

import com.initsrc.admin.system.entity.log.SysLog;
import com.initsrc.admin.system.entity.log.vo.SysLogListVo;
import com.initsrc.admin.system.entity.log.vo.SysLogDetailVo;
import com.initsrc.admin.system.entity.log.dto.SysLogQueryDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.Result;
import java.util.List;

/**
 * <p>
 * 操作日志-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-31 16:52:03
 */
public interface SysLogService extends IService<SysLog>{

    //查询操作日志列表
    List<SysLogListVo> pageData(SysLogQueryDto dto);

    //查询操作日志详情
    Result<SysLogDetailVo> detail(String id);
}
