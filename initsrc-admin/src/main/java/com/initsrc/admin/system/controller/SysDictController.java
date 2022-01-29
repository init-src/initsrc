package com.initsrc.admin.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.admin.system.entity.dict.vo.SysDictListVo;
import com.initsrc.admin.system.entity.dict.vo.SysDictDetailVo;
import com.initsrc.admin.system.entity.dict.dto.SysDictQueryDto;
import com.initsrc.admin.system.entity.dict.dto.SysDictSaveDto;
import com.initsrc.admin.system.service.SysDictService;
import com.initsrc.common.annotation.LoginUser;
import com.initsrc.common.annotation.LogAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.initsrc.common.base.Result;
import com.initsrc.common.base.PageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
import javax.annotation.Resource;
import com.initsrc.common.enums.LogOperateTypeEnum;
import java.util.List;

/**
 * <p>
 * 字典表-前端控制器
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-20 16:23:41
 */

@RestController
@RequestMapping("/webApi/system/dict")
@Api(tags = "系统管理-字典模块")
public class SysDictController {
    @Resource
    private SysDictService sysDictService;

    /**
     * 查询字典表列表
     *
     * @param  dto
     * @return Result<PageResult<SysDictListVo>>
     */
    @GetMapping("/page")
    @ApiOperation(value = "查询字典表列表", notes = "查询字典表列表")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询了字典表数据")
    @RequiresPermissions("p:system:dict:page")
    @LoginUser
    public Result<PageResult<SysDictListVo>> pageData(@Validated SysDictQueryDto dto){
        Page<SysDictListVo> page = PageHelper.startPage(dto.page, dto.limit,"a.create_time "+dto.getSort());
        List<SysDictListVo> list = sysDictService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }



    /**
     * 查询字典表详情
     *
     * @param id
     * @return Result<SysDictDetailVo>
     */
    @GetMapping("/detail")
    @ApiOperation(value = "查询字典表详情", notes = "查询字典表详情")
    @RequiresPermissions("p:system:dict:page")
    public Result<SysDictDetailVo> detail(@RequestParam(value = "id") String id){
        return sysDictService.detail(id);
    }

    /**
     * 保存字典表数据
     *
     * @param  dto
     * @return Result
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存字典表数据", notes = "保存字典表数据")
    @RequiresPermissions("p:system:dict:add")
    @LogAnnotation(operationType = LogOperateTypeEnum.ADD, operateContent = "新增了字典表数据")
    @LoginUser
    public Result saveData(@Validated @RequestBody SysDictSaveDto dto){
        return sysDictService.saveData(dto);
    }

    /**
     * 更新字典表数据
     *
     * @param  dto
     * @return Result
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新字典表数据", notes = "更新字典表数据")
    @RequiresPermissions("p:system:dict:edit")
    @LogAnnotation(operationType = LogOperateTypeEnum.EDIT, operateContent = "更新了字典表数据")
    @LoginUser
    public Result updateData(@Validated @RequestBody SysDictSaveDto dto){
        return sysDictService.updateData(dto);
    }

    /**
     * 删除字典表数据
     *
     * @param id
     * @return Result
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除字典表数据", notes = "删除字典表数据")
    @RequiresPermissions("p:system:dict:del")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "删除了字典表数据")
    public Result deleteData(@RequestParam(value = "id") String id){
        return sysDictService.deleteData(id);
    }

    /**
     * 批量删除字典表数据
     *
     * @param ids
     * @return Result
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "批量删除字典表数据", notes = "批量删除字典表数据")
    @RequiresPermissions("p:system:dict:dels")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "批量删除了字典表数据")
    public Result deletesData(@RequestParam(value = "ids") List<String> ids){
        return sysDictService.deletesData(ids);
    }

}
