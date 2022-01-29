package com.initsrc.admin.system.entity.role.vo;

import com.initsrc.admin.system.entity.user.vo.SysUserListVo;
import com.initsrc.common.base.LoginPermVo;
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
* 角色表-详情对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 14:45:49
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysRoleDetailVo详情对象", description="详情对象")
public class SysRoleDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色主键")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "查询权限 （-1：无权限 0：全部数据权限  1:自定义部门数据权限 2：本部门及以下数据权限 3：本部门数据权限 ）")
    private String isSearch;

    @ApiModelProperty(value = "自定义部门数组")
    private String powerDepts;

    @ApiModelProperty(value = "自定义部门数组名称")
    private String powerDeptsName;

    @ApiModelProperty(value = "是否系统角色 0：不是 1：是")
    private String isSystem;

    @ApiModelProperty(value = "状态 （0：禁用 1：正常 ）")
    private String status;

    @ApiModelProperty(value = "描述")
    private String des;

    @ApiModelProperty(value = "权限")
    private List<LoginPermVo> permVos;

    @ApiModelProperty(value = "管理用户")
    List<SysUserListVo> sysUserListVo;

}
