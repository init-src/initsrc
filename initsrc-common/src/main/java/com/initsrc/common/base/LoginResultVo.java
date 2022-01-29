package com.initsrc.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "基础模块-返回登录信息VO", description = "返回登录信息")
public class LoginResultVo {
    @ApiModelProperty(value = "token", example = "xxx")
    private String token;
    @ApiModelProperty(value = "ticket", example = "xxx")
    private String ticket;
}
