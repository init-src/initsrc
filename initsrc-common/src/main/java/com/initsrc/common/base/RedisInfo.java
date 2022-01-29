package com.initsrc.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述：权限验证实体
 * 作者：INITSRC
 */
@Data
public class RedisInfo<T> {
    @ApiModelProperty(value = "用户信息", example = "0")
    private T loginInfo;
    @ApiModelProperty(value = "登陆者门票", example = "0")
    private Long ticket;
    @ApiModelProperty(value = "登陆当前ip", example = "0")
    private String ip;
    @ApiModelProperty(value = "平台", example = "0")
    private String plf;
    @ApiModelProperty(value = "token验证时间", example = "0")
    private Long currentTimeMillis;
}
