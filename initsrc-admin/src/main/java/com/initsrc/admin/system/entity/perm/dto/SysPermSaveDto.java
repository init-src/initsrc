package com.initsrc.admin.system.entity.perm.dto;

import javax.validation.constraints.*;
import com.initsrc.common.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表-更新或编辑对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysPermSaveDto更新或编辑对象", description="更新或编辑对象")
public class SysPermSaveDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    private String permId;

    @ApiModelProperty(value = "所属菜单")
    @NotBlank(message = "所属菜单不能为空")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    @ApiModelProperty(value = "菜单路径")
    @NotBlank(message = "菜单路径不能为空")
    private String path;

    @ApiModelProperty(value = "菜单类型")
    @NotBlank(message = "菜单类型不能为空")
    private String resource;

    @ApiModelProperty(value = "路由组件")
    private String component;

    @ApiModelProperty(value = "shiro权限")
    @NotBlank(message = "shiro权限不能为空")
    private String perm;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "按钮颜色")
    private String color;

    @ApiModelProperty(value = "是否缓存")
    @NotBlank(message = "是否缓存不能为空")
    private String isCache;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "链接类型")
    @NotBlank(message = "链接类型不能为空")
    private String linkType;

}
