package com.initsrc.admin.base.entity.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.initsrc.admin.system.entity.role.vo.SysRoleListVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "基础模块-返回登录权限信息VO", description = "返回登录权限信息")
public class AuthInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @ApiModelProperty(value = "用户id", example = "")
    private String id;

    @ApiModelProperty(value = "员工名称")
    private String nickName;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "部门ID")
    private String deptId;

    @ApiModelProperty(value = "员工手机号")
    private String mobile;

    @ApiModelProperty(value = "员工头像")
    private String headImg;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "角色")
    private List<SysRoleListVo> roles;

}
