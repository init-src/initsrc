package com.initsrc.admin.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.admin.system.entity.log.dto.SysLogQueryDto;
import com.initsrc.admin.system.entity.log.vo.SysLogDetailVo;
import com.initsrc.admin.system.entity.log.vo.SysLogListVo;
import com.initsrc.admin.system.service.SysLogService;
import com.initsrc.common.annotation.LogAnnotation;
import com.initsrc.common.annotation.LoginUser;
import com.initsrc.common.base.PageResult;
import com.initsrc.common.base.Result;
import com.initsrc.common.enums.LogOperateTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 操作日志-前端控制器
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-31 16:52:03
 */

@RestController
@RequestMapping("/webApi/system/log")
@Api(tags = "系统管理-操作日志")
public class SysLogController {
    @Resource
    private SysLogService sysLogService;

    /**
     * 查询操作日志列表
     *
     * @param dto
     * @return Result<PageResult < SysLogListVo>>
     */
    @GetMapping("/page")
    @ApiOperation(value = "查询操作日志列表", notes = "查询操作日志列表")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询了操作日志数据")
    @RequiresPermissions("p:system:log:page")
    @LoginUser
    public Result<PageResult<SysLogListVo>> pageData(@Validated SysLogQueryDto dto) {
        Page<SysLogListVo> page = PageHelper.startPage(dto.page, dto.limit, "a.create_time " + dto.getSort());
        List<SysLogListVo> list = sysLogService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }


    /**
     * 查询操作日志详情
     *
     * @param id
     * @return Result<SysLogDetailVo>
     */
    @GetMapping("/detail")
    @ApiOperation(value = "查询操作日志详情", notes = "查询操作日志详情")
    @RequiresPermissions("p:system:log:page")
    public Result<SysLogDetailVo> detail(@RequestParam(value = "id") String id) {
        return sysLogService.detail(id);
    }

}
