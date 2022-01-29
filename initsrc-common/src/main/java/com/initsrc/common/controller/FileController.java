package com.initsrc.common.controller;

import com.initsrc.common.base.Result;
import com.initsrc.common.plugin.oss.OssServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  公共接口-文件上传下载接口
 * </p>
 *
 * @author INITSRC
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/common/file")
@Api(tags = "公共接口-文件上传下载接口")
public class FileController {
    @Resource
    private OssServiceImpl ossService;


    /**
     * 授权前端阿里OSS TOKEN
     *
     * @param
     * @return Result
     */
    @PostMapping("/getAliOssToken")
    @ApiOperation(value = "授权前端阿里OSS TOKEN", notes = "授权前端阿里OSS TOKEN")
    public Result getAliOssToken(){
        return Result.success(ossService.getStsAuthorization());
    }
}
