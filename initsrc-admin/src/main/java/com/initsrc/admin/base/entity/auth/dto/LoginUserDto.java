package com.initsrc.admin.base.entity.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "基础模块-登录参数DTO", description = "登录参数")
public class LoginUserDto {
    @NotBlank(message = "登陆账号不能为空")
    @ApiModelProperty(value = "登录账号", example = "1")
    private String userName;

    @NotBlank(message = "登陆密码不能为空")
    @ApiModelProperty(value = "登录密码", example = "1", required = true)
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", example = "X5625", required = true)
    private String code;
}
