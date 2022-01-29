package com.initsrc.admin.system.entity.perm.dto;

import com.initsrc.common.base.BaseSearchEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表-列表查询对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysPermQueryDto列表查询对象", description="列表查询对象")
public class SysPermQueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属菜单")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

}
