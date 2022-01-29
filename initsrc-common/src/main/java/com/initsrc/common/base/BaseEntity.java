package com.initsrc.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录基础信息
 * 作者：INITSRC
 */
@Data
public class BaseEntity implements Serializable {
    //登录用户id
    @ApiModelProperty(hidden = true)
    private String authId;
    //登录用户权限
    @ApiModelProperty(hidden = true)
    private String scopeType;
    //自定义权限部门数组
    @ApiModelProperty(hidden = true)
    private String scopeIds;
    //用户所属部门
    @ApiModelProperty(hidden = true)
    private String scopeId;
    /*参数注入*/
    @ApiModelProperty(hidden = true)
    private Map<String, Object> params = new HashMap<>();
}
