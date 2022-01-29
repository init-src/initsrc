package com.initsrc.admin.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeListVo;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeDetailVo;
import com.initsrc.admin.system.entity.notice.dto.SysNoticeQueryDto;
import com.initsrc.admin.system.entity.notice.dto.SysNoticeSaveDto;
import com.initsrc.admin.system.service.SysNoticeService;
import com.initsrc.common.annotation.LoginUser;
import com.initsrc.common.annotation.LogAnnotation;
import com.initsrc.common.util.easypoi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.initsrc.common.base.Result;
import com.initsrc.common.base.PageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletResponse;
import com.initsrc.common.base.BaseEntity;
import io.lettuce.core.dynamic.annotation.Param;
import javax.validation.constraints.NotNull;
import javax.annotation.Resource;
import com.initsrc.common.enums.LogOperateTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 通知公告-前端控制器
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-06-07 17:02:27
 */

@RestController
@RequestMapping("/webApi/system/notice")
@Api(tags = "系统管理-通知公告")
public class SysNoticeController {
    @Resource
    private SysNoticeService sysNoticeService;

    /**
     * 查询通知公告列表
     *
     * @param  dto
     * @return Result<PageResult<SysNoticeListVo>>
     */
    @GetMapping("/page")
    @ApiOperation(value = "查询通知公告列表", notes = "查询通知公告列表")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询了通知公告数据")
    @RequiresPermissions("p:system:notice:page")
    @LoginUser
    public Result<PageResult<SysNoticeListVo>> pageData(@Validated SysNoticeQueryDto dto){
        Page<SysNoticeListVo> page = PageHelper.startPage(dto.page, dto.limit,"a.create_time "+dto.getSort());
        List<SysNoticeListVo> list = sysNoticeService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }

    /**
     * 导出导入模板
     *
     * @param
     * @return
     */
    @GetMapping("/downloadExcel")
    @RequiresPermissions("p:system:notice:import")
    @LoginUser
    public void downloadExcel(HttpServletResponse response) {
        List<SysNoticeSaveDto> list = new ArrayList<>();
        ExcelUtil.exoprtExcel(list, "通知公告模板",SysNoticeSaveDto.class, response);
    }

    /**
     * 导出通知公告数据
     *
     * @param dto
     * @return
     */
    @GetMapping("/export")
    @RequiresPermissions("p:system:notice:export")
    @ApiOperation(value = "导出通知公告", notes = "导出通知公告", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @LogAnnotation(operationType = LogOperateTypeEnum.EXPORT, operateContent = "查询了通知公告数据")
    @LoginUser
    public void export(HttpServletResponse response, @Validated SysNoticeQueryDto dto) {
        sysNoticeService.export(response,dto);
    }

    @ApiOperation(value = "通知公告批量导入 ", notes = "通知公告批量导入")
    @PostMapping("/import")
    @RequiresPermissions("p:system:notice:import")
    @LogAnnotation(operationType = LogOperateTypeEnum.IMPORT, operateContent = "导入了通知公告数据")
    @LoginUser
    public Result<String> batchImport(@Param("file") MultipartFile file, BaseEntity dto, HttpServletResponse response) throws Exception {
        return sysNoticeService.batchImport(file,dto,response);
    }


    /**
     * 查询通知公告详情
     *
     * @param id
     * @return Result<SysNoticeDetailVo>
     */
    @GetMapping("/detail")
    @ApiOperation(value = "查询通知公告详情", notes = "查询通知公告详情")
    @RequiresPermissions("p:system:notice:page")
    public Result<SysNoticeDetailVo> detail(@RequestParam(value = "id") String id){
        return sysNoticeService.detail(id);
    }

    /**
     * 保存通知公告数据
     *
     * @param  dto
     * @return Result
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存通知公告数据", notes = "保存通知公告数据")
    @RequiresPermissions("p:system:notice:add")
    @LogAnnotation(operationType = LogOperateTypeEnum.ADD, operateContent = "新增了通知公告数据")
    @LoginUser
    public Result saveData(@Validated @RequestBody SysNoticeSaveDto dto){
        return sysNoticeService.saveData(dto);
    }

    /**
     * 更新通知公告数据
     *
     * @param  dto
     * @return Result
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新通知公告数据", notes = "更新通知公告数据")
    @RequiresPermissions("p:system:notice:edit")
    @LogAnnotation(operationType = LogOperateTypeEnum.EDIT, operateContent = "更新了通知公告数据")
    @LoginUser
    public Result updateData(@Validated @RequestBody SysNoticeSaveDto dto){
        return sysNoticeService.updateData(dto);
    }

    /**
     * 删除通知公告数据
     *
     * @param id
     * @return Result
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除通知公告数据", notes = "删除通知公告数据")
    @RequiresPermissions("p:system:notice:del")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "删除了通知公告数据")
    public Result deleteData(@RequestParam(value = "id") String id){
        return sysNoticeService.deleteData(id);
    }

    /**
     * 批量删除通知公告数据
     *
     * @param ids
     * @return Result
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "批量删除通知公告数据", notes = "批量删除通知公告数据")
    @RequiresPermissions("p:system:notice:dels")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "批量删除了通知公告数据")
    public Result deletesData(@RequestParam(value = "ids") List<String> ids){
        return sysNoticeService.deletesData(ids);
    }

}
