package com.initsrc.admin.base.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.admin.base.entity.home.dto.HomeInfoDto;
import com.initsrc.admin.base.entity.home.vo.HomeInfoVo;
import com.initsrc.admin.base.service.HomeService;
import com.initsrc.admin.system.entity.log.dto.SysLogQueryDto;
import com.initsrc.admin.system.entity.log.vo.SysLogListVo;
import com.initsrc.admin.system.entity.notice.dto.SysNoticeQueryDto;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeDetailVo;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeListVo;
import com.initsrc.admin.system.service.SysLogService;
import com.initsrc.admin.system.service.SysNoticeService;
import com.initsrc.common.annotation.LoginUser;
import com.initsrc.common.base.PageResult;
import com.initsrc.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  基础模块-首页模块
 * </p>
 *
 * @author INITSRC
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/webApi/home")
@Api(tags = "基础模块-首页模块")
public class HomeController {

    @Resource
    private SysNoticeService sysNoticeService;

    @Resource
    private HomeService homeService;

    @Resource
    private SysLogService sysLogService;

    /**
     * 查询通知公告列表
     *
     * @param  dto
     * @return Result<PageResult<SysNoticeListVo>>
     */
    @GetMapping("/noticeList")
    @ApiOperation(value = "查询通知公告列表", notes = "查询通知公告列表")
    @LoginUser
    public Result<PageResult<SysNoticeListVo>> pageData(@Validated SysNoticeQueryDto dto){
        Page<SysNoticeListVo> page = PageHelper.startPage(dto.page, dto.limit,"a.create_time "+dto.getSort());
        List<SysNoticeListVo> list = sysNoticeService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }

    /**
     * 查询通知公告详情
     *
     * @param id
     * @return Result<SysNoticeDetailVo>
     */
    @GetMapping("/detailNotice")
    @ApiOperation(value = "查询通知公告详情", notes = "查询通知公告详情")
    public Result<SysNoticeDetailVo> detail(@RequestParam(value = "id") String id){
        return sysNoticeService.detail(id);
    }

    /**
     * 查询操作日志列表
     *
     * @param dto
     * @return Result<PageResult < SysLogListVo>>
     */
    @GetMapping("/logList")
    @ApiOperation(value = "查询操作日志列表", notes = "查询操作日志列表")
    @LoginUser
    public Result<PageResult<SysLogListVo>> logList(@Validated SysLogQueryDto dto) {
        Page<SysLogListVo> page = PageHelper.startPage(dto.page, dto.limit, "a.create_time " + dto.getSort());
        List<SysLogListVo> list = sysLogService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }



    /**
     * 获取首页信息
     *
     * @param  dto
     * @return Result<PageResult<SysNoticeListVo>>
     */
    @GetMapping("/homeInfo")
    @ApiOperation(value = "获取首页信息", notes = "获取首页信息")
    @LoginUser
    public Result<HomeInfoVo> getHomeInfo(@Validated HomeInfoDto dto){
        return  homeService.getHomeInfo(dto);
    }
}
