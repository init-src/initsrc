package com.initsrc.admin.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.admin.system.entity.dept.dto.SysDeptQueryDto;
import com.initsrc.admin.system.entity.dept.dto.SysDeptSaveDto;
import com.initsrc.admin.system.entity.dept.vo.SysDeptDetailVo;
import com.initsrc.admin.system.entity.dept.vo.SysDeptListVo;
import com.initsrc.admin.system.entity.dept.vo.SysDeptSelectVo;
import com.initsrc.admin.system.service.SysDeptService;
import com.initsrc.common.annotation.LogAnnotation;
import com.initsrc.common.annotation.LoginUser;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.PageResult;
import com.initsrc.common.base.Result;
import com.initsrc.common.enums.LogOperateTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* <p>
    * 部门-前端控制器
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 21:34:09
*/

@RestController
@RequestMapping("/webApi/system/dept")
@Api(tags = "系统管理-部门")
public class SysDeptController {
    @Resource
    private SysDeptService sysDeptService;

    /**
    * 查询部门列表
    *
    * @param  dto
    * @return Result<PageResult<SysDeptListVo>>
    */
    @GetMapping("/page")
    @ApiOperation(value = "查询部门列表", notes = "查询部门列表")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询了部门数据")
    @RequiresPermissions("p:system:dept:page")
    @LoginUser
    public Result<PageResult<SysDeptListVo>> pageData(@Validated SysDeptQueryDto dto){
        Page<SysDeptListVo> page = PageHelper.startPage(dto.page, dto.limit,"a.sort "+dto.getSort());
        List<SysDeptListVo> list = sysDeptService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }

    /**
    * 查询部门左侧列表
    * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
    * @param  dto
    * @return  Result<List<NodeEntity>>
    */
    @GetMapping("/leftPage")
    @ApiOperation(value = "查询部门左侧列表", notes = "查询部门左侧列表")
    @RequiresPermissions("p:system:dept:page")
    @LoginUser
    public Result<List<NodeEntity>> leftPage(BaseEntity dto){
        List<NodeEntity> list = sysDeptService.leftData(dto);
        return Result.success(list);
    }

    /**
    * 查询部门下拉框列表
    * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
    * @param  dto
    * @return  Result<SysDeptSelectVo>
    */
    @GetMapping("/selectPage")
    @ApiOperation(value = "查询部门下拉框列表", notes = "查询部门下拉框列表")
    @RequiresPermissions("p:system:dept:page")
    @LoginUser
    public Result<SysDeptSelectVo> selectData(BaseEntity dto){
        SysDeptSelectVo list = sysDeptService.selectData(dto);
       return Result.success(list);
   }

    /**
    * 查询部门详情
    *
    * @param id
    * @return Result<SysDeptDetailVo>
    */
    @GetMapping("/detail")
    @ApiOperation(value = "查询部门详情", notes = "查询部门详情")
    @RequiresPermissions("p:system:dept:page")
    public Result<SysDeptDetailVo> detail(@RequestParam(value = "id") String id){
        return sysDeptService.detail(id);
    }

    /**
      * 保存部门数据
      *
      * @param  dto
      * @return Result
    */
    @PostMapping("/save")
    @ApiOperation(value = "保存部门数据", notes = "保存部门数据")
    @RequiresPermissions("p:system:dept:add")
    @LogAnnotation(operationType = LogOperateTypeEnum.ADD, operateContent = "新增了部门数据")
    @LoginUser
    public Result saveData(@Validated @RequestBody SysDeptSaveDto dto){
        return sysDeptService.saveData(dto);
    }

    /**
    * 更新部门数据
    *
    * @param  dto
    * @return Result
    */
    @PostMapping("/update")
    @ApiOperation(value = "更新部门数据", notes = "更新部门数据")
    @RequiresPermissions("p:system:dept:edit")
    @LogAnnotation(operationType = LogOperateTypeEnum.EDIT, operateContent = "更新了部门数据")
    @LoginUser
    public Result updateData(@Validated @RequestBody SysDeptSaveDto dto){
        return sysDeptService.updateData(dto);
    }

    /**
    * 删除部门数据
    *
    * @param id
    * @return Result
    */
    @PostMapping("/delete")
    @ApiOperation(value = "删除部门数据", notes = "删除部门数据")
    @RequiresPermissions("p:system:dept:del")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "删除了部门数据")
    public Result deleteData(@RequestParam(value = "id") String id){
        return sysDeptService.deleteData(id);
    }


}
