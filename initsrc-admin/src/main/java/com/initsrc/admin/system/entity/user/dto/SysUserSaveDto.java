package com.initsrc.admin.system.entity.user.dto;

import javax.validation.constraints.*;
import java.util.Date;
import com.initsrc.common.base.BaseEntity;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 用户表-更新或编辑对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysUserSaveDto更新或编辑对象", description="更新或编辑对象")
public class SysUserSaveDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户主键")
    private String userId;

    @ApiModelProperty(value = "所属部门")
    @NotBlank(message = "所属部门不能为空")
    private String deptId;

    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPwd;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "性别 （0：男 1：女 ）")
    private String sex;

    @ApiModelProperty(value = "生日日期")
    private Date birthday;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "状态 （0：禁用  1: 正常  ）")
    @NotBlank(message = "状态 （0：禁用  1: 正常  ）不能为空")
    private String status;

    @ApiModelProperty(value = "角色ID")
    private List<String> roleId;

    @ApiModelProperty(value = "备注")
    private String remark;

}
