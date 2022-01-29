package com.initsrc.admin.base.entity.home.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="基础模块-获取首页信息传参DTO", description="获取首页信息传参DTO")
public class HomeInfoDto {
    @ApiModelProperty(value = "开始时间", example = "0")
    private String beginTime;
    @ApiModelProperty(value = "结束时间", example = "0")
    private String endTime;
}
