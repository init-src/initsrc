package com.initsrc.admin.system.entity.role;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
    * 角色表-is_sys_role
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 14:45:49
*/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("is_sys_role")
@ApiModel(value="SysRole对象", description="")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色主键")
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "查询权限 （-1：无权限 0：全部数据权限  1:自定义部门数据权限 2：本部门及以下数据权限 3：本部门数据权限 ）")
    private String isSearch;

    @ApiModelProperty(value = "自定义部门数组")
    private String powerDepts;

    @ApiModelProperty(value = "是否系统角色 0：不是 1：是")
    private String isSystem;

    @ApiModelProperty(value = "状态 （0：禁用 1：正常 ）")
    private String status;

    @ApiModelProperty(value = "描述")
    private String des;

    @ApiModelProperty(value = "删除标识 （0：删除 1：正常）")
    @TableLogic
    private String dr;

    @ApiModelProperty(value = "幂等")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}
