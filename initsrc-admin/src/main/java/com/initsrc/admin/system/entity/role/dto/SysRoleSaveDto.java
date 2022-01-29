package com.initsrc.admin.system.entity.role.dto;

import javax.validation.constraints.*;
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
* 角色表-更新或编辑对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 14:45:49
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysRoleSaveDto更新或编辑对象", description="更新或编辑对象")
public class SysRoleSaveDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色主键")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "查询权限 （-1：无权限 0：全部数据权限  1:自定义部门数据权限 2：本部门及以下数据权限 3：本部门数据权限 ）")
    @NotBlank(message = "查询权限 （-1：无权限 0：全部数据权限  1:自定义部门数据权限 2：本部门及以下数据权限 3：本部门数据权限 ）不能为空")
    private String isSearch;

    @ApiModelProperty(value = "自定义部门数组")
    private List<String> powerDepts;

    @ApiModelProperty(value = "状态 （0：禁用 1：正常 ）")
    @NotBlank(message = "状态 （0：禁用 1：正常 ）不能为空")
    private String status;

    @ApiModelProperty(value = "描述")
    private String des;

    @ApiModelProperty(value = "权限列表")
    private List<String> permList;
}
