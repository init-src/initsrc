package com.initsrc.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.initsrc.admin.system.entity.log.SysLog;
import com.initsrc.admin.system.entity.log.vo.SysLogListVo;
import com.initsrc.admin.system.entity.log.vo.SysLogDetailVo;
import com.initsrc.admin.system.entity.log.dto.SysLogQueryDto;
import com.initsrc.admin.system.service.SysLogService;
import com.initsrc.admin.system.dao.SysLogMapper;
import com.initsrc.common.base.Result;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
/**
 * <p>
 * 操作日志-服务实现类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-31 16:52:03
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {


    /**
     * 查询操作日志列表
     *
     * @param  dto
     * @return List<SysLogListVo>
     */
    @Override
    public List<SysLogListVo> pageData(SysLogQueryDto dto) {
        List<SysLogListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }


    /**
     * 查询操作日志详情
     *
     * @param id
     * @return Result<SysLogDetailVo>
     */
    @Override
    public Result<SysLogDetailVo> detail(String id) {
        SysLogDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if(detail != null){
            return Result.success(detail);
        }else{
            return Result.fail("查询失败");
        }
    }
}
