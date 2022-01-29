package com.initsrc.admin.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.admin.system.entity.role.vo.SysRoleListVo;
import com.initsrc.admin.system.entity.role.vo.SysRoleDetailVo;
import com.initsrc.admin.system.entity.role.dto.SysRoleQueryDto;
import com.initsrc.admin.system.entity.role.dto.SysRoleSaveDto;
import com.initsrc.admin.system.service.SysRoleService;
import com.initsrc.admin.system.entity.role.vo.SysRoleSelectVo;
import com.initsrc.common.annotation.LoginUser;
import com.initsrc.common.annotation.LogAnnotation;
import com.initsrc.common.base.BaseEntity;
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
 * 角色-前端控制器
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-28 14:57:28
 */

@RestController
@RequestMapping("/webApi/system/role")
@Api(tags = "系统管理-角色管理")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 查询角色列表
     *
     * @param  dto
     * @return Result<PageResult<SysRoleListVo>>
     */
    @GetMapping("/page")
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询了角色数据")
    @RequiresPermissions("p:system:role:page")
    @LoginUser
    public Result<PageResult<SysRoleListVo>> pageData(@Validated SysRoleQueryDto dto){
        Page<SysRoleListVo> page = PageHelper.startPage(dto.page, dto.limit,"a.create_time "+dto.getSort());
        List<SysRoleListVo> list = sysRoleService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }


    /**
     * 查询角色下拉框列表
     * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
     * @param  dto
     * @return  Result<SysRoleSelectVo>
     */
    @GetMapping("/selectPage")
    @ApiOperation(value = "查询角色下拉框列表", notes = "查询角色下拉框列表")
    @RequiresPermissions("p:system:role:page")
    @LoginUser
    public Result<SysRoleSelectVo> selectData(BaseEntity dto){
        SysRoleSelectVo list = sysRoleService.selectData(dto);
        return Result.success(list);
    }

    /**
     * 查询角色详情
     *
     * @param id
     * @return Result<SysRoleDetailVo>
     */
    @GetMapping("/detail")
    @ApiOperation(value = "查询角色详情", notes = "查询角色详情")
    @RequiresPermissions("p:system:role:page")
    public Result<SysRoleDetailVo> detail(@RequestParam(value = "id") String id){
        return sysRoleService.detail(id);
    }

    /**
     * 保存角色数据
     *
     * @param  dto
     * @return Result
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存角色数据", notes = "保存角色数据")
    @RequiresPermissions("p:system:role:add")
    @LogAnnotation(operationType = LogOperateTypeEnum.ADD, operateContent = "新增了角色数据")
    @LoginUser
    public Result saveData(@Validated @RequestBody SysRoleSaveDto dto){
        return sysRoleService.saveData(dto);
    }

    /**
     * 更新角色数据
     *
     * @param  dto
     * @return Result
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新角色数据", notes = "更新角色数据")
    @RequiresPermissions("p:system:role:edit")
    @LogAnnotation(operationType = LogOperateTypeEnum.EDIT, operateContent = "更新了角色数据")
    @LoginUser
    public Result updateData(@Validated @RequestBody SysRoleSaveDto dto){
        return sysRoleService.updateData(dto);
    }

    /**
     * 删除角色数据
     *
     * @param id
     * @return Result
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除角色数据", notes = "删除角色数据")
    @RequiresPermissions("p:system:role:del")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "删除了角色数据")
    public Result deleteData(@RequestParam(value = "id") String id){
        return sysRoleService.deleteData(id);
    }

    /**
     * 批量删除角色数据
     *
     * @param ids
     * @return Result
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "批量删除角色数据", notes = "批量删除角色数据")
    @RequiresPermissions("p:system:role:dels")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "批量删除了角色数据")
    public Result deletesData(@RequestParam(value = "ids") List<String> ids){
        return sysRoleService.deletesData(ids);
    }

}
