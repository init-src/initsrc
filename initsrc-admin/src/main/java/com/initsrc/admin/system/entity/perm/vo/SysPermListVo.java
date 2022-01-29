package com.initsrc.admin.system.entity.perm.vo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表-列表对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */

@Data
@Accessors(chain = true)
@ApiModel(value="SysPermListVo列表对象", description="列表对象")
public class SysPermListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    private String permId;

    @ApiModelProperty(value = "所属菜单")
    private String parentId;

    @ApiModelProperty(value = "所属菜单")
    private String permName;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单路径")
    private String path;

    @ApiModelProperty(value = "菜单类型")
    private String resource;

    @ApiModelProperty(value = "路由组件")
    private String component;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
