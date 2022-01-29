package com.initsrc.dev.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.initsrc.common.annotation.LogAnnotation;
import com.initsrc.common.annotation.LoginUser;
import com.initsrc.common.base.PageResult;
import com.initsrc.common.base.Result;
import com.initsrc.common.enums.LogOperateTypeEnum;
import com.initsrc.dev.entity.table.dto.DevTableQueryDto;
import com.initsrc.dev.entity.table.dto.DevTableSaveDto;
import com.initsrc.dev.entity.table.vo.DevTableDetailVo;
import com.initsrc.dev.entity.table.vo.DevTableListVo;
import com.initsrc.dev.service.DevTableService;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据库表-前端控制器
 * </p>
 *
 * @author INITSRC
 * @since  2021-01-27 11:31:29
 */

@RestController
@RequestMapping("/devApi/generator")
@Api(tags = "代码生成模块")
public class DevGeneratorController {
    @Resource
    private DevTableService devTableService;

    /**
     * 查询数据库表列表
     *
     * @param  dto
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "查询数据库表列表", notes = "查询数据库表列表")
    @RequiresPermissions("p:dev:generator:page")
//    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询数据库表数据列表")
    @LoginUser
    public Result<PageResult<DevTableListVo>> pageData(@Validated DevTableQueryDto dto){
        Page<DevTableListVo> page = PageHelper.startPage(dto.page, dto.limit,"create_time "+dto.sort);
        List<DevTableListVo> list = devTableService.pageData(dto);
        return Result.success(PageResult.buildPageResult(page));
    }

    /**
     * 查询数据库表详情
     *
     * @param id
     * @return Result<GenTableDetailVo>
     */
    @GetMapping("/detail")
    @ApiOperation(value = "查询数据库表详情", notes = "查询数据库表详情")
    @RequiresPermissions("p:dev:generator:page")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "查询数据库表数据详情")
    public Result<DevTableDetailVo> detail(@RequestParam(value = "id") String id){
        return devTableService.detail(id);
    }

    /**
     * 更新数据库表数据
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新数据库表数据", notes = "更新数据库表数据")
    @RequiresPermissions("p:dev:generator:edit")
    @LogAnnotation(operationType = LogOperateTypeEnum.EDIT, operateContent = "更新了数据库表数据")
    @LoginUser
    public Result updateData(@Validated @RequestBody DevTableSaveDto dto){
        return devTableService.updateData(dto);
    }

    /**
     * 同步表的数据库信息
     *
     * @param
     * @return
     */
    @PostMapping("/syncDbByTableName")
    @ApiOperation(value = "同步表的数据库信息", notes = "同步表的数据库信息")
    @RequiresPermissions("p:dev:generator:sync")
    public Result SyncDbByTableName(@RequestParam(value = "tableName") String tableName){
        return devTableService.SyncDbByTableName(tableName);
    }


    /**
     * 预览代码生成
     *
     * @param  id
     * @return
     */
    @GetMapping("/preview")
    @ApiOperation(value = "预览代码生成", notes = "预览代码生成")
    @RequiresPermissions("p:dev:generator:detail")
    @LogAnnotation(operationType = LogOperateTypeEnum.SEARCH, operateContent = "预览数据库表数据")
    public Result<Map<String,String>> previewData(@RequestParam(value = "id") String id) throws Exception {
        return devTableService.previewData(id);
    }

    /**
     * 下载代码
     *
     * @param  id
     * @return
     */
    @RequiresPermissions("p:dev:generator:download")
    @ApiOperation(value = "下载代码", notes = "下载代码")
    @LogAnnotation(operationType = LogOperateTypeEnum.EXPORT, operateContent = "下载了代码")
    @GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam(value = "id") String id) throws IOException, TemplateException {
        byte[] data = devTableService.downloadCode(id);
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"initsrc.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

}
