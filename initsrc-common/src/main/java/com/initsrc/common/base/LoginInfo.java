package com.initsrc.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 登录刷新用户信息
 * auther INITSRC (启源)
 */
@Data
public class LoginInfo {
    @ApiModelProperty(value = "用户id", example = "0")
    private String uid;
    @ApiModelProperty(value = "员工名称")
    private String nickName;
    @ApiModelProperty(value = "用户名", example = "0")
    private String userName;
    @ApiModelProperty(value = "用户手机号", example = "0")
    private String phone;
    @ApiModelProperty(value = "所属部门", example = "110000")
    private String departmentId;
    @ApiModelProperty(value = "自定义权限部门列表", example = "110000")
    private String powerDepts;
    @ApiModelProperty(value = "搜索权限", example = "0")
    private String isSearch;
    @ApiModelProperty(value = "角色id", example = "0")
    private String roleId;
    @ApiModelProperty(value = "权限", example = "0")
    private List<LoginPermVo> loginPermVos;
}
