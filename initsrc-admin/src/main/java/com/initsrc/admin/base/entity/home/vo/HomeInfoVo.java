package com.initsrc.admin.base.entity.home.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="基础模块-获取首页信息Vo", description="获取首页信息Vo")
public class HomeInfoVo {
    @ApiModelProperty(value = "近七日登录信息", example = "0")
    private List<Integer> loginList;
    @ApiModelProperty(value = "登录操作总数", example = "0")
    private Integer loginCount;
    @ApiModelProperty(value = "新增操作总数", example = "0")
    private Integer addCount;
    @ApiModelProperty(value = "编辑操作总数", example = "0")
    private Integer editCount;
    @ApiModelProperty(value = "删除操作总数", example = "0")
    private Integer delCount;
    @ApiModelProperty(value = "所有操作总数", example = "0")
    private Integer totalCount;
}
