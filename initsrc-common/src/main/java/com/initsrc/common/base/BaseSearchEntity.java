package com.initsrc.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 登录搜索基础信息
 * 作者：INITSRC
 */
@ApiModel(value = "BaseSearchEntity", description = "")
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseSearchEntity extends BaseEntity{
    @ApiModelProperty(value = "页号（默认1）", example = "1")
    @Min(value = 1,message = "page必须大于1")
    public int page = 1;

    @ApiModelProperty(value = "每页大小（默认10）", example = "10")
    @Max(value = 500,message = "最大限制每页500条数据")
    @Min(value = 10,message = "最小限制每页10条数据")
    public int limit = 10;

    @ApiModelProperty(value = "排序（asc:正序，desc:倒序）", example = "desc")
    public String sort = "desc";
}
