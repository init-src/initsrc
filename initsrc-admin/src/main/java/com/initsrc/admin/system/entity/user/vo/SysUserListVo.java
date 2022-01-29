package com.initsrc.admin.system.entity.user.vo;

import java.io.Serializable;
import java.util.List;

import com.initsrc.admin.system.entity.role.vo.SysRoleListVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 用户表-列表对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysUserListVo列表对象", description="列表对象")
public class SysUserListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户主键")
    private String userId;

    @ApiModelProperty(value = "所属部门")
    private String deptId;

    @ApiModelProperty(value = "所属部门")
    private String deptName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "状态 （0：禁用  1: 正常  ）")
    private String status;

    @ApiModelProperty(value = "角色信息")
    private List<SysRoleListVo> roles;

}
