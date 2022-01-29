package com.initsrc.admin.system.entity.user.vo;

import java.util.Date;

import com.initsrc.admin.system.entity.role.vo.SysRoleListVo;
import com.initsrc.common.base.NodeEntity;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 用户表-详情对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysUserDetailVo详情对象", description="详情对象")
public class SysUserDetailVo implements Serializable {

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

    @ApiModelProperty(value = "性别 （0：男 1：女 ）")
    private String sex;

    @ApiModelProperty(value = "生日日期")
    private Date birthday;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "状态 （0：禁用  1: 正常  ）")
    private String status;

    @ApiModelProperty(value = "登录日期")
    private Date loginDate;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "角色信息")
    private List<SysRoleListVo> roles;

}
