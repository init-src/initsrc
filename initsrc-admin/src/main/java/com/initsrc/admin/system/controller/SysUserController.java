package com.initsrc.admin.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.admin.system.entity.user.vo.SysUserListVo;
import com.initsrc.admin.system.entity.user.vo.SysUserDetailVo;
import com.initsrc.admin.system.entity.user.dto.SysUserQueryDto;
import com.initsrc.admin.system.entity.user.dto.SysUserSaveDto;
import com.initsrc.admin.system.service.SysUserService;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.admin.system.entity.user.vo.SysUserSelectVo;
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
    * 用户-前端控制器
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/

@RestController
@RequestMapping("/webApi/system/user")
@Api(tags = "系统管理-用户管理")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    /**
    * 查询用户列表
    *
    * @param  dto
    * @return Result<PageResult<SysUserListVo>>
    */
    @GetMapping("/page")
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询了用户数据")
    @RequiresPermissions("p:system:user:page")
    @LoginUser
    public Result<PageResult<SysUserListVo>> pageData(@Validated SysUserQueryDto dto){
        Page<SysUserListVo> page = PageHelper.startPage(dto.page, dto.limit,"a.create_time "+dto.getSort());
        List<SysUserListVo> list = sysUserService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }

    /**
    * 查询用户左侧列表
    * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
    * @param  dto
    * @return  Result<List<NodeEntity>>
    */
    @GetMapping("/leftPage")
    @ApiOperation(value = "查询用户左侧列表", notes = "查询用户左侧列表")
    @RequiresPermissions("p:system:user:page")
    @LoginUser
    public Result<List<NodeEntity>> leftPage(BaseEntity dto){
        List<NodeEntity> list = sysUserService.leftData(dto);
        return Result.success(list);
    }

    /**
    * 查询用户下拉框列表
    * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
    * @param  dto
    * @return  Result<SysUserSelectVo>
    */
    @GetMapping("/selectPage")
    @ApiOperation(value = "查询用户下拉框列表", notes = "查询用户下拉框列表")
    @RequiresPermissions("p:system:user:page")
    @LoginUser
    public Result<SysUserSelectVo> selectData(BaseEntity dto){
        SysUserSelectVo list = sysUserService.selectData(dto);
       return Result.success(list);
   }

    /**
    * 查询用户详情
    *
    * @param id
    * @return Result<SysUserDetailVo>
    */
    @GetMapping("/detail")
    @ApiOperation(value = "查询用户详情", notes = "查询用户详情")
    @RequiresPermissions("p:system:user:page")
    public Result<SysUserDetailVo> detail(@RequestParam(value = "id") String id){
        return sysUserService.detail(id);
    }

    /**
      * 保存用户数据
      *
      * @param  dto
      * @return Result
    */
    @PostMapping("/save")
    @ApiOperation(value = "保存用户数据", notes = "保存用户数据")
    @RequiresPermissions("p:system:user:add")
    @LogAnnotation(operationType = LogOperateTypeEnum.ADD, operateContent = "新增了用户数据")
    @LoginUser
    public Result saveData(@Validated @RequestBody SysUserSaveDto dto){
        return sysUserService.saveData(dto);
    }

    /**
    * 更新用户数据
    *
    * @param  dto
    * @return Result
    */
    @PostMapping("/update")
    @ApiOperation(value = "更新用户数据", notes = "更新用户数据")
    @RequiresPermissions("p:system:user:edit")
    @LogAnnotation(operationType = LogOperateTypeEnum.EDIT, operateContent = "更新了用户数据")
    @LoginUser
    public Result updateData(@Validated @RequestBody SysUserSaveDto dto){
        return sysUserService.updateData(dto);
    }

    /**
    * 删除用户数据
    *
    * @param id
    * @return Result
    */
    @PostMapping("/delete")
    @ApiOperation(value = "删除用户数据", notes = "删除用户数据")
    @RequiresPermissions("p:system:user:del")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "删除了用户数据")
    public Result deleteData(@RequestParam(value = "id") String id){
        return sysUserService.deleteData(id);
    }

    /**
    * 批量删除用户数据
    *
    * @param ids
    * @return Result
    */
    @PostMapping("/deletes")
    @ApiOperation(value = "批量删除用户数据", notes = "批量删除用户数据")
    @RequiresPermissions("p:system:user:dels")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "批量删除了用户数据")
    public Result deletesData(@RequestParam(value = "ids") List<String> ids){
        return sysUserService.deletesData(ids);
    }

}
