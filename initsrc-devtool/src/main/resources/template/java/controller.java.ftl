package ${packageName}.${moduleName}.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}ListVo;
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}DetailVo;
import ${packageName}.${moduleName}.entity.${bizName}.dto.${className}QueryDto;
import ${packageName}.${moduleName}.entity.${bizName}.dto.${className}SaveDto;
import ${packageName}.${moduleName}.service.${className}Service;
<#if isSelect == '1' || isCategory == "2">
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}SelectVo;
</#if>
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
<#if isExcel == "1">
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletResponse;
import com.initsrc.common.base.BaseEntity;
import io.lettuce.core.dynamic.annotation.Param;
</#if>
import javax.validation.constraints.NotNull;
import javax.annotation.Resource;
import com.initsrc.common.enums.LogOperateTypeEnum;
import java.util.List;

/**
* <p>
    * ${functionName}-前端控制器
    * </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/

@RestController
@RequestMapping("/webApi/${moduleName}/${bizName}")
@Api(tags = "${moduleName}-${bizName}-${functionName}")
public class ${className}Controller {
    @Resource
    private ${className}Service ${className?uncap_first}Service;

    /**
    * 查询${functionName}列表
    *
    * @param  dto
    * @return Result<PageResult<${className}ListVo>>
    */
    @GetMapping("/page")
    @ApiOperation(value = "查询${functionName}列表", notes = "查询${functionName}列表")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询了${functionName}数据")
    @RequiresPermissions("p:${moduleName}:${bizName}:page")
    @LoginUser
    public Result<PageResult<${className}ListVo>> pageData(@Validated ${className}QueryDto dto){
        Page<${className}ListVo> page = PageHelper.startPage(dto.page, dto.limit<#list columns as field><#if field.columnName == 'create_time'>,"a.create_time "+dto.getSort()</#if></#list>);
        List<${className}ListVo> list = ${className?uncap_first}Service.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }

    <#if vueTableType =='2'>
    /**
    * 查询${functionName}左侧列表
    * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
    * @param  dto
    * @return  Result<List<NodeEntity>>
    */
    @GetMapping("/leftPage")
    @ApiOperation(value = "查询${functionName}左侧列表", notes = "查询${functionName}左侧列表")
    @RequiresPermissions("p:${moduleName}:${bizName}:page")
    @LoginUser
    public Result<List<NodeEntity>> leftPage(BaseEntity dto){
        List<NodeEntity> list = ${className?uncap_first}Service.leftData(dto);
        return Result.success(list);
    }
    </#if>

    <#if isSelect == '1'>
    /**
    * 查询${functionName}下拉框列表
    * 注意,请生成后把service、DAO以及SQL语句转移到对应模块
    * @param  dto
    * @return  Result<${className}SelectVo>
    */
    @GetMapping("/selectPage")
    @ApiOperation(value = "查询${functionName}下拉框列表", notes = "查询${functionName}下拉框列表")
    @RequiresPermissions("p:${moduleName}:${bizName}:page")
    @LoginUser
    public Result<${className}SelectVo> selectData(BaseEntity dto){
        ${className}SelectVo list = ${className?uncap_first}Service.selectData(dto);
       return Result.success(list);
   }
    </#if>

    <#if isExcel == "1">
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
    List<${className}SaveDto> list = new ArrayList<>();
        ExcelUtil.exoprtExcel(list, "${functionName}模板",${className}SaveDto.class, response);
    }

    /**
    * 导出${functionName}数据
    *
    * @param dto
    * @return
    */
    @GetMapping("/export")
    @RequiresPermissions("p:${moduleName}:${bizName}:export")
    @ApiOperation(value = "导出${functionName}", notes = "导出${functionName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @LogAnnotation(operationType = LogOperateTypeEnum.EXPORT, operateContent = "查询了${functionName}数据")
    @LoginUser
    public void export(HttpServletResponse response, @Validated ${className}QueryDto dto) {
        ${className?uncap_first}Service.export(response,dto);
    }

    @ApiOperation(value = "${functionName}批量导入 ", notes = "${functionName}批量导入")
    @PostMapping("/import")
    @RequiresPermissions("p:${moduleName}:${bizName}:import")
    @LogAnnotation(operationType = LogOperateTypeEnum.IMPORT, operateContent = "导入了${functionName}数据")
    @LoginUser
    public Result<String> batchImport(@Param("file") MultipartFile file, BaseEntity dto, HttpServletResponse response) throws Exception {
        return ${className?uncap_first}Service.batchImport(file,dto,response);
    }

    </#if>

    /**
    * 查询${functionName}详情
    *
    * @param id
    * @return Result<${className}DetailVo>
    */
    @GetMapping("/detail")
    @ApiOperation(value = "查询${functionName}详情", notes = "查询${functionName}详情")
    @RequiresPermissions("p:${moduleName}:${bizName}:page")
    public Result<${className}DetailVo> detail(@RequestParam(value = "id") String id){
        return ${className?uncap_first}Service.detail(id);
    }

    /**
      * 保存${functionName}数据
      *
      * @param  dto
      * @return Result
    */
    @PostMapping("/save")
    @ApiOperation(value = "保存${functionName}数据", notes = "保存${functionName}数据")
    @RequiresPermissions("p:${moduleName}:${bizName}:add")
    @LogAnnotation(operationType = LogOperateTypeEnum.ADD, operateContent = "新增了${functionName}数据")
    @LoginUser
    public Result saveData(@Validated @RequestBody ${className}SaveDto dto){
        return ${className?uncap_first}Service.saveData(dto);
    }

    /**
    * 更新${functionName}数据
    *
    * @param  dto
    * @return Result
    */
    @PostMapping("/update")
    @ApiOperation(value = "更新${functionName}数据", notes = "更新${functionName}数据")
    @RequiresPermissions("p:${moduleName}:${bizName}:edit")
    @LogAnnotation(operationType = LogOperateTypeEnum.EDIT, operateContent = "更新了${functionName}数据")
    @LoginUser
    public Result updateData(@Validated @RequestBody ${className}SaveDto dto){
        return ${className?uncap_first}Service.updateData(dto);
    }

    /**
    * 删除${functionName}数据
    *
    * @param id
    * @return Result
    */
    @PostMapping("/delete")
    @ApiOperation(value = "删除${functionName}数据", notes = "删除${functionName}数据")
    @RequiresPermissions("p:${moduleName}:${bizName}:del")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "删除了${functionName}数据")
    public Result deleteData(@RequestParam(value = "id") String id){
        return ${className?uncap_first}Service.deleteData(id);
    }

    <#if isCategory != "2">
    /**
    * 批量删除${functionName}数据
    *
    * @param ids
    * @return Result
    */
    @PostMapping("/deletes")
    @ApiOperation(value = "批量删除${functionName}数据", notes = "批量删除${functionName}数据")
    @RequiresPermissions("p:${moduleName}:${bizName}:dels")
    @LogAnnotation(operationType = LogOperateTypeEnum.DEL, operateContent = "批量删除了${functionName}数据")
    public Result deletesData(@RequestParam(value = "ids") List<String> ids){
        return ${className?uncap_first}Service.deletesData(ids);
    }
    </#if>

}
