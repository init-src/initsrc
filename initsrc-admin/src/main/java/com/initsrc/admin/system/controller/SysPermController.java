package com.initsrc.admin.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.admin.system.entity.perm.vo.SysPermListVo;
import com.initsrc.admin.system.entity.perm.vo.SysPermDetailVo;
import com.initsrc.admin.system.entity.perm.dto.SysPermQueryDto;
import com.initsrc.admin.system.entity.perm.dto.SysPermSaveDto;
import com.initsrc.admin.system.service.SysPermService;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.admin.system.entity.perm.vo.SysPermSelectVo;
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
 * 菜单-前端控制器
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */

@RestController
@RequestMapping("/webApi/system/perm")
@Api(tags = "系统管理-菜单")
public class SysPermController {
    @Resource
    private SysPermService sysPermService;

    /**
     * 查询菜单列表
     *
     * @param  dto
     * @return Result<PageResult<SysPermListVo>>
     */
    @GetMapping("/page")
    @ApiOperation(value = "查询菜单列表", notes = "查询菜单列表")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询了菜单数据")
    @RequiresPermissions("p:system:perm:page")
    @LoginUser
    public Result<PageResult<SysPermListVo>> pageData(@Validated SysPermQueryDto dto){
        Page<SysPermListVo> page = PageHelper.startPage(dto.page, dto.limit,"a.sort "+dto.getSort());
        List<SysPermListVo> list = sysPermService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }

    /**
     * 查询菜单左侧列表
     * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
     * @param  dto
     * @return  Result<List<NodeEntity>>
     */
    @GetMapping("/leftPage")
    @ApiOperation(value = "查询菜单左侧列表", notes = "查询菜单左侧列表")
    @RequiresPermissions("p:system:perm:page")
    @LoginUser
    public Result<List<NodeEntity>> leftPage(BaseEntity dto){
        List<NodeEntity> list = sysPermService.leftData(dto);
        return Result.success(list);
    }

    /**
     * 查询菜单下拉框列表
     * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
     * @param  dto
     * @return  Result<SysPermSelectVo>
     */
    @GetMapping("/selectPage")
    @ApiOperation(value = "查询菜单下拉框列表", notes = "查询菜单下拉框列表")
    @RequiresPermissions("p:system:perm:page")
    @LoginUser
    public Result<SysPermSelectVo> selectData(BaseEntity dto){
        SysPermSelectVo list = sysPermService.selectData(dto);
        return Result.success(list);
    }

    /**
     * 查询菜单详情
     *
     * @param id
     * @return Result<SysPermDetailVo>
     */
    @GetMapping("/detail")
    @ApiOperation(value = "查询菜单详情", notes = "查询菜单详情")
    @RequiresPermissions("p:system:perm:page")
    public Result<SysPermDetailVo> detail(@RequestParam(value = "id") String id){
        return sysPermService.detail(id);
    }

    /**
     * 保存菜单数据
     *
     * @param  dto
     * @return Result
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存菜单数据", notes = "保存菜单数据")
    @RequiresPermissions("p:system:perm:add")
    @LogAnnotation(operationType = LogOperateTypeEnum.ADD, operateContent = "新增了菜单数据")
    @LoginUser
    public Result saveData(@Validated @RequestBody SysPermSaveDto dto){
        return sysPermService.saveData(dto);
    }

    /**
     * 更新菜单数据
     *
     * @param  dto
     * @return Result
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新菜单数据", notes = "更新菜单数据")
    @RequiresPermissions("p:system:perm:edit")
    @LogAnnotation(operationType = LogOperateTypeEnum.EDIT, operateContent = "更新了菜单数据")
    @LoginUser
    public Result updateData(@Validated @RequestBody SysPermSaveDto dto){
        return sysPermService.updateData(dto);
    }

    /**
     * 删除菜单数据
     *
     * @param id
     * @return Result
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除菜单数据", notes = "删除菜单数据")
    @RequiresPermissions("p:system:perm:del")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "删除了菜单数据")
    public Result deleteData(@RequestParam(value = "id") String id){
        return sysPermService.deleteData(id);
    }


}
