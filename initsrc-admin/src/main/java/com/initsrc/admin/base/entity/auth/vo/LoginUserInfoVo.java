package com.initsrc.admin.base.entity.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "基础模块-登录用户参数VO", description = "登录用户参数")
public class LoginUserInfoVo {
    @ApiModelProperty(value = "用户id", example = "1")
    private String id;

    @ApiModelProperty(value = "用户昵称", example = "1")
    private String nickName;

    @ApiModelProperty(value = "账号", example = "1")
    private String userName;

    @ApiModelProperty(value = "密码", example = "1")
    private String userPwd;

    @ApiModelProperty(value = "密码盐", example = "1")
    private String salt;

}
