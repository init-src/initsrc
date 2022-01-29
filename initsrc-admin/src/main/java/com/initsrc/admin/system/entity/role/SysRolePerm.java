package com.initsrc.admin.system.entity.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色权限关联表-t_sys_role_perm
 * </p>
 *
 * @author INITSRC (启源)
 * @since  2021-02-18 16:23:37
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("is_sys_role_perm")
@ApiModel(value="SysRolePerm对象", description="")
public class SysRolePerm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色权限关联主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "权限ID")
    private String permId;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

}
