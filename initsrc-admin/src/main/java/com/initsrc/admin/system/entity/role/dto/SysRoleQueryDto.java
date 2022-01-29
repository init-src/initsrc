package com.initsrc.admin.system.entity.role.dto;

import com.initsrc.common.base.BaseSearchEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 角色表-列表查询对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 14:45:49
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysRoleQueryDto列表查询对象", description="列表查询对象")
public class SysRoleQueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

}
